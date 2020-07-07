package com.zb.service;

import com.zb.pojo.TempStore;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 王效全
 * @Description TODO
 * @Date 2020/6/11
 * @Version V1.0
 */
public interface TempStoreService {
    /**
     * 添加一条记录
     * @param companyid
     * @param userid
     * @return
     */
    public Integer insertTempStore(@PathVariable("roomId") Integer companyid, @PathVariable("uid") Integer userid);

    /**
     * 根据id删除记录
     * @param id
     * @return
     */
    public Integer deleteTempStoreById(@Param(value = "id") Long id);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    public List<TempStore> getTempStoreByCompanyId(@Param(value = "id") Integer companyid);
    /**
     * 修改临时库存
     * @param tempStore
     * @return
     */
    public Integer updateTempStore(TempStore tempStore);
}
