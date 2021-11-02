package com.su.cvsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Business rules layer
@Service
public class CvService {

    @Autowired
    private CvRepository cvRepository;

    public List<Cv> searchContent(String content){
    	return cvRepository.searchContent(content);
    }
    
    public List<Cv> searchFilename(String filename) {
        return cvRepository.searchFilename(filename);
    }
	
    public Cv save(Cv cv) {
        return cvRepository.save(cv);
    }
    
    public long count() {
        return cvRepository.count();
    }
    
}
