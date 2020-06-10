package com.zb.mapper;

import com.zb.pojo.TempStore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TempStoreMapper {

	public TempStore getTempStoreById(@Param(value = "id") Long id)throws Exception;

	public List<TempStore>	getTempStoreListByMap(Map<String, Object> param)throws Exception;

	public Integer getTempStoreCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertTempStore(TempStore tempStore)throws Exception;

	public Integer updateTempStore(TempStore tempStore)throws Exception;

	public Integer deleteTempStoreById(@Param(value = "id") Long id)throws Exception;

	public Integer batchDeleteTempStore(Map<String, List<String>> params);

}
