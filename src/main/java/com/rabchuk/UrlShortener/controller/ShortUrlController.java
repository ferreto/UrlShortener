package com.rabchuk.UrlShortener.controller;

import com.rabchuk.UrlShortener.dto.ShortURLDto;
import com.rabchuk.UrlShortener.service.ShortUrlService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

/**
 * 
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class ShortUrlController {

	private ShortUrlService shortenerService;
	@Autowired
	public ShortUrlController(ShortUrlService shortenerService) {
		this.shortenerService = shortenerService;
	}

	@PostMapping(value = "/urls")
	public ResponseEntity<ShortURLDto> create(@RequestBody ShortURLDto shortURLDto) {
		shortURLDto = shortenerService.create(shortURLDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(shortURLDto);
	}

	@GetMapping("/{id}")
	public void redirectToAnotherUrl(@PathVariable(name = "id") String id, HttpServletResponse response) throws IOException {
		ShortURLDto shortURLDto = shortenerService.getUrlById(id);
		log.info("Redirect to " + shortURLDto.getLongURL());
		response.sendRedirect(shortURLDto.getLongURL());
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteUrlById(@PathVariable(name = "id") String id) {
		shortenerService.deleteUrlById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/expired")
	public ResponseEntity<?> deleteExpiredUrls() {
		shortenerService.deleteExpiredUrls();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
