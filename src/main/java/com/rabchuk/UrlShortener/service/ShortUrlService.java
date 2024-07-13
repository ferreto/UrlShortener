/**
 * 
 */
package com.rabchuk.UrlShortener.service;

import com.rabchuk.UrlShortener.converter.UrlConverter;
import com.rabchuk.UrlShortener.dto.ShortURLDto;
import com.rabchuk.UrlShortener.exception.ResourceDuplicatedException;
import com.rabchuk.UrlShortener.exception.ResourceNotFoundException;
import com.rabchuk.UrlShortener.repo.ShortURLRepository;
import com.rabchuk.UrlShortener.entity.ShortURL;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 
 */
@Service
@Slf4j
public class ShortUrlService {
	
	private final ShortURLRepository shortURLJPARepository;
	private final UrlConverter urlConverter;
	@Autowired
	public ShortUrlService(ShortURLRepository shortURLJPARepository, UrlConverter urlConverter) {
		this.shortURLJPARepository = shortURLJPARepository;
		this.urlConverter = urlConverter;
	}

	@Transactional
	public ShortURLDto create(ShortURLDto shortURLDto) {

		ShortURL shortURL = urlConverter.toEntity(shortURLDto);
		try {
			shortURLJPARepository.saveAndFlush(shortURL);
		} catch (DataIntegrityViolationException e) {
			throw new ResourceDuplicatedException("URL", "short URL id", shortURL.getUrlId());
		}
		log.info("Url with short URL-id >" + shortURL.getUrlId() + "< created successfully.");
		return urlConverter.toDto(shortURL);
	}


	public ShortURLDto getUrlById(String urlId) {
		Optional<ShortURL> optionalShortUrl = shortURLJPARepository.findByUrlId(urlId);
		if (!optionalShortUrl.isPresent()) {
			log.info("Url for short URL-id >" + urlId + "< not found.");
			throw new ResourceNotFoundException("URL", "short URL", urlId);
		}
		log.info("Url for short URL-id >" + urlId + "< found.");
		return urlConverter.toDto(optionalShortUrl.get());
	}

	@Transactional
	public void deleteUrlById(String urlId) {
		// Удаляемый URL должен существовать
		Optional<ShortURL> optionalShortUrl = shortURLJPARepository.findByUrlId(urlId);
		if (!optionalShortUrl.isPresent()) {
			log.info("Url for short URL-id >" + urlId + "< not found.");
			throw new ResourceNotFoundException("URL", "short URL", urlId);
		}
		shortURLJPARepository.deleteByUrlId(optionalShortUrl.get().getUrlId());
		log.info("Url for short URL-id >" + urlId + "< deleted.");
	}

	@Transactional
	public void deleteExpiredUrls() {
		shortURLJPARepository.deleteExpiredUrls();
		log.info("Expired URLs deleted.");
	}
}
