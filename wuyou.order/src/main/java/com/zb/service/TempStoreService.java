package com.zb.service;

import com.zb.pojo.TempStore;
import org.apache.ibatis.annotations.Param;

/**
 * @author 王效全
 * @Description TODO
 * @Date 2020/6/11
 * @Version V1.0
 */
public interface TempStoreService {
    /**
     * 添加一条记录
     * @param tempStore
     * @return
     */
    public Integer insertTempStore(TempStore tempStore);

    /**
     * 根据id删除记录
     * @param id
     * @return
     */
    public Integer deleteTempStoreById(@Param(value = "id") Integer id);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    public TempStore getTempStoreById(@Param(value = "id") Integer id);
}
