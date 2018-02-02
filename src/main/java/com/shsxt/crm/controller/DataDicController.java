package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.dto.ServeType;
import com.shsxt.crm.service.DataDicService;
import com.shsxt.crm.dto.CusLevel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("data_dic")
public class DataDicController extends BaseController {

    @Resource
    private DataDicService dataDicService;

    @RequestMapping("queryCusLevel")
    @ResponseBody
    public List<CusLevel> queryCusLevel() {
        return dataDicService.queryCusLevel();
    }

    @RequestMapping("#queryServeType")
    @ResponseBody
    public List<ServeType> queryServeType(){
        return dataDicService.queryServeType();
    }
}
