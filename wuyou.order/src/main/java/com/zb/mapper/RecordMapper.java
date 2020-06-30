package com.zb.mapper;

import com.zb.pojo.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecordMapper {
	/**
	 * 根据id查对应的订单信息
	 * @param id
	 * @return
	 */
	public Record getRecordById(@Param(value = "id") Integer id);

	/**
	 * 增加新的订单
	 * @param record
	 * @return
	 */
	public Integer insertRecord(Record record);

	/**
	 * 删除任务（接收到record模块发送到MQ中的消息后）
	 * @param id
	 * @return
	 */
	public Integer deleteRecordById(@Param(value = "id") String id);

	/**
	 * 修改订单的状态。交易编号改为支付宝产生的交易编号，（支付完成以后进行修改）
	 * @param orderno  订单编号
	 * @param tradwno  交易编号
	 * @return
	 */
	public int updateOrder(@Param("orderno") String orderno, @Param("tradwno") String tradwno);

	/**
	 * 根据用户id查出用户的订单信息
	 */
	public List<Record> findOrderByUserid(@Param("userid") Integer userid);
	/**
	 * 根据装修公司id查出用户的订单信息
	 */
	public List<Record> findOrderByCompanyid(@Param("companyid") Integer companyid);
	/**
	 * 当用户下单30分钟后未支付，将订单状态改为已取消
	 */
	public int updateStatetoFinish(@Param("orderno") String orderno);
	/**
	 * 根据订单号查询订单
	 */
	public Record findOrderByOrderNo(@Param("orderNo") String orderNo);

	/**
	 * 为防止任务信息一致，每次发送的消息都应更改其发送的时间为当前时间
	 */
	public int updateOrdertime(Record record);
	/**
	 * 查询一分钟之后的任务
	 */
	public List<Record> findByUpdateTimeBefore();

	/**
	 * 乐观锁(当有线程准备修改数据时，修改这条记录的乐观锁版本，其它的线程拿这个版本与其本身的version对比，如果不一致，则不能进行修改)
	 */
	public int updateTaskVersion(Record Record);
}
