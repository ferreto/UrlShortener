package com.rabchuk.UrlShortener.converter;

// E - Entity
// D - Dto
public interface Converter<E, D> {
    E toEntity(D dto);
    D toDto(E entity);
}
