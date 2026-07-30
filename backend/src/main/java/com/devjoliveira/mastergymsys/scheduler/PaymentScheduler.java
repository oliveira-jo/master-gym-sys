package com.devjoliveira.mastergymsys.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.mastergymsys.service.PaymentService;

@Component
public class PaymentScheduler {

  private final PaymentService paymentService;

  public PaymentScheduler(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  /**
   * Exec all days at 00:05
   * how we use transaction don't need to save, jpa manage this
   */
  // For Test
  @Scheduled(fixedRate = 10000)
  // @Scheduled(cron = "0 5 0 * * *")
  @Transactional
  public void updateOverduePayments() {
    paymentService.updateOverduePayments();
  }

}