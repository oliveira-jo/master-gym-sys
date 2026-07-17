package com.devjoliveira.mastergymsys.doc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.devjoliveira.mastergymsys.domain.exception.ErrorResponse;
import com.devjoliveira.mastergymsys.dto.request.SubscriptionRequestDTO;
import com.devjoliveira.mastergymsys.dto.response.SubscriptionResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

// @formatter:off

@Tag(name = "Subscriptions", description = "Operations for registration, consultation, updating, deletion and filtering of subscriptions")
public interface SubscriptionControllerDoc {

  // Find All
  @Operation(
          summary = "List subscriptions",
          description = "List of subscriptions in a paginated way",
          responses = {
                  @ApiResponse(responseCode = "200", description = "List of subscriptions returned successfully")
          })
  ResponseEntity<Page<SubscriptionResponseDTO>> findAll(
          @Parameter(description = "Pagination and sorting information")
          Pageable pageable);


  // Find By ID
  @Operation(
          summary = "Search subscription by ID",
          description = "Returns the summary data of a specific subscription",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Subscription found"),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Subscription not found or invalid ID",
                          content = @Content(schema = @Schema(implementation =  ErrorResponse.class))
                  )})
  ResponseEntity<SubscriptionResponseDTO> findById(
      @Parameter(description = "Subscription ID", example = "2", required = true) Long id);


  // Save
  @Operation(
      summary = "Save subscription", description = "Create a new subscription in the gym system",
      responses = {
              @ApiResponse(
                      responseCode = "201",
                      description = "Subscription created successfully"
              ),
              @ApiResponse(
                      responseCode = "400",
                      description = "Validation error or business rule violation",
                      content = @Content(schema = @Schema(implementation = ErrorResponse.class))
              )})
  ResponseEntity<SubscriptionResponseDTO> save(
                  @RequestBody
                  @Valid
                  @io.swagger.v3.oas.annotations.parameters.RequestBody(
                        description = "Necessary data to register a subscription", required = true,
                        content = @Content(schema = @Schema(implementation = SubscriptionRequestDTO.class),
                        examples = @ExampleObject(
                                name = "Subscription valid",
                                value = """
                                        {
                                              "name" : "Básico",
                                              "price" : 150.00,
                                              "modalityId" : 1
                                        }
                                        """ )))
                  SubscriptionRequestDTO subscriptionRequestDTO);

  // Change
  @Operation(
      summary = "Change subscription", description = "Update an existing subscription in the gym system",
      responses = {
              @ApiResponse(
                      responseCode = "200",
                      description = "Subscription updated successfully"
              ),
              @ApiResponse(
                      responseCode = "400",
                      description = "Validation error or business rule violation",
                      content = @Content(schema = @Schema(implementation = ErrorResponse.class))
              )})
  ResponseEntity<SubscriptionResponseDTO> change(
                  @Parameter(description = "ID of the subscription to be changed", required = true)
                  Long id,
                  @RequestBody @Valid
                  @io.swagger.v3.oas.annotations.parameters.RequestBody(
                        description = "Necessary data to update a subscription",
                        required = true,
                        content = @Content(schema = @Schema(implementation = SubscriptionRequestDTO.class),
                        examples = @ExampleObject(
                                name = "Subscription valid",
                                value = """
                                        {
                                              "name" : "Premium",
                                              "price" : 150.00,
                                              "modalityId" : 1
                                        }
                                        """)))
                          SubscriptionRequestDTO subscriptionRequestDTO);
        
  // Delete
  @Operation(
          summary = "Delete subscription", description = "Remove a subscription from the gym system",
          responses = {
                  @ApiResponse(
                          responseCode = "200",
                          description = "Subscription deleted successfully"
                  ),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Validation error or business rule violation",
                          content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                  )})
  ResponseEntity<Void> deleteById(
          @Parameter(description = "ID of the subscription to be deleted", required = true)
          Long id);

}

// @formatter:on