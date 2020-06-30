package com.zb.mapper;

import com.zb.pojo.Record;
import com.zb.pojo.TempStore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TempStoreMapper {
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


	/**
	 * 修改临时库存
	 * @param tempStore
	 * @return
	 */
	public Integer updateTempStore(TempStore tempStore);
}
