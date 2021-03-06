package com.zb.mapper;

import com.zb.entity.Pic;
import io.swagger.models.auth.In;

import javax.crypto.interfaces.PBEKey;
import java.security.PublicKey;
import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/6/10
 * @Version V1.0
 */
public interface PicMapper {

    //根据效果图id查对应包含的所有图片
    public List<Pic>findPicByDesignId(Integer targetid);

    //根据公司id查公司对应的所有图片
    public List<Pic>findCompanyPicByid(Integer targetid);
}
