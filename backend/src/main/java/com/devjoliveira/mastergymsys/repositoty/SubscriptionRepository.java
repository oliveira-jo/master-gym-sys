package com.devjoliveira.mastergymsys.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devjoliveira.mastergymsys.domain.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}
