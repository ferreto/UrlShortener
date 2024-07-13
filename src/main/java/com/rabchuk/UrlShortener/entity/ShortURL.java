/**
 *
 */
package com.rabchuk.UrlShortener.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 *
 */
@Entity
@Data
public class ShortURL {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String urlId;

    private String longUrl;

    private Long ttl;

    @CreationTimestamp
    private LocalDateTime createdOn;

}
