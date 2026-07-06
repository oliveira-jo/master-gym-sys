import { GraduationResponse } from "../response/graduation-response.model";
import { ModalityResponse } from "../response/modality-response.model";
import { SubscriptionResponse } from "../response/subscription-response.model";

export interface EnrollmentModalityRequest {
  modalityId?: number | null | undefined;
  graduationId?: number | null | undefined;
  subscriptionId?: number | null | undefined;
  startDate: string;
  endDate: string;
}