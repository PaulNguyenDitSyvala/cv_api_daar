package com.su.cvsearch;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

// data access
@Repository
public interface CvRepository extends ElasticsearchRepository<Cv, String> {
     
    @Query("{\"bool\": {\"must\": [{\"match\": {\"content\": \"?0\"}}]}}")    
    List<Cv> searchContent(String content);
    
    @Query("{\"bool\": {\"must\": [{\"term\": {\"filename\": \"?0\"}}]}}")
    List<Cv> searchFilename(String filename);
    
    Cv save(Cv cv);
    
    long count();
}


