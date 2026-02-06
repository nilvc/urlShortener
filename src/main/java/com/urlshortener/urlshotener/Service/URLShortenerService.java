package com.urlshortener.urlshotener.Service;

import com.urlshortener.urlshotener.Entites.URLRecord;
import com.urlshortener.urlshotener.Repository.urlShortenerRepository;
import org.springframework.stereotype.Service;

@Service
public class URLShortenerService {
    private final urlShortenerRepository urlShortener;


    public URLShortenerService(urlShortenerRepository urlShortener){
        this.urlShortener = urlShortener;
    }

    public boolean saveURLRecord(String longURL, String shortURL){
        try {
            URLRecord record = new URLRecord(longURL,shortURL);
            this.urlShortener.save(record);
            return true;
        }
        catch (Exception e){
            System.out.println("Error occurred - " + e.getMessage());
            return false;
        }

    }
}
