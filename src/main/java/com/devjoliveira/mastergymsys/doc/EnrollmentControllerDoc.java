package com.devjoliveira.mastergymsys.doc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.devjoliveira.mastergymsys.domain.exception.ErrorResponse;
import com.devjoliveira.mastergymsys.dto.EnrollmentRequestDTO;
import com.devjoliveira.mastergymsys.dto.EnrollmentResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

// @formatter:off

@Tag(name = "Enrollments", description = "Operations for registration, consultation, updating, deletion and filtering of subscriptions")

public interface EnrollmentControllerDoc {

   // Find All
  @Operation(
          summary = "List enrollments",
          description = "List of enrollments in a paginated way",
          responses = {
                  @ApiResponse(responseCode = "200", description = "List of enrollments returned successfully")
          })
  ResponseEntity<Page<EnrollmentResponseDTO>> findAll(
          @Parameter(description = "Pagination and sorting information")
          Pageable pageable);


  // Find By ID
  @Operation(
          summary = "Search enrollment by ID",
          description = "Returns the summary data of a specific enrollment",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Enrollment found"),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Enrollment not found or invalid ID",
                          content = @Content(schema = @Schema(implementation =  ErrorResponse.class))
                  )})
  ResponseEntity<EnrollmentResponseDTO> findById(
      @Parameter(description = "Enrollment ID", example = "2", required = true) Long id);


  // Save
  @Operation(
      summary = "Save enrollment", description = "Create a new enrollment in the gym system",
      responses = {
              @ApiResponse(
                      responseCode = "201",
                      description = "Enrollment created successfully"
              ),
              @ApiResponse(
                      responseCode = "400",
                      description = "Validation error or business rule violation",
                      content = @Content(schema = @Schema(implementation = ErrorResponse.class))
              )})
  ResponseEntity<EnrollmentResponseDTO> save(
                  @RequestBody
                  @Valid
                  @io.swagger.v3.oas.annotations.parameters.RequestBody(
                        description = "Necessary data to register a enrollment", required = true,
                        content = @Content(schema = @Schema(implementation = EnrollmentRequestDTO.class),
                        examples = @ExampleObject(
                                name = "Enrollment valid",
                                value = """
                                        {
                                              "id" : "2", ,
                                              "enrollmentDate" : "2024-06-01", 
                                              "dueDay" : "15",
                                              "closingDate" : "2024-06-30",
                                              "studentId" : "1"
                                        }
                                        """  )))
                  EnrollmentRequestDTO enrollmentRequestDTO);

  // Change
  @Operation(
      summary = "Change enrollment", description = "Update an existing enrollment in the gym system",
      responses = {
              @ApiResponse(
                      responseCode = "200",
                      description = "Enrollment updated successfully"
              ),
              @ApiResponse(
                      responseCode = "400",
                      description = "Validation error or business rule violation",
                      content = @Content(schema = @Schema(implementation = ErrorResponse.class))
              )})
  ResponseEntity<EnrollmentResponseDTO> change(
                  @Parameter(description = "ID of the enrollment to be changed", required = true)
                  Long id,
                  @RequestBody @Valid
                  @io.swagger.v3.oas.annotations.parameters.RequestBody(
                        description = "Necessary data to update a enrollment",
                        required = true,
                        content = @Content(schema = @Schema(implementation = EnrollmentRequestDTO.class),
                        examples = @ExampleObject(
                                name = "Enrollment valid",
                                value = """
                                        {
                                                "id" : "2", 
                                                "enrollmentDate" : "2024-06-01", 
                                                "dueDay" : "15",
                                                "closingDate" : "2024-06-30",
                                                "studentId" : "1"
                                        }
                                        """)))
                          EnrollmentRequestDTO enrollmentRequestDTO);
        
  // Delete
  @Operation(
          summary = "Delete enrollment", description = "Remove an enrollment from the gym system",
          responses = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Enrollment deleted successfully"
                  ),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Validation error or business rule violation",
                          content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                  )})
  ResponseEntity<Void> deleteById(
          @Parameter(description = "ID of the enrollment to be deleted", required = true)
          Long id);

}

// @formatter:on