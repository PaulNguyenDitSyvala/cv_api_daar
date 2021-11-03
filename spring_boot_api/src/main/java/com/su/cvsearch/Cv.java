package com.su.cvsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;


// model layer
@Document(indexName = "cv")
public class Cv {

    @Id
    private String filename;
    private String content;
    
    public Cv(String filename, String content){
    	this.filename = filename;
    	this.content = content;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    public String toString(){
    	return "{" + this.filename + " - " + this.content + "}";
    }
}
