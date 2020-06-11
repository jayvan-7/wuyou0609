package com.zb.contrller;

import com.zb.service.RecordHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王效全
 * @Description TODO
 * @Date 2020/6/11
 * @Version V1.0
 */

@RestController
public class RecordHisController {
    @Autowired(required = false)
    private RecordHisService recordHisService;
}
