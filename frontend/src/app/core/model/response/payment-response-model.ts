import { EnrollmentSummaryResponse } from "./enrollment-summary-response.model";

export interface PaymentResponse {

  id: number,
  dueDate: '',
  amount: number,
  paymentAmount: number,
  paymentDate: '',
  canceledAt: '',
  status: '',
  observation: '',
  enrollmentId: number

}