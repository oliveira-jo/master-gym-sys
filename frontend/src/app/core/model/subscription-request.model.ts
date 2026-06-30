import { ModalityResponse } from "./modality-response.model";

export interface SubscriptionRequest {
  name: string;
  modalityName: string;
  price: number;
}