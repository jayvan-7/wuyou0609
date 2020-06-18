package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.entity.Area;
import com.zb.entity.EnterpriseUser;
import com.zb.entity.ExtendProperty;
import com.zb.entity.Pic;
import com.zb.mapper.AreaMapper;
import com.zb.mapper.EnterpriseUserMapper;
import com.zb.mapper.ExtendMapper;
import com.zb.mapper.PicMapper;
import com.zb.service.EnterpriseUserService;
import com.zb.util.PageUtil;
import com.zb.util.RedisUtil;
import com.zb.vo.CompanyPageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.awt.geom.AreaOp;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/11
 * @Version V1.0
 */
@Service
public class EnterpriseUserServiceImpl implements EnterpriseUserService {
    @Autowired(required = false)
    private EnterpriseUserMapper enterpriseUserMapper;
    @Autowired(required = false)
    private AreaMapper areaMapper;
    @Autowired(required = false)
    private ExtendMapper extendMapper;
    @Autowired(required = false)
    private PicMapper picMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public PageUtil<EnterpriseUser> findCompanyPage(String[] extendname,
                                                    String[] servicearea,
                                                    Integer index,
                                                    Integer size,
                                                    String ordertype) {
        //将查询条件封装成一个对象
        CompanyPageForm companyPageForm=new CompanyPageForm();
        companyPageForm.setExtendname(extendname);
        companyPageForm.setServicearea(servicearea);
        companyPageForm.setStart((index-1)*size);
        companyPageForm.setSize(size);
        companyPageForm.setOrdertype(ordertype);
        //查询分页数据
        PageUtil<EnterpriseUser>pageUtil=new PageUtil<>();
        List<EnterpriseUser>data=enterpriseUserMapper.findCompanyPage(companyPageForm);
        int count=enterpriseUserMapper.findCount(companyPageForm);
        pageUtil.setPageindex(index);
        pageUtil.setPagesize(size);
        pageUtil.setTotalNewscount(count);
        //把装修公司的服务区域、特色、公司详情页的5张图片 封装进去,把前台所需要的数据都封装进来
        for(EnterpriseUser e:data){
            List<Area>areas=areaMapper.findAreaByCompanyid(e.getId());
            List<ExtendProperty>extendProperties=extendMapper.findPropertyByCompanyid(e.getId());
            List<Pic>companyPic=picMapper.findCompanyPicByid(e.getId());
            e.setImgUrl(companyPic);
            e.setAreas(areas);
            e.setExtendProperties(extendProperties);
        }
        pageUtil.setData(data);
        return pageUtil;
    }

    //根据id查装修公司详情
    @Override
    public EnterpriseUser findCompanyByid(Integer id) {
        String key="EnterpriseUser-"+id;
        EnterpriseUser enterpriseUser=null;
        //如果redis中有，则直接访问redis
        if (redisUtil.hasKey(key)){
            System.out.println("访问redis");
            String ejson=redisUtil.get(key).toString();
            enterpriseUser=JSON.parseObject(ejson,EnterpriseUser.class);
        }else {
            //如果redis中没有，就从数据库中查
            System.out.println("访问db");
            enterpriseUser=enterpriseUserMapper.findCompanyByid(id);
            List<Area>areas=areaMapper.findAreaByCompanyid(id);
            List<ExtendProperty>extendProperties=extendMapper.findPropertyByCompanyid(id);
            List<Pic>companyPic=picMapper.findCompanyPicByid(id);
            enterpriseUser.setImgUrl(companyPic);
            enterpriseUser.setExtendProperties(extendProperties);
            enterpriseUser.setAreas(areas);
        }
        return enterpriseUser;
    }

    //查询全部的装修公司，存进redis
    @Override
    public List<EnterpriseUser> findCompanyAll() {
        List<EnterpriseUser>enterpriseUsers=enterpriseUserMapper.findCompanyAll();
        for (EnterpriseUser e:enterpriseUsers){
            String key="EnterpriseUser-"+e.getId();
            List<Area>areas=areaMapper.findAreaByCompanyid(e.getId());
            List<ExtendProperty>extendProperties=extendMapper.findPropertyByCompanyid(e.getId());
            List<Pic>companyPic=picMapper.findCompanyPicByid(e.getId());
            e.setImgUrl(companyPic);
            e.setAreas(areas);
            e.setExtendProperties(extendProperties);
            //将装修公司的信息存入redis中一小时
            redisUtil.set(key, JSON.toJSONString(e),60*60);
        }
        return enterpriseUsers;
    }
}
