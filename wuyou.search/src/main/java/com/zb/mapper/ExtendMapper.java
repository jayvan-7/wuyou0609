package com.zb.mapper;

import com.zb.entity.ExtendProperty;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/11
 * @Version V1.0
 */
public interface ExtendMapper {

    //查询装修公司的特色
    public List<ExtendProperty>findPropertyByCompanyid(Integer companyid);
}
