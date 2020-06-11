package com.zb.mapper;

import com.zb.pojo.RecordHis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RecordHisMapper {
	//根据id查历史任务
	public RecordHis getRecordHisById(@Param(value = "id") Integer id);
	//增加历史任务
	public Integer insertRecordHis(RecordHis recordHis);
	//修改历史任务
	public Integer updateRecordHis(RecordHis recordHis);


}
