import { PageResponse } from "../page/page-response.model";
import { EnrollmentSummaryResponse } from "./enrollment-summary-response.model";
import { PaymentResponse } from "./payment-response-model";

export interface PaymentHistoryResponse {

  enrollment: EnrollmentSummaryResponse;

  payments: PageResponse<PaymentResponse>;
}