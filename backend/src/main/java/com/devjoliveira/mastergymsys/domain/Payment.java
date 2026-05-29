package com.devjoliveira.mastergymsys.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.devjoliveira.mastergymsys.domain.enums.StatusPayment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "due_date")
  private LocalDate dueDate;

  private BigDecimal amount;

  @Column(name = "payment_date")
  private LocalDateTime paymentDate;

  @Column(name = "canceled_date")
  private LocalDate canceledDate;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private StatusPayment status = StatusPayment.OPEN;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "enrollment_id")
  private Enrollment enrollment;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public LocalDateTime getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(LocalDateTime paymentDate) {
    this.paymentDate = paymentDate;
  }

  public LocalDate getCanceledDate() {
    return canceledDate;
  }

  public void setCanceledDate(LocalDate canceledDate) {
    this.canceledDate = canceledDate;
  }

  public StatusPayment getStatus() {
    return status;
  }

  public void setStatus(StatusPayment status) {
    this.status = status;
  }

  public Enrollment getEnrollment() {
    return enrollment;
  }

  public void setEnrollment(Enrollment enrollment) {
    this.enrollment = enrollment;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Payment other = (Payment) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
