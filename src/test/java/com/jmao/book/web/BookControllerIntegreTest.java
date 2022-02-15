package com.jmao.book.web;

/*
* 통합테스트(모든 Bean들을 똑같이 Ioc올리고 테스트 하는것)
* SpringBootTest.WebEnvironment.MOCK 실제 톰켓을 올리는게아니라 다른톰캣을 올림
* SpringBootTest.WebEnvironment.RANDOM_PORT 실제 톰켓올림
* @AutoConfigureMockMvc MockMvc를 IoC에 등록해줌
* @Transactional은 각각의 테스트함수가 종료될때마다 트랜젝션을 롤백해주는 어노테이션
*/


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmao.book.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class BookControllerIntegreTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void save_테스트() throws Exception {
        //given(테스트를 하기 위한 준비)
        Book book = new Book(null,"스프링 따라하기", "쏘갈");
        String content = new ObjectMapper().writeValueAsString(book);

        //when (테스트실행)
        ResultActions resultActions = mockMvc.perform(post("/book")
                .contentType(APPLICATION_JSON_UTF8)
                .content(content)
                .accept(APPLICATION_JSON_UTF8));

        //then(검증)
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("스프링 따라하기"))
                .andDo(MockMvcResultHandlers.print());

    }

}
