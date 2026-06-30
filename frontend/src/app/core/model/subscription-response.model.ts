import { ModalityResponse } from "./modality-response.model";

export interface SubscriptionResponse {
  id: number;
  name: string;
  modality: ModalityResponse | null;
  price: number;
  active: boolean;
}