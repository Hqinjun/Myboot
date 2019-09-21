package com.hqinjun.myboot.domain.es;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "my_es_books", type = "txt")
public class Books {

    //id
    @Id
    @Field(type = FieldType.Long, store = true)
    private Long id;

    //标题
    @Field(type = FieldType.Text, store = true, analyzer = "ik_smart")
    private String title;

    //id
    @Field(type = FieldType.Long, store = true)
    private Long chapter;

    //作者
    @Field(type = FieldType.Text, store = true, analyzer = "ik_smart")
    private String author;

    //内容
    @Field(type = FieldType.Text, store = true, analyzer = "ik_smart")
               private String content;




}
