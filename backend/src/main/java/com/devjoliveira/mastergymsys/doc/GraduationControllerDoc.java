package com.devjoliveira.mastergymsys.doc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.devjoliveira.mastergymsys.domain.exception.ErrorResponse;
import com.devjoliveira.mastergymsys.dto.GraduationRequestDTO;
import com.devjoliveira.mastergymsys.dto.GraduationResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

// @formatter:off

@Tag(name = "Graduations", description = "Operations for registration, consultation, updating, deletion and filtering of graduations")
public interface GraduationControllerDoc {

  // Find All
  @Operation(
          summary = "List graduations",
          description = "List of graduations in a paginated way",
          responses = {
                  @ApiResponse(responseCode = "200", description = "List of graduations returned successfully")
          })
  ResponseEntity<Page<GraduationResponseDTO>> findAll(
          @Parameter(description = "Pagination and sorting information")
          Pageable pageable);


  // Find By ID
  @Operation(
          summary = "Search graduation by ID",
          description = "Returns the summary data of a specific graduation",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Graduation found"),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Graduation not found or invalid ID",
                          content = @Content(schema = @Schema(implementation =  ErrorResponse.class))
                  )})
  ResponseEntity<GraduationResponseDTO> findById(
      @Parameter(description = "Graduation ID", example = "2", required = true) Long id);

  // Find By ID
  @Operation(
          summary = "Search graduation by name",
          description = "Returns the summary data of a specific graduation",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Graduation found"),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Graduation not found or invalid name",
                          content = @Content(schema = @Schema(implementation =  ErrorResponse.class))
                  )})
  ResponseEntity<GraduationResponseDTO> searchByName(
    @RequestParam(defaultValue = "")
    @Parameter(description = "Name of the graduation to be searched",
      example = "Meio Periodo", 
      required = true) 
    String name);

  // Save
  @Operation(
      summary = "Save graduation", description = "Create a new graduation in the gym system",
      responses = {
              @ApiResponse(
                      responseCode = "201",
                      description = "Graduation created successfully"
              ),
              @ApiResponse(
                      responseCode = "400",
                      description = "Validation error or business rule violation",
                      content = @Content(schema = @Schema(implementation = ErrorResponse.class))
              )})
  ResponseEntity<GraduationResponseDTO> save(
                  @RequestBody
                  @Valid
                  @io.swagger.v3.oas.annotations.parameters.RequestBody(
                        description = "Necessary data to register a graduation", required = true,
                        content = @Content(schema = @Schema(implementation = GraduationRequestDTO.class),
                        examples = @ExampleObject(
                                name = "Graduation valid",
                                value = """
                                        {
                                              "name" : "Iniciante",
                                              "modalityId" : "1"
                                        }
                                        """  )))
                  GraduationRequestDTO graduationRequest);

  // Change
  @Operation(
      summary = "Change graduation", description = "Update an existing graduation in the gym system",
      responses = {
              @ApiResponse(
                      responseCode = "200",
                      description = "Graduation updated successfully"
              ),
              @ApiResponse(
                      responseCode = "400",
                      description = "Validation error or business rule violation",
                      content = @Content(schema = @Schema(implementation = ErrorResponse.class))
              )})
  ResponseEntity<GraduationResponseDTO> change(
                  @Parameter(description = "ID of the graduation to be changed", required = true)
                  Long id,
                  @RequestBody @Valid
                  @io.swagger.v3.oas.annotations.parameters.RequestBody(
                        description = "Necessary data to update a graduation",
                        required = true,
                        content = @Content(schema = @Schema(implementation = GraduationRequestDTO.class),
                        examples = @ExampleObject(
                                name = "Graduation valid",
                                value = """
                                        {
                                              "name" : "Intermediário",
                                              "modalityId" : "1"
                                        }
                                        """)))
                          GraduationRequestDTO graduationRequest);
        
  // Delete
  @Operation(
          summary = "Delete graduation", description = "Remove a graduation from the gym system",
          responses = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Graduation deleted successfully"
                  ),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Validation error or business rule violation",
                          content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                  )})
  ResponseEntity<Void> deleteById(
          @Parameter(description = "ID of the graduation to be deleted", required = true)
          Long id);

}

// @formatter:on