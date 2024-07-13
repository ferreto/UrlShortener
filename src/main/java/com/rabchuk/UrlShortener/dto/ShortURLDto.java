/**
 * 
 */
package com.rabchuk.UrlShortener.dto;

import lombok.Data;

/**
 * 
 */
@Data
public class ShortURLDto {
	private String urlId;
	private String longURL;
	private Long ttl;
}
