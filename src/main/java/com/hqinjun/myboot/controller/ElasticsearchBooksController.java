package com.hqinjun.myboot.controller;

import com.hqinjun.myboot.domain.es.Books;
import com.hqinjun.myboot.repository.es.BooksEsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/es")
public class ElasticsearchBooksController {

    @Autowired
    BooksEsRepository booksEsRepository;

    @GetMapping("save/{id}")
    public void save(@PathVariable long id){

        Books books = new Books();
        books.setId(id);
        books.setAuthor("hqinjun");
        books.setChapter(1L);
        books.setContent("这是一本修仙小说");
        books.setTitle("开始修仙");
        booksEsRepository.save(books);
    }

}
