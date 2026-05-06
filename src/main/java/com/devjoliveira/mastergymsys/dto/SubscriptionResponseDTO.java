package com.devjoliveira.mastergymsys.dto;

import com.devjoliveira.mastergymsys.domain.Subscription;

public record SubscriptionResponseDTO(
        Long id,
        String name,
        String modalityName,
        String price,
        boolean active) {

    public SubscriptionResponseDTO(Subscription subscription) {
        this(
                subscription.getId(),
                subscription.getName(),
                subscription.getModality() != null ? subscription.getModality().getName() : null,
                subscription.getPrice() != null ? subscription.getPrice().toString() : null,
                subscription.isActive());
    }

}
