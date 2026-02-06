package com.urlshortener.urlshotener.Entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Records")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class URLRecord {
    @Id
    private String longURL;
    private String shortURL;
}
