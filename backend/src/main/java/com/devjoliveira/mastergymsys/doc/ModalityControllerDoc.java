package com.devjoliveira.mastergymsys.doc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.devjoliveira.mastergymsys.domain.exception.ErrorResponse;
import com.devjoliveira.mastergymsys.dto.request.ModalityRequestDTO;
import com.devjoliveira.mastergymsys.dto.response.ModalityResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

// @formatter:off

@Tag(name = "Modalities", description = "Operations for registration, consultation, updating, deletion and filtering of modalities")
public interface ModalityControllerDoc {

  // Find All
  @Operation(
          summary = "List modalities",
          description = "List of modalities in a paginated way",
          responses = {
                  @ApiResponse(responseCode = "200", description = "List of modalities returned successfully")
          })
  ResponseEntity<Page<ModalityResponseDTO>> findAll(
          @Parameter(description = "Pagination and sorting information")
          Pageable pageable);


  // Find By ID
  @Operation(
          summary = "Search modality by ID",
          description = "Returns the summary data of a specific modality",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Modality found"),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Modality not found or invalid ID",
                          content = @Content(schema = @Schema(implementation =  ErrorResponse.class))
                  )})
  ResponseEntity<ModalityResponseDTO> findById(
      @Parameter(description = "Modality ID", example = "2", required = true) Long id);


  // Find By ID
  @Operation(
          summary = "Search modality by name",
          description = "Returns the summary data of a specific modality",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Modality found"),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Modality not found or invalid name",
                          content = @Content(schema = @Schema(implementation =  ErrorResponse.class))
                  )})
  ResponseEntity<ModalityResponseDTO> searchByName(
    @RequestParam(defaultValue = "")
    @Parameter(description = "Name of the modality to be searched",
      example = "Levantamento de peso", 
      required = true) 
    String name);

  // Save
  @Operation(
      summary = "Save modality", description = "Create a new modality in the gym system",
      responses = {
              @ApiResponse(
                      responseCode = "201",
                      description = "Modality created successfully"
              ),
              @ApiResponse(
                      responseCode = "400",
                      description = "Validation error or business rule violation",
                      content = @Content(schema = @Schema(implementation = ErrorResponse.class))
              )})
  ResponseEntity<ModalityResponseDTO> save(
                  @RequestBody
                  @Valid
                  @io.swagger.v3.oas.annotations.parameters.RequestBody(
                        description = "Necessary data to register a modality", required = true,
                        content = @Content(schema = @Schema(implementation = ModalityRequestDTO.class),
                        examples = @ExampleObject(
                                name = "Modality valid",
                                value = """
                                        {
                                              "name" : "Levantamento de peso",
                                        }
                                        """ )))
                  ModalityRequestDTO modalityRequest);

  // Change
  @Operation(
      summary = "Change modality", description = "Update an existing modality in the gym system",
      responses = {
              @ApiResponse(
                      responseCode = "200",
                      description = "Modality updated successfully"
              ),
              @ApiResponse(
                      responseCode = "400",
                      description = "Validation error or business rule violation",
                      content = @Content(schema = @Schema(implementation = ErrorResponse.class))
              )})
  ResponseEntity<ModalityResponseDTO> change(
                  @Parameter(description = "ID of the modality to be changed", required = true)
                  Long id,
                  @RequestBody @Valid
                  @io.swagger.v3.oas.annotations.parameters.RequestBody(
                        description = "Necessary data to update a modality",
                        required = true,
                        content = @Content(schema = @Schema(implementation = ModalityRequestDTO.class),
                        examples = @ExampleObject(
                                name = "Modality valid",
                                value = """
                                        {
                                              "name" : "Levantamento de peso bruto",
                                        }
                                        """)))
                          ModalityRequestDTO modalityRequest);
        
  // Delete
  @Operation(
          summary = "Delete modality", description = "Remove a modality from the gym system",
          responses = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Modality deleted successfully"
                  ),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Validation error or business rule violation",
                          content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                  )})
  ResponseEntity<Void> deleteById(
          @Parameter(description = "ID of the modality to be deleted", required = true)
          Long id);

}

// @formatter:on