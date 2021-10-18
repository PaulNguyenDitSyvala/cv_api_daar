package com.su.cvsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// rest Api layer
@RestController
@RequestMapping("/api")
public class CvRessource {
    private int id_counter = 0;
    
    @Autowired
    private SearchService searchService;

    //@GetMapping
    @RequestMapping("/search")
    public ResponseEntity<List<Cv>> search(@RequestParam("id") String id) {
        return ResponseEntity.ok(searchService.searchCvId(id));
    }
    
    //@GetMapping
    @RequestMapping("/search_cont")
    public ResponseEntity<List<Cv>> searchContent(@RequestParam("content") String content) {
    	System.out.println(content);
        return ResponseEntity.ok(searchService.searchCvContent(content));
    }
    
    //@GetMapping
    @RequestMapping("/search_2")
    public ResponseEntity<List<Cv>> searchContentContains(@RequestParam("content") String content) {
        return ResponseEntity.ok(searchService.searchCvContentContains(content));
    }

    //@GetMapping
    @RequestMapping("/cvsave")
    public ResponseEntity<Cv> save(@RequestParam("id") String id, @RequestParam("content") String content) {
        Cv cv = new Cv(id, content);
        return ResponseEntity.ok(searchService.save(cv));
    }
    
    //@GetMapping
    @RequestMapping("/cvcount")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(searchService.count());
    }
    
    
}

