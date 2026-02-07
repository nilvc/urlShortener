package com.urlshortener.urlshotener.controller;

import com.urlshortener.urlshotener.Entites.URLRecord;
import com.urlshortener.urlshotener.Service.URLShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
public class urlShortenerController {

    private final URLShortenerService urlShortenerService;
    private final String BASE62_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final int BASE = BASE62_CHARS.length();

    public urlShortenerController(URLShortenerService urlShortenerService){
        this.urlShortenerService = urlShortenerService;
    }

    private String base62Encode(long id){
        if (id == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            sb.insert(0, BASE62_CHARS.charAt((int) (id % BASE)));
            id /= BASE;
        }
        return sb.toString();
    }

    private long base62Decoding(String shortURL){
        long id = 0;
        long power = 1;
        for (int i = shortURL.length() - 1; i >= 0; i--) {
            int digit = BASE62_CHARS.indexOf(shortURL.charAt(i));
            if (digit < 0) {
                throw new IllegalArgumentException("Invalid Base62 character: " + shortURL.charAt(i));
            }
            id += digit * power;
            power *= BASE;
        }
        return id;
    }

    @GetMapping("/getURL/{shortURL}")
    public ResponseEntity<String> getlongURL(@PathVariable String shortURL){
        long id = base62Decoding(shortURL);
        String longURL = urlShortenerService.getLongURL(id);
        if(longURL.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT)
                .location(URI.create(longURL))
                .build();
    }

    @PostMapping(value = "/createShortURL",consumes = "text/plain")
    public ResponseEntity<String> creatShortURL(@RequestBody String longURL ){
        try{

            Optional<URLRecord> urlRecord = Optional.ofNullable(urlShortenerService.saveURLRecord(longURL));
            if (urlRecord.isPresent()){
                long id = urlRecord.get().getId();
                String shortURL = base62Encode(id);
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
