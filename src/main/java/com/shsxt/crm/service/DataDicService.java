package com.shsxt.crm.service;

import com.shsxt.crm.dao.DataDicDao;
import com.shsxt.crm.dto.CusLevel;
import com.shsxt.crm.dto.ServeType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataDicService {

    @Resource
    private DataDicDao dataDicDao;

    public List<CusLevel> queryCusLevel() {
        return dataDicDao.queryAllLevel();
    }

    public List<ServeType> queryServeType() {
        return dataDicDao.queryAllServe();
    }
}
