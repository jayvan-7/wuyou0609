package com.zb.tools;

import com.zb.entity.DesignSketch;
import com.zb.mapper.DesignSketchMapper;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/15
 * @Version V1.0
 */
@Component
public class EsTool {
    @Autowired(required = false)
    private DesignSketchMapper designSketchMapper;
    @Autowired
    private RestHighLevelClient client;

    /**
     * 新建一个索引库
     * @throws Exception
     */
    public void addIndex() throws Exception {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("my_design");
        //参数
        createIndexRequest.settings(
                Settings.builder().put("number_of_shards", 1).
                        put("number_of_replicas", 0).
                        build());
        //创建映射
        createIndexRequest.mapping("doc", "{\n" +
                "        \"properties\": {\n" +
                "          \"designname\": {\n" +
                "            \"type\": \"text\",\n" +
                "            \"analyzer\": \"ik_max_word\",\n" +
                "            \"search_analyzer\": \"ik_smart\"\n" +
                "          },\n" +
                "          \"type\": {\n" +
                "            \"type\": \"keyword\"\n" +
                "          },\n" +
                "          \"style\": {\n" +
                "            \"type\": \"keyword\"\n" +
                "          },\n" +
                "          \"area\": {\n" +
                "            \"type\": \"float\"\n" +
                "          }\n" +
                "        }\n" +
                "      }", XContentType.JSON);
        //获取索引对象
        IndicesClient indices = client.indices();
        //创建并返回一个响应对象
        CreateIndexResponse indexResponse = indices.create(createIndexRequest);
        //是否执行成功
        boolean val = indexResponse.isAcknowledged();
        System.out.println(val);
    }

    /**
     * 把数据库中查到的所有数据存到ES中
     * @throws Exception
     */
    public void importData()throws Exception {
        List<DesignSketch>designSketchList=designSketchMapper.findDesignAll();
        for (DesignSketch d:designSketchList){
            Map<String,Object>data=new HashMap<>();
            data.put("designname",d.getDesignname());
            data.put("type",d.getType());
            data.put("style",d.getStyle());
            data.put("area",d.getArea());
            data.put("piccount",d.getPiccount());
            data.put("userid",d.getUserid());
            data.put("firstimg",d.getFirstimg());

            //创建请求对象
            IndexRequest indexRequest=new IndexRequest("my_design","doc",d.getId()+"");
            //绑定数据
            indexRequest.source(data);
            //执行获取相应
            IndexResponse indexResponse=client.index(indexRequest);
            DocWriteResponse.Result result = indexResponse.getResult();
        }

    }

}
