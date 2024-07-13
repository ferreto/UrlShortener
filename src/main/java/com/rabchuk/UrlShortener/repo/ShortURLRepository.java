/**
 * 
 */
package com.rabchuk.UrlShortener.repo;

import com.rabchuk.UrlShortener.entity.ShortURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 
 */
@Repository
public interface ShortURLRepository extends JpaRepository<ShortURL, Long> {

   Optional<ShortURL> findByUrlId(String urlId);

   void deleteByUrlId(String urlId);

   @Modifying
   @Query(value = "DELETE FROM shorturl WHERE ttl IS NOT NULL AND created_on + ttl * INTERVAL '1 second' < CURRENT_TIMESTAMP;", nativeQuery = true)
   void deleteExpiredUrls();

}
