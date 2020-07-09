package com.zb.mapper;

import com.zb.entity.WorkteamBuilder;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/19
 * @Version V1.0
 */
public interface WorkteamMapper {

    public int findTeamCount(Integer companyid);

    public List<WorkteamBuilder>findBuilderFive(Integer companyid);

}
