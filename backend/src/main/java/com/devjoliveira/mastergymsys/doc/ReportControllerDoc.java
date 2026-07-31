package com.devjoliveira.mastergymsys.doc;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.devjoliveira.mastergymsys.dto.response.DashboardResponseDTO;
import com.devjoliveira.mastergymsys.projection.EnrollmentsByStatusProjection;
import com.devjoliveira.mastergymsys.projection.ExpiringEnrollmentProjection;
import com.devjoliveira.mastergymsys.projection.MonthlyBillingProjection;
import com.devjoliveira.mastergymsys.projection.NewStudentsByMonthProjection;
import com.devjoliveira.mastergymsys.projection.OutstandingInvoicesProjection;
import com.devjoliveira.mastergymsys.projection.PaymentsByStatusProjection;
import com.devjoliveira.mastergymsys.projection.StudentsByCityProjection;
import com.devjoliveira.mastergymsys.projection.StudentsByModalityProjection;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

// @formatter:off
@Tag(name = "Reports", description = "Endpoints for dashboard data and gym management reports")
public interface ReportControllerDoc {

        // Dashboard
        @Operation(
                summary = "Get dashboard data",
                description = "Returns the consolidated data displayed on the gym management dashboard",
                responses = {
        @ApiResponse(
                responseCode = "200",
                description = "Dashboard data retrieved successfully"
        ),
        @ApiResponse(
                responseCode = "401",
                description = "User is not authenticated"
        ),
        @ApiResponse(
                responseCode = "403",
                description = "User does not have permission to access the dashboard"
        )}) 
        ResponseEntity<DashboardResponseDTO> getDashboard();

        // Monthly Billing
        @Operation(
                summary = "Get monthly billing report",
                description = "Returns the monthly billing totals for the gym",
                responses = {
        @ApiResponse(
                responseCode = "200",
                description = "Monthly billing report retrieved successfully"
        ),
        @ApiResponse(
                responseCode = "401",
                description = "User is not authenticated"
        ),
        @ApiResponse(
                responseCode = "403",
                description = "User does not have permission to access the report"
        )}) 
        ResponseEntity<List<MonthlyBillingProjection>> monthlyBilling();

        // Students By City
        @Operation(
                summary = "Get students by city report",
                description = "Returns the number of registered students grouped by city",
                responses = {
        @ApiResponse(
                responseCode = "200",
                description = "Students by city report retrieved successfully"
        ),
        @ApiResponse(
                responseCode = "401",
                description = "User is not authenticated"
        ),
        @ApiResponse(
                responseCode = "403",
                description = "User does not have permission to access the report"
        )}) 
        ResponseEntity<List<StudentsByCityProjection>> studentsByCity();

        // Outstanding Invoices
        @Operation(
                summary = "Get outstanding invoices report",
                description = "Returns the outstanding invoices and their respective amounts",
                responses = {
        @ApiResponse(
                responseCode = "200",
                description = "Outstanding invoices report retrieved successfully"
        ),
        @ApiResponse(
                responseCode = "401",
                description = "User is not authenticated"
        ),
        @ApiResponse(
                responseCode = "403",
                description = "User does not have permission to access the report"
        )}) 
        ResponseEntity<List<OutstandingInvoicesProjection>> outstandingInvoices();

        // Students By Modality
        @Operation(
                summary = "Get students by modality report",
                description = "Returns the number of students grouped by enrolled modality",
                responses = {
        @ApiResponse(
                responseCode = "200",
                description = "Students by modality report retrieved successfully"
        ),
        @ApiResponse(
                responseCode = "401",
                description = "User is not authenticated"
        ),
        @ApiResponse(
                responseCode = "403",
                description = "User does not have permission to access the report"
        )}) 
        ResponseEntity<List<StudentsByModalityProjection>> studentsByModality();

        // Enrollments By Status
        @Operation(
                summary = "Get enrollments by status report",
                description = "Returns the number of enrollments grouped by status",
                responses = {
        @ApiResponse(
                responseCode = "200",
                description = "Enrollments by status report retrieved successfully"
        ),
        @ApiResponse(
                responseCode = "401",
                description = "User is not authenticated"
        ),
        @ApiResponse(
                responseCode = "403",
                description = "User does not have permission to access the report"
        )}) 
        ResponseEntity<List<EnrollmentsByStatusProjection>> enrollmentsByStatus();

        // Payments By Status
        @Operation(
                summary = "Get payments by status report",
                description = "Returns the number of payments grouped by status",
                responses = {
        @ApiResponse(
                responseCode = "200",
                description = "Payments by status report retrieved successfully"
        ),
        @ApiResponse(
                responseCode = "401",
                description = "User is not authenticated"
        ),
        @ApiResponse(
                responseCode = "403",
                description = "User does not have permission to access the report"
        )}) 
        ResponseEntity<List<PaymentsByStatusProjection>> paymentsByStatus();

        // New Students By Month
        @Operation(
                summary = "Get new students by month report",
                description = "Returns the number of new students registered each month",
                responses = {
        @ApiResponse(
                responseCode = "200",
                description = "New students by month report retrieved successfully"
        ),
        @ApiResponse(
                responseCode = "401",
                description = "User is not authenticated"
        ),
        @ApiResponse(
                responseCode = "403",
                description = "User does not have permission to access the report"
        )}) 
        ResponseEntity<List<NewStudentsByMonthProjection>> newStudentsByMonth();

        // Expiring Enrollments
        @Operation(
                summary = "Get expiring enrollments",
                description = "Returns enrollments that will expire within the next 30 days",
                responses = {
        @ApiResponse(
                responseCode = "200",
                description = "Expiring enrollments retrieved successfully"
        ),
        @ApiResponse(
                responseCode = "401",
                description = "User is not authenticated"
        ),
        @ApiResponse(
                responseCode = "403",
                description = "User does not have permission to access the report"
        )}) 
        ResponseEntity<List<ExpiringEnrollmentProjection>> expiringEnrollments();

}

// @formatter:on
