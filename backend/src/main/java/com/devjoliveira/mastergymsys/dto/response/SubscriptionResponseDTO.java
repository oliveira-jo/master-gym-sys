package com.devjoliveira.mastergymsys.dto.response;

import com.devjoliveira.mastergymsys.domain.Subscription;

public record SubscriptionResponseDTO(
        Long id,
        String name,
        ModalityResponseDTO modality,
        String price,
        boolean active) {

    public SubscriptionResponseDTO(Subscription subscription) {
        this(
                subscription.getId(),
                subscription.getName(),
                new ModalityResponseDTO(subscription.getModality()),
                subscription.getPrice() != null ? subscription.getPrice().toString() : null,
                subscription.isActive());
    }

}
