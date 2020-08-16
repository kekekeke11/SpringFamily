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

    /**
     * 登录
     *
     * @param mobile
     * @param pwd
     * @return
     */
    public CustUac getCustUacByMobileAndPwd(String mobile, String pwd) {
        return custUacDao.getCustUacByMobileAndPwd(mobile, pwd);
    }

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

    public List<CustUac> listCustUacNotLoginUac(Long uacId) {
        return custUacDao.listCustUacNotLoginUac(uacId);
    }
}
