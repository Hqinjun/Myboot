package com.hqinjun.myboot.repository.es;

import com.hqinjun.myboot.domain.es.Books;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksEsRepository  extends ElasticsearchRepository<Books, Long> {
}
