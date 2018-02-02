package com.shsxt.crm.utils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.crm.query.LinkManQuery;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageUtil {

    public static Map getPagedMap(Object obj,List list){
        Class c = obj.getClass();
        Class sc = c.getSuperclass();
        Integer pageNum = 1;
        Integer pageRows = 10;
        try {
            Method getPage = sc.getDeclaredMethod("getPage");
            Method getRows = sc.getDeclaredMethod("getRows");
            pageNum = (Integer) getPage.invoke(obj);
            pageRows = (Integer)getRows.invoke(obj);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        PageHelper.startPage(pageNum,pageRows);
        PageInfo pi = new PageInfo(list);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("rows",pi.getList());
        map.put("total",pi.getTotal());
        return map;
    }

/*    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        LinkManQuery linkManQuery = new LinkManQuery();
        linkManQuery.setPage(1);
        Class<? extends LinkManQuery> c = linkManQuery.getClass();
        Class<?> sc = c.getSuperclass();
        Method getPage = sc.getDeclaredMethod("getPage");
        Integer page = (Integer)getPage.invoke(linkManQuery);
        System.out.println(page);
    }*/
}
