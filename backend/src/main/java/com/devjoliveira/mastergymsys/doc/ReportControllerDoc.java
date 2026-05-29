package com.devjoliveira.mastergymsys.doc;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.devjoliveira.mastergymsys.projection.MonthlyBillingProjection;
import com.devjoliveira.mastergymsys.projection.OutstandingInvoicesProjection;
import com.devjoliveira.mastergymsys.projection.StudentsByCityProjection;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

// @formatter:off

@Tag(name = "Reports", description = "This Reports is to presente a gym financial report")
public interface ReportControllerDoc {
  
  // Monthly Billing
  @Operation(
          summary = "Monthly Billing Report",
          description = "List monthly billing projections for all students",
          responses = {
                  @ApiResponse(responseCode = "200", description = "List of monthlyt billing projections"),
                  @ApiResponse(responseCode = "400", description = "Invalid filter parameters")
          })
  ResponseEntity<List<MonthlyBillingProjection>> monthlyBilling();

  // Students By City
  @Operation(
          summary = "Students By City Report",
          description = "List of Students by city",
          responses = {
                  @ApiResponse(responseCode = "200", description = "List of students by city"),
          })
  ResponseEntity<List<StudentsByCityProjection>> studentsByCity();

  // Outstanding Invoices
  @Operation(
          summary = "Outstanding Invoices Report",
          description = "List of outstanding invoices for all students",
          responses = {
                  @ApiResponse(responseCode = "200", description = "List of outstanding invoices"),
          })
  ResponseEntity<List<OutstandingInvoicesProjection>> outstandingInvoices();


}

// @formatter:on