package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("customer_serve")
public class CustomerServeController extends BaseController {


    @RequestMapping("index/{type}")
    public String index(@PathVariable("type")Integer type,Integer id){
        switch(type){
            case 1:return "customer_serve_create";
            case 2:return "customer_serve_assign";
            case 3:return "customer_serve_proceed";
            case 4:return "customer_serve_feedback";
            case 5:return "customer_serve_archive";
            default:return "error";
        }
    }



}
