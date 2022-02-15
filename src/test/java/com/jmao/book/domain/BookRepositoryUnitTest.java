package com.jmao.book.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

//단위 테스트 (DB관련된 Bean이 IoC에 등록되면됨)

@Transactional
@AutoConfigureTestDatabase(replace = Replace.ANY)//가짜디비로테스트 Replace.NONE은 실제디비
@DataJpaTest // Repository들을 다 IoC에 등록해줌
public class BookRepositoryUnitTest {

    @Autowired
    private BookRepository bookRepository;

}
