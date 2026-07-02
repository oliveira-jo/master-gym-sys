export interface EnrollmentRequest {
  enrollmentDate: string; // dd/MM/yyyy
  dueDay: number;
  closingDate: string; // dd/MM/yyyy
  studentId: number | null;
}