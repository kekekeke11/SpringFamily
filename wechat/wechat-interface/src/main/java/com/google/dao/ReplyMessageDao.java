package com.google.dao;

import com.google.entity.ReplyMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyMessageDao extends JpaRepository<ReplyMessage, Long> {

    ReplyMessage findByKeyword(String keyword);
}
