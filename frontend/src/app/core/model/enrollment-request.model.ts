import { EnrollmentModalityRequest } from "./enrollment-modality-request.model";

export interface EnrollmentRequest {
  enrollmentDate: string; // dd/MM/yyyy
  closingDate: string; // dd/MM/yyyy
  dueDay: number | null;
  studentId: number | null;
  enrollmentModalities: EnrollmentModalityRequest[];
}