package com.devjoliveira.mastergymsys.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.mastergymsys.domain.Modality;
import com.devjoliveira.mastergymsys.domain.Subscription;
import com.devjoliveira.mastergymsys.domain.exception.BusinessException;
import com.devjoliveira.mastergymsys.dto.SubscriptionRequestDTO;
import com.devjoliveira.mastergymsys.dto.SubscriptionResponseDTO;
import com.devjoliveira.mastergymsys.repositoty.ModalityRepository;
import com.devjoliveira.mastergymsys.repositoty.SubscriptionRepository;

@Service
public class SubscriptionService {

  private final SubscriptionRepository subscriptionRepository;
  private final ModalityRepository modalityRepository;

  public SubscriptionService(SubscriptionRepository subscriptionRepository, ModalityRepository modalityRepository) {
    this.subscriptionRepository = subscriptionRepository;
    this.modalityRepository = modalityRepository;
  }

  @Transactional(readOnly = true)
  public Page<SubscriptionResponseDTO> findAll(@NonNull Pageable pageable) {
    return subscriptionRepository.findAll(pageable).map(SubscriptionResponseDTO::new);
  }

  @Transactional(readOnly = true)
  public SubscriptionResponseDTO findById(Long id) {
    return new SubscriptionResponseDTO(searchById(id));
  }

  @SuppressWarnings("null")
  @Transactional
  public SubscriptionResponseDTO save(SubscriptionRequestDTO subscriptionRequestDTO) {

    Long modalityId;
    try {
      modalityId = Long.valueOf(subscriptionRequestDTO.modalityId());
    } catch (NumberFormatException e) {
      throw new BusinessException("Invalid modality id: " + subscriptionRequestDTO.modalityId() + ":" + e);
    }

    Modality modalityFromDB = modalityRepository.findById(modalityId)
        .orElseThrow(() -> new BusinessException("Modality not found with id: " + modalityId));

    Subscription sub = new Subscription();
    sub.setName(subscriptionRequestDTO.name());
    sub.setPrice(subscriptionRequestDTO.price());
    sub.setModality(modalityFromDB);

    subscriptionRepository.save(sub);

    return new SubscriptionResponseDTO(sub);

  }

  @Transactional
  public SubscriptionResponseDTO update(Long id, SubscriptionRequestDTO subscriptionRequestDTO) {
    Subscription fromDB = searchById(id);

    fromDB.setName(subscriptionRequestDTO.name());
    fromDB.setPrice(subscriptionRequestDTO.price());

    return new SubscriptionResponseDTO(subscriptionRepository.save(fromDB));
  }

  @SuppressWarnings("null")
  @Transactional
  public void deleteById(Long id) {
    Subscription fromDB = searchById(id);

    try {
      subscriptionRepository.delete(fromDB);
    } catch (Exception e) {
      throw new BusinessException("Error deleting subscription with id: " + id + " : " + e);
    }
  }

  @SuppressWarnings("null")
  private Subscription searchById(Long id) {
    return subscriptionRepository.findById(id)
        .orElseThrow(() -> new BusinessException("Subscription not found with id: " + id));
  }

}
