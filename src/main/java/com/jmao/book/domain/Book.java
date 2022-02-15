package com.jmao.book.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //서버실행시 오브젝트 리렉션 매핑이 됨 (테이블이 h2디비에 생성됨)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//해당디비전략 따라감
    private Long id;

    private String title;
    private String author;

}
