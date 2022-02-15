package com.jmao.book.web;

//단위테스트(Controller, Filter, ControllerAdvice)

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmao.book.domain.Book;
import com.jmao.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(BookController.class)
public class BookControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean //IoC환경에 bean등록됨
    private BookService bookService;

    //BDDmokito
    @Test
    public void save_테스트() throws Exception {
      //given(테스트를 하기 위한 준비)
        Book book = new Book(null,"스프링 따라하기", "쏘갈");


        String content = new ObjectMapper().writeValueAsString(book);
        when(bookService.저장하기(book)).thenReturn(new Book(1L,"스프링 따라하기", "쏘갈"));

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
