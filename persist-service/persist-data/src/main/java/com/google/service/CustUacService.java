package com.google.service;

import com.google.dao.CustUacDao;
import com.google.entity.CustUac;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wk
 * @Description:
 * @date 2020/7/28 10:50
 **/
@Service
public class CustUacService {

    @Autowired
    private CustUacDao custUacDao;

    public List<CustUac> listCustUac() {
        return custUacDao.listCustUac();
    }

    /**
     * uac信息保存
     *
     * @param custUac
     */
    public void saveCustUac(CustUac custUac) {
        custUacDao.save(custUac);
    }

    /**
     * 通过微信查询
     *
     * @param wechat
     * @return
     */
    public CustUac getCustUacByWeChat(String wechat) {
        return custUacDao.getCustUacByWechat(wechat);
    }

    public CustUac getCustUacByBid(String bid) {
        return custUacDao.getCustUacByBid(bid);
    }
}
