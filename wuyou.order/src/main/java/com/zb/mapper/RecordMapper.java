package com.zb.mapper;

import com.zb.pojo.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RecordMapper {

	public Record getRecordById(@Param(value = "id") Long id)throws Exception;

	public List<Record>	getRecordListByMap(Map<String, Object> param)throws Exception;

	public Integer getRecordCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertRecord(Record record)throws Exception;

	public Integer updateRecord(Record record)throws Exception;

	public Integer deleteRecordById(@Param(value = "id") Long id)throws Exception;

	public Integer batchDeleteRecord(Map<String, List<String>> params);

}
