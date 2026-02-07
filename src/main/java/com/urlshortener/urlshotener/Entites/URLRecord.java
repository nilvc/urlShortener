package com.urlshortener.urlshotener.Entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Entity(name = "Records")
@RequiredArgsConstructor
@Data
@NoArgsConstructor(force = true)
public class URLRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or GenerationType.AUTO
    private long id;

    @NonNull
    private final String longURL;
}
