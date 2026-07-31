package com.devjoliveira.mastergymsys.projection;

import java.math.BigDecimal;

public interface PaymentsByStatusProjection {
  String getStatus();

  Long getQuantity();

  BigDecimal getTotal();
}
