package com.urlshortener.urlshotener.Service;

import com.urlshortener.urlshotener.Entites.URLRecord;
import com.urlshortener.urlshotener.Repository.urlShortenerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class URLShortenerService {
    private final urlShortenerRepository urlShortener;


    public URLShortenerService(urlShortenerRepository urlShortener){
        this.urlShortener = urlShortener;
    }

    public URLRecord saveURLRecord(String longURL){
        try {
            URLRecord record = new URLRecord(longURL);
            return this.urlShortener.save(record);
        }
        catch (Exception e){
            System.out.println("Error occurred - " + e.getMessage());
            return null;
        }

    }

    public String getLongURL(long id){
        try{
            Optional<URLRecord> record = this.urlShortener.findById(id);
            return record.map(URLRecord::getLongURL).orElse("");
        }
        catch (Exception e){
            System.out.println("Error occurred while retrieving long URL for id "+id);
            return "";
        }
    }
}
