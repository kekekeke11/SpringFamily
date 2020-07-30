package com.google.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * @Author YC
 * @create 2020/3/7
 */
@Entity
@Data
@Table(name = "T_REPLY_MESSAGE")
public class ReplyMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;

    private String text;

}
