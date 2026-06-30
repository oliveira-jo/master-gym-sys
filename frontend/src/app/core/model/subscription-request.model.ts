import { ModalityResponse } from "./modality-response.model";

export interface SubscriptionRequest {
  name: string | null;
  modalityId: number | null;
  price: number | null;
}