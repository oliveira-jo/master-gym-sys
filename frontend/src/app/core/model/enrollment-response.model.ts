import { StudentResponse } from "./student-response.model";

export interface EnrollmentResponse {
  id: number;
  enrollmentDate: string;
  dueDay: number;
  closingDate: string;
  student: StudentResponse;
}