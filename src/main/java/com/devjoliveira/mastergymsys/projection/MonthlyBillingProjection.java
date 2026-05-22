package com.devjoliveira.mastergymsys.projection;

import java.math.BigDecimal;

public interface MonthlyBillingProjection {

  String getMonth();

  BigDecimal getTotal();

}
