package com.delivery.project.app.api.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public final class LocationBulder {
    public static URI create(Object id){
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id) // substitui {id}
                .toUri();
    }
}
