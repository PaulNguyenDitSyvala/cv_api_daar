package com.su.cvsearch;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

// data access
@Repository
public interface CvRepository extends ElasticsearchRepository<Cv, String> {
    //@Query("{\"bool\": {\"must\": [{\"fuzzy\": {\"title\": \"?0\"}}]}}")
    @Query("{\"bool\": {\"must\": [{\"term\": {\"id\": \"?0\"}}]}}")
    List<Cv> search(String id);
    
    @Query("{\"bool\": {\"must\": [{\"term\": {\"content\": \"?0\"}}]}}")    
    List<Cv> searchContent(String content);
    
    @Query("{\"bool\": {\"must\": [{\"match\": {\"content\": \"?0\"}}]}}")    
    List<Cv> searchContentContains(String content);    
    
    Cv save(Cv cv);
    
    long count();
}


