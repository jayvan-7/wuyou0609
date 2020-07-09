package com.zb.fegin.imple;

import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.fegin.IndividualUserFeigin;
import org.springframework.stereotype.Component;

/**
 * @USER : LQY
 * @DATE : 2020/6/17 17:34
 */
@Component
public class IndividualUserFeiginImple implements IndividualUserFeigin {


    @Override
    public Dto userInfo(String token) {


        return DtoUtil.returnFail("调用用户模块错误","110");
    }
}
