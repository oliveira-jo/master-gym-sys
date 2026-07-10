package com.devjoliveira.mastergymsys.dto;

public record UserFilterRequest(
    String name,
    String email,
    String phone,
    String city,
    String state) {

}
