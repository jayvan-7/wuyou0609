package com.zb.service.impl;

import com.zb.mapper.PowerMapper;
import com.zb.pojo.Power;
import com.zb.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @USER : LQY
 * @DATE : 2020/6/15 17:38
 */
@Service
public class PowerServiceImple implements PowerService {
    @Autowired
    private PowerMapper powerMapper;

    @Override
    public List<Power> getPowerAll(Integer id) {
        try {
            return powerMapper.getPowerAll(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

}
