package com.zb.service;

import com.zb.pojo.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 王效全
 * @Description TODO
 * @Date 2020/6/10
 * @Version V1.0
 */
public interface RecordService {
    /**
     * 添加订单
     * @param record
     * @return
     */
    public  int insertOrder(Record record);

    /**
     * 根据id查找记录
     * @param id
     * @return
     */
    public Record findOrderByid(Integer id);

    /**
     * 修改订单的状态。交易编号改为支付宝产生的交易编号，（支付完成以后进行修改）
     * @param orderno
     * @param tradeno
     * @return
     */
    public int updateOrderstate(String orderno, String tradeno);

    /**
     * 根据用户id查该用户的订单信息
     * @param userid
     * @return
     */
    public List<Record> findOrderByUserid(Integer userid);

    /**
     * 根据装修公司id查该用户的订单信息
     * @param companyid
     * @return
     */
    public List<Record> findOrderByCompanyid(Integer companyid);

    /**
     * 根据订单号查询订单
     * @param orderno
     * @return
     */
    public Record findOrderByOrderNo(String orderno);


    /**
     * 查询一分钟之后的任务
     * @return
     */
    public List<Record> findByUpdateTimeBefore();

    /**
     * 获取乐观锁
     * @param id
     * @param version
     * @return
     */
    public int findTaskByidAndversion(Long id, Integer version);

    /**
     * 发送消息到消息队列，并修改任务的更新时间
     * @param record
     */
    public void publishOrder(Record record);

    /**
     * 删除任务（接收到record模块发送到MQ中的消息后）
     * @param record
     */
    public void finishOrder(Record record);

    /**
     * 抢购方法
     * @param companyid
     * @param token
     * @return
     */
    public String qg(Integer companyid, String token);

    /**
     * 轮询方法
     * @param companyid
     * @param token
     * @return
     */
    public String qgwhile(Integer companyid, String token);

}
