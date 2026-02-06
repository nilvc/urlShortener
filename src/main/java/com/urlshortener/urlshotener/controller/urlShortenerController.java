package com.urlshortener.urlshotener.controller;

import com.urlshortener.urlshotener.Service.URLShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class urlShortenerController {

    private final URLShortenerService urlShortenerService;

    public urlShortenerController(URLShortenerService urlShortenerService){
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping("/getURL/{shortURL}")
    public ResponseEntity<String> getlongURL(@PathVariable String shortURL){
        String longURL = "This is a long URL for "+ shortURL+ " that was stored in DB";
        return new ResponseEntity<>(longURL, HttpStatus.TEMPORARY_REDIRECT);
    }

    @PostMapping(value = "/createShortURL",consumes = "text/plain")
    public ResponseEntity<String> creatShortURL(@RequestBody String longURL ){
        try{
            String shortURL = longURL.substring(0,5);
            if (urlShortenerService.saveURLRecord(longURL,shortURL)){
                return ResponseEntity.ok("Created a short url \"" + shortURL +"\" for long url - "+longURL);
            }
            else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
        catch (Exception e){
            System.out.println("Error " +
                    "occurred while creating a short URL");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
