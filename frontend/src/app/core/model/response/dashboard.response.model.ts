
export interface Dashboard {
  totalStudents: number;
  activeEnrollments: number;
  monthlyBilling: number;
  openAmount: number;
  overdueAmount: number;
  overduePayments: number;
  monthlyBillingHistory: MonthlyBilling[];
}

export interface OutstandingInvoices {
  enrollmentId: number;
  studentName: string;
  dueDate: string;
  amount: number;
}

export interface MonthlyBilling {
  month: string;
  total: number;
}

export interface StudentsByCity {
  city: number;
  quantity: number;
}


export interface StudentsByModality {
  modalityName: string;
  quantity: number;
}

export interface EnrollmentsByStatus {
  status: string;
  quantity: number;
}

export interface PaymentsByStatus {
  status: string;
  quantity: number;
  total: number;
}

export interface NewStudentsByMonth {
  month: string;
  quantity: number;
}

export interface ExpiringEnrollment {
  enrollmentId: number;
  studentName: string;
  closingDate: string;
}
