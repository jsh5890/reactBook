package com.jmao.book.service;

import com.jmao.book.domain.Book;
import com.jmao.book.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//서비스로 등록하면 기능을 정의할수있고,트랜잭션을 관리할수 있음
@Service
@RequiredArgsConstructor
public class BookService {

    // 함수 ==> 송금() 내부적으로 리파지토리에 여러개의 함수를 실행하고 전부다되면 커밋 안되면 롤백 (트랜잭션)
    private final BookRepository bookRepository;

    @Transactional
    public Book 저장하기(Book book){
        return bookRepository.save(book);
    }

    @Transactional(readOnly = true) //jpa에서는 변경감지라는 내부 기능이있음, update시의 정합성 유지,insert시 유령데이터현상 못막음
    public Book 한건가져오기(Long id){
        return bookRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("id 확인해주세요"));
    }
    @Transactional(readOnly = true)
    public List<Book> 모두가져오기(){
        return bookRepository.findAll();
    }
    @Transactional
    public Book 수정하기(Long id,Book book){
        Book bookEntity =  bookRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("id 확인해주세요")); //book 오브젝트 영속화
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        return bookEntity;
    }// 종료시 영속화 데이터 갱신(flush) => db에 커밋됨 ====> 더티체킹
    @Transactional
    public String 삭제하기(Long id){
        bookRepository.deleteById(id);
        return "ok";
    }
}
