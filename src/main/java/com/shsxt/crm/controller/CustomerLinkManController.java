package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.models.MessageModel;
import com.shsxt.crm.query.LinkManQuery;
import com.shsxt.crm.service.LinkManService;
import com.shsxt.crm.vo.LinkMan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("linkMan")
public class CustomerLinkManController extends BaseController {


    @Resource
    private LinkManService linkManService;

    @RequestMapping("query")
    @ResponseBody
    public Map<String, Object> query(LinkManQuery linkManQuery) {
        return linkManService.query(linkManQuery);
    }

    @RequestMapping("insert")
    @ResponseBody
    public MessageModel insert(LinkMan linkMan) {
        linkManService.insert(linkMan);
        return success("添加联系人成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public MessageModel update(LinkMan linkMan) {
        linkManService.update(linkMan);
        return success("修改联系人成功");
    }
    @RequestMapping("delete")
    @ResponseBody
    public MessageModel delete(Integer[] ids) {
        linkManService.delete(ids);
        return success("修改联系人成功");
    }
}
