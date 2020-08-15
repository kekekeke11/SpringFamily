package com.google.dao;

import com.google.entity.CustUac;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustUacDao extends CrudRepository<CustUac, Long> {

    @Query(value = "from CustUac where status=0")
    List<CustUac> listCustUac();

    @Query(value = "from CustUac where status=0 and wechat=:wechat")
    CustUac getCustUacByWechat(@Param("wechat") String wechat);

    @Query(value = "from CustUac where status=0 and bid=:bid")
    CustUac getCustUacByBid(@Param("bid") String bid);

    @Query(value = "from CustUac where status=0 and mobile=:mobile and password=:password")
    CustUac getCustUacByMobileAndPwd(@Param("mobile") String mobile, @Param("password") String password);

}
