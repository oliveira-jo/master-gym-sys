import { ModalityResponse } from "../response/modality-response.model";

export interface SubscriptionRequest {
  name: string | null;
  modalityId: number | null;
  price: number | null;
}