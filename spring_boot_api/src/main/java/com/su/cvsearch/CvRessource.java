package com.su.cvsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;



import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;

// rest Api layer
@CrossOrigin(origins = "http://localhost:6058/", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CvRessource {


    private static final Logger LOG = Logger.getLogger(CvRessource.class.getName());

    
    @Autowired
    private CvService cvService;
    
//    @CrossOrigin(origins = "http://localhost:6058/", allowedHeaders = "*", maxAge = 3600)
    @RequestMapping(value = "/cv", method = RequestMethod.POST)
    public ResponseEntity<Cv> save(@RequestPart MultipartFile cv) {
        String filename = cv.getOriginalFilename();

        String logMessage = "testing PDF reception - " + new Date() + "\n" + filename;
        LOG.log(Level.INFO, logMessage);

    	Path p = Paths.get("../src/server/data/cv/"+filename);
    	try{
    		cv.transferTo(p);
    	}
    	catch(IOException e){
    		String ErrorMessage = "ERROR - " + new Date() + "\n" + e.toString();
        	LOG.log(Level.INFO, ErrorMessage);
    	}
	
		

        ResponseEntity entity = ResponseEntity.ok(null);
//        ResponseEntity entity = ResponseEntity.ok(cvService.save(newCv));

        String response = "testing response - " + new Date() + "\n" + entity.toString();
        LOG.log(Level.INFO, response);
        return entity; 
    }
    
  //  @CrossOrigin(origins = "http://localhost:6058/", allowedHeaders = "*", maxAge = 3600)
    @RequestMapping(value = "/search/*", method = RequestMethod.GET)
    public ResponseEntity<List<Cv>> searchContent(@RequestParam("tags") String tags) {
        String log_tags = "testing search tags - " + new Date() + " - " + tags;
        LOG.log(Level.INFO, log_tags);
	

        ResponseEntity entity = ResponseEntity.ok(cvService.searchContent(tags));
        
        String response = "testing search results - " + new Date() + "\n" + entity.toString();
        LOG.log(Level.INFO, response);
        return entity;
    }
        
    
    @RequestMapping("/search_filename")
    public ResponseEntity<List<Cv>> searchFilename(@RequestParam("filename") String filename) {
    	return ResponseEntity.ok(cvService.searchFilename(filename));
    }
        
    @RequestMapping(value = "/cv", method = RequestMethod.GET)
    public ResponseEntity<Long> count() {
    	String response = "Using count ressource - " + new Date();
		LOG.log(Level.INFO, response);
        
        try {
			throw new Exception("Exception has occured....");
		} catch (Exception e) {
            LOG.error(e);
		    StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw);
		    e.printStackTrace(pw);
		    String stackTrace = sw.toString();
		    LOG.error("Exception - " + stackTrace);
		}
        return ResponseEntity.ok(cvService.count());
    }
}

