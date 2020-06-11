package com.zb.service.impl;

import com.zb.entity.Area;
import com.zb.entity.EnterpriseUser;
import com.zb.entity.ExtendProperty;
import com.zb.mapper.AreaMapper;
import com.zb.mapper.EnterpriseUserMapper;
import com.zb.mapper.ExtendMapper;
import com.zb.service.EnterpriseUserService;
import com.zb.util.PageUtil;
import com.zb.vo.CompanyPageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //把装修公司的服务区域、特色封装进去
        for(EnterpriseUser e:data){
            List<Area>areas=areaMapper.findAreaByCompanyid(e.getId());
            List<ExtendProperty>extendProperties=extendMapper.findPropertyByCompanyid(e.getId());
            e.setAreas(areas);
            e.setExtendProperties(extendProperties);
        }
        pageUtil.setData(data);
        return pageUtil;
    }
}
