package com.zb.service.impl;

import com.zb.mapper.TempStoreMapper;
import com.zb.pojo.TempStore;
import com.zb.service.TempStoreService;
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
    @Override
    public Integer insertTempStore(TempStore tempStore) {
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
}
