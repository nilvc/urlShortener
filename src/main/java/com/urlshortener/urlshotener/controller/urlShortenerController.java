package com.urlshortener.urlshotener.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class urlShortenerController {

    @GetMapping("/getURL/{shortURL}")
    public String getlongURL(@PathVariable String shortURL){
        String longURL = "This is a long URL for "+ shortURL+ " that was stored in DB";
        return longURL;
    }

    @PostMapping(value = "/createShortURL",consumes = "text/plain")
    public String creatShortURL(@RequestBody String longURL ){
        return "Created a short url for url - "+longURL;
    }
}
