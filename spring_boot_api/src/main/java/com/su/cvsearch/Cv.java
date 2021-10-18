package com.su.cvsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;


// model layer
@Document(indexName = "cv")
public class Cv {

    @Id
    private String id;
    private String content;

    public Cv(String id, String content){
    	this.id = id;
    	this.content = content;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
