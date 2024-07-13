package com.rabchuk.UrlShortener.converter;

import com.rabchuk.UrlShortener.dto.ShortURLDto;
import com.rabchuk.UrlShortener.entity.ShortURL;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component

public class UrlConverter implements Converter<ShortURL, ShortURLDto>{
    @Override
    public ShortURL toEntity(ShortURLDto shortURLDTO) {

        ShortURL url = null;

        if (shortURLDTO != null) {
            url = new ShortURL();

         //   url.setUrlId(shortURLDTO.getUrlId());
            url.setLongUrl(shortURLDTO.getLongURL());
            url.setTtl(shortURLDTO.getTtl());
            if (shortURLDTO.getUrlId() != null) {
                url.setUrlId(shortURLDTO.getUrlId());
            } else {
            //    String randomString = String.valueOf(UUID.randomUUID());
                int length = 10;
                boolean useLetters = true;
                boolean useNumbers = false;
                String randomString = RandomStringUtils.random(length, useLetters, useNumbers);
                url.setUrlId(randomString);
            }
        }
        return url;
    }

    @Override
    public ShortURLDto toDto(ShortURL shortURL) {

        ShortURLDto sUrlDto = null;

        if (shortURL != null) {
            sUrlDto = new ShortURLDto();

            sUrlDto.setUrlId(shortURL.getUrlId());
            sUrlDto.setLongURL(shortURL.getLongUrl());
            sUrlDto.setTtl(shortURL.getTtl());
        }
        return sUrlDto;
    }

}
