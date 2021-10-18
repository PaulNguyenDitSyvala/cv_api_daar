package com.su.cvsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Business rules layer
@Service
public class SearchService {

    @Autowired
    private CvRepository cvRepository;
    
    public List<Cv> searchCvId(String id) {
        return cvRepository.search(id);
    }
    
    public List<Cv> searchCvContent(String content){
    	return cvRepository.searchContent(content);
    }
    
    public List<Cv> searchCvContentContains(String content){
    	return cvRepository.searchContentContains(content);
    }
	
    public Cv save(Cv cv) {
        return cvRepository.save(cv);
    }
    
    public long count() {
        return cvRepository.count();
    }
    
}
