package com.devjoliveira.mastergymsys.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Table(name = "enrollments_modalities")
public class EnrollmentModality {

  @Column(name = "start_date")
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "enrollments_id")
  private Enrollment enrollment;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "modalities_id")
  private Modality modality;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "graduations_id")
  private Graduation graduation;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "subscriptions_id")
  private Subscription subscription;

  @PrePersist
  public void prePersist() {
    if (startDate == null) {
      startDate = LocalDate.now();
    }
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public Enrollment getEnrollment() {
    return enrollment;
  }

  public void setEnrollment(Enrollment enrollment) {
    this.enrollment = enrollment;
  }

  public Modality getModality() {
    return modality;
  }

  public void setModality(Modality modality) {
    this.modality = modality;
  }

  public Graduation getGraduation() {
    return graduation;
  }

  public void setGraduation(Graduation graduation) {
    this.graduation = graduation;
  }

  public Subscription getSubscription() {
    return subscription;
  }

  public void setSubscription(Subscription subscription) {
    this.subscription = subscription;
  }

}
