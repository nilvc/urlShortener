package com.urlshortener.urlshotener.Repository;

import com.urlshortener.urlshotener.Entites.URLRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface urlShortenerRepository extends JpaRepository<URLRecord , Long> {

}
