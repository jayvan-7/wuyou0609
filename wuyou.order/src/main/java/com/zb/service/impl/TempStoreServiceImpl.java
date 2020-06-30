package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.entity.EnterpriseUser;
import com.zb.mapper.TempStoreMapper;
import com.zb.pojo.TempStore;
import com.zb.service.TempStoreService;
import com.zb.util.IdWorker;
import com.zb.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王效全
 * @Description TODO
 * @Date 2020/6/11
 * @Version V1.0
 */
@Service
public class TempStoreServiceImpl implements TempStoreService {
    @Autowired(required = false)
    private TempStoreMapper tempStoreMapper;
    @Autowired(required = false)
    private RedisUtil redisUtil;

    @Override
    public Integer insertTempStore(Integer companyid, Integer userid) {
        //获取redis中的商品信息
        String key="discountcompany:"+companyid;
        String cjson=redisUtil.get(key).toString();
        EnterpriseUser company= JSON.parseObject(cjson,EnterpriseUser.class);

        //向临时库存表中添加一条抢购记录
        TempStore tempStore=new TempStore();
        tempStore.setId(Long.parseLong(IdWorker.getId()));
        tempStore.setCompanyid(companyid);
        tempStore.setUserid(userid);
        tempStore.setStatus(0);
        tempStore.setStore(company.getDiscountnum());

        //修改redis中的库存-1
        company.setDiscountnum(company.getDiscountnum()-1);
        redisUtil.set(key,JSON.toJSONString(company));

        return tempStoreMapper.insertTempStore(tempStore);
    }

    @Override
    public Integer deleteTempStoreById(Integer id) {
        return tempStoreMapper.deleteTempStoreById(id);
    }

    @Override
    public TempStore getTempStoreById(Integer id) {
        return tempStoreMapper.getTempStoreById(id);
    }

    @Override
    public Integer updateTempStore(TempStore tempStore) {
        return tempStoreMapper.updateTempStore(tempStore);
    }
}
