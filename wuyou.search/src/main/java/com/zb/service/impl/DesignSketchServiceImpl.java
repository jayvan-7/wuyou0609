package com.zb.service.impl;

import com.zb.entity.DesignSketch;
import com.zb.entity.Pic;
import com.zb.mapper.DesignSketchMapper;
import com.zb.mapper.PicMapper;
import com.zb.service.DesignSketchService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/9
 * @Version V1.0
 */
@Service
public class DesignSketchServiceImpl implements DesignSketchService {

    @Autowired
    private RestHighLevelClient client;
    @Autowired(required = false)
    private DesignSketchMapper designSketchMapper;
    @Autowired(required = false)
    private PicMapper picMapper;

    /**
     *
     * @param keyword
     * @param type
     * @param style
     * @param area
     * @param index
     * @param size
     * @return
     * @throws Exception
     */
    @Override
    public List<DesignSketch> SearchDesignES(String keyword, String type, String style, Double area, Integer index, Integer size) throws Exception {
        List<DesignSketch>designSketchList=new ArrayList<>();
        //创建查询请求对象
        SearchRequest searchRequest = new SearchRequest("my_design");
        searchRequest.types("doc");
        //构建查询方式
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from((index - 1) * size);
        searchSourceBuilder.size(size);

        //因为是多个条件的组合创建bool查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //关键词查询
        if (keyword!=null&&!"".equals(keyword)){
            //多个列的分词查询
            boolQueryBuilder.must(QueryBuilders.multiMatchQuery(keyword,new String[]{"auctionName","auctionDesc"})
                    .operator(Operator.OR).field("designname", 10));
        }
        //户型查询
        if (type!=null&&!"".equals(type)){
            boolQueryBuilder.filter((QueryBuilders.termQuery("type",type)));
        }
        //风格查询
        if (style!=null&&!"".equals(style)){
            boolQueryBuilder.filter((QueryBuilders.termQuery("style",style)));
        }
        //面积范围查询
        if(area!=null){
            if(area==1){
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("area").lte(60));
            }else if(area==2){
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("area").gte(60).lte(80));
            }else if(area==3){
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("area").gte(81).lte(100));
            }else if(area==4){
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("area").gte(101).lte(120));
            }else if(area==5){
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("area").gte(121).lte(150));
            }else if(area==6){
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("area").gte(151).lte(200));
            }else if(area==7){
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("area").gte(201).lte(500));
            }else if(area==8){
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("area").gte(501).lte(1000));
            }else if(area==9){
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("area").gte(1000));
            }
        }

        //创建高亮对象
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //设置标签
        highlightBuilder.preTags("<div style=’color:red;’>");
        highlightBuilder.postTags("</div>");
        //添加高亮字段
        highlightBuilder.fields().add(new HighlightBuilder.Field("designname"));
        //将highlightBuilder对象添加到查询对象中
        searchSourceBuilder.highlighter(highlightBuilder);

        //绑定查询构建对象
        searchSourceBuilder.query(boolQueryBuilder);
        //将构建对象存储到request对象中去
        searchRequest.source(searchSourceBuilder);
        //执行查询并获取响应对象
        SearchResponse searchResponse = client.search(searchRequest);
        //获取索引的hits
        SearchHits hits = searchResponse.getHits();
        //获取到数组对象(存储的数据)
        SearchHit[] hitsHits = hits.getHits();
        for (SearchHit hit : hitsHits) {
            String id = hit.getId();
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            String mydesignname = sourceAsMap.get("designname").toString();
            String mytype = sourceAsMap.get("type").toString();
            String mystyle = sourceAsMap.get("style").toString();
            Double myarea = Double.parseDouble(sourceAsMap.get("area").toString());
            Integer mypiccount = Integer.parseInt(sourceAsMap.get("piccount").toString());
            Integer myuserid = Integer.parseInt(sourceAsMap.get("userid").toString());
            String myfirstimg = sourceAsMap.get("firstimg").toString();

            //获取高亮数据
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (highlightFields != null) {
                HighlightField nameField = highlightFields.get("designname");
                //验证高亮部分中是否包含该属性
                if (nameField != null) {
                    Text[] nameText = nameField.getFragments();
                    StringBuffer nameSbf = new StringBuffer();
                    for (Text text : nameText) {
                        nameSbf.append(text);
                    }
                    //覆盖掉原始的数据
                    mydesignname = nameSbf.toString();
                }
            }
            DesignSketch designSketch = new DesignSketch();
            designSketch.setId(Integer.parseInt(id));
            designSketch.setDesignname(mydesignname);
            designSketch.setType(mytype);
            designSketch.setStyle(mystyle);
            designSketch.setArea(myarea);
            designSketch.setPiccount(mypiccount);
            designSketch.setUserid(myuserid);
            designSketch.setFirstimg(myfirstimg);
            designSketchList.add(designSketch);
        }
        return designSketchList;
    }

    /**
     * 查看效果图详情,把图片封装到属性集合中
     * @param id
     * @return
     */
    @Override
    public DesignSketch findDesignByid(Integer id) {
        DesignSketch designSketch=designSketchMapper.findDesignByid(id);
        List<Pic>picList=picMapper.findPicByDesignId(id);
        designSketch.setImgUrl(picList);
        return designSketch;
    }
}
