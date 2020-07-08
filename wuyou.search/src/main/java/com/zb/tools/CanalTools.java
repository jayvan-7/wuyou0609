package com.zb.tools;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.util.*;

@Component
public class CanalTools {
    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private RestTemplate restTemplate;

    public void execution() {
        // 创建链接
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(AddressUtils.getHostIp(),
                11111), "example", "root", "ok");
        int batchSize = 1000;
        int emptyCount = 0;
        try {
            connector.connect();
            connector.subscribe(".*\\..*");
            connector.rollback();
            int totalEmtryCount = 1200;
            while (emptyCount < totalEmtryCount) {
                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    emptyCount++;
                    //System.out.println("empty count : " + emptyCount);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    emptyCount = 0;
                    // System.out.printf("message[batchId=%s,size=%s] \n", batchId, size);
                    printEntry(message.getEntries());
                }

                connector.ack(batchId); // 提交确认
                // connector.rollback(batchId); // 处理失败, 回滚数据
            }

            System.out.println("empty too many times, exit");
        } finally {
            connector.disconnect();
        }
    }

    private void printEntry(List<Entry> entrys) {
        for (Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }

            RowChange rowChage = null;
            try {
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));

            for (RowData rowData : rowChage.getRowDatasList()) {
                //对效果图表进行同步
                if (entry.getHeader().getTableName().equals("design_sketch")){
                    if (eventType == EventType.DELETE) {
                        printColumn(rowData.getBeforeColumnsList());
                        //deleteData(rowData.getAfterColumnsList());
                    } else if (eventType == EventType.INSERT) {
                        //printColumn(rowData.getAfterColumnsList());
                        insertData(rowData.getAfterColumnsList());
                    } else {
                        updateESData(rowData.getAfterColumnsList());
                    }
                }

                if (entry.getHeader().getTableName().equals("tb_content")){
                    updateRedisData(rowData.getAfterColumnsList());
                }

            }
        }
    }

    /**
     * 同步删除的方法
     * @param columns
     */
    public void deleteData(List<Column>columns){
        System.out.println("同步删除数据");
        try {
            Map<String, Object> data = new HashMap<>();
            String id ="";
            for (Column column : columns) {
                if(column.getName().equals("id")){
                    id = column.getValue();
                    continue;
                }
            }
            System.out.println(id);
            DeleteRequest deleteRequest=new DeleteRequest("my_design", "doc", id);
            DeleteResponse deleteResponse=client.delete(deleteRequest);
            DocWriteResponse.Result result = deleteResponse.getResult();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步增加数据的方法
     * @param columns
     */
    public void insertData(List<Column>columns){
        System.out.println("同步增加数据");
        try {
            Map<String, Object> data = new HashMap<>();
            String id ="";
            for (Column column : columns) {
                if(column.getName().equals("id")){
                    id = column.getValue();
                    continue;
                }
                data.put(column.getName(),column.getValue() );
            }
            IndexRequest indexRequest=new IndexRequest("my_design", "doc", id);
            indexRequest.source(data);
            IndexResponse indexResponse=client.index(indexRequest);
            DocWriteResponse.Result result = indexResponse.getResult();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 同步修改数据的方法
     * @param columns
     */
    private void updateESData(List<Column> columns) {
        System.out.println("同步修改数据");
        try {
            Map<String, Object> data = new HashMap<>();
            String id ="";
            for (Column column : columns) {
                if(column.getName().equals("id")){
                    id = column.getValue();
                    continue;
                }
                data.put(column.getName(),column.getValue() );
            }
            UpdateRequest updateRequest = new UpdateRequest("my_design", "doc", id);
            updateRequest.doc(data);
            UpdateResponse updateResponse = client.update(updateRequest);
            DocWriteResponse.Result result = updateResponse.getResult();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步修改数据的方法
     * 页面广告的同步
     * @param columns
     */
    private void updateRedisData(List<Column> columns) {
        Set<Integer> categoryId = new HashSet<>();
        for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "  update=" + column.getUpdated());
            if(column.getName().equals("category_id")) {
                categoryId.add(Integer.parseInt(column.getValue()));
            }
        }
        for (Integer cid : categoryId) {
            //程序内部发起的http请求， 调用ngix的执行写的操作的接口
            String myurl ="http://localhost:9000/readData";
            String result = restTemplate.getForObject(myurl, String.class);
            System.out.println("远程调用nginx中的接口程序:"+result);
        }
    }


    private void printColumn(List<Column> columns) {
       /* for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "  update=" + column.getUpdated());
        }*/
    }
}
