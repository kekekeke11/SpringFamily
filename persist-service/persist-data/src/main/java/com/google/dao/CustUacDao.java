package com.google.dao;

import com.google.entity.CustUac;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustUacDao extends CrudRepository<CustUac, Long> {

    @Query(value = "from CustUac where status=0")
    List<CustUac> listCustUac();
}