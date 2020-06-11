package com.zb.mapper;

import com.zb.entity.Area;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/11
 * @Version V1.0
 */
public interface AreaMapper {

    //查询装修公司的服务区域
    public List<Area>findAreaByCompanyid(Integer companyid);
}
