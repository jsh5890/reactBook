package com.jmao.book.service;

import com.jmao.book.domain.BookRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/*
* 단위 테스트 (서비스와 관련된 로직만 메모리에 띄우면 됨)
* BoardRepository => 가짜 객체로 만들 수 있음
*
* */

@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTest {

    @InjectMocks //BookService 객체가 만들어질때 BookServiceUnitTest파일에 @Mock로 등록된 모든 애들을 주입받는다.
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;


}
