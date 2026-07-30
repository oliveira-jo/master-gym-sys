
export interface Dashboard {
  totalStudents: number;
  activeEnrollments: number;
  monthlyBilling: number;
  openAmount: number;
  overdueAmount: number;
  overduePayments: number;
  monthlyBillingHistory: MonthlyBilling[];
}

export interface MonthlyBilling {
  month: string;
  total: number;
}
