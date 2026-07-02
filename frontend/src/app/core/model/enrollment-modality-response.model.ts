import { GraduationResponse } from "./graduation-response.model";
import { ModalityResponse } from "./modality-response.model";
import { SubscriptionResponse } from "./subscription-response.model";

export interface EnrollmentModalityResponse {
  modality: ModalityResponse;
  graduation: GraduationResponse;
  subscription: SubscriptionResponse;
  startDate: string;
  endDate: string;
}