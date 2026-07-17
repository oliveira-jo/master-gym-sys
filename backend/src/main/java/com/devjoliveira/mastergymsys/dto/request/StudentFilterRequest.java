package com.devjoliveira.mastergymsys.dto.request;

public record StudentFilterRequest(
        String name,
        String email,
        String phone,
        String city,
        String state) {

}
