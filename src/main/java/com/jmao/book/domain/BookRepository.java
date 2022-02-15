package com.jmao.book.domain;

import org.springframework.data.jpa.repository.JpaRepository;

//@Repository 를 적어야 스프링 ioc에 빈이 등록이되는데
// JpaRepostitory 쓰면 그거 crud쓰면됨
public interface BookRepository extends JpaRepository<Book,Long> {
}
