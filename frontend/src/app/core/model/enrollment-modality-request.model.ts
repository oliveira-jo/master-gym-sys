import { GraduationResponse } from "./graduation-response.model";
import { ModalityResponse } from "./modality-response.model";
import { SubscriptionResponse } from "./subscription-response.model";

export interface EnrollmentModalityRequest {
  modalityId?: number | null | undefined;
  graduationId?: number | null | undefined;
  subscriptionId?: number | null | undefined;
  startDate: string;
  endDate: string;
}