package com.devjoliveira.mastergymsys.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "enrollments_modalities")
public class EnrollmentModality {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "start_date")
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "enrollment_id")
  private Enrollment enrollment;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "modalitie_id")
  private Modality modality;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "graduation_id")
  private Graduation graduation;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "subscription_id")
  private Subscription subscription;

  @PrePersist
  public void prePersist() {
    if (startDate == null) {
      startDate = LocalDate.now();
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
