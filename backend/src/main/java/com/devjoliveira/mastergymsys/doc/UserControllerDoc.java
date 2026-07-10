package com.devjoliveira.mastergymsys.doc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.devjoliveira.mastergymsys.domain.exception.ErrorResponse;
import com.devjoliveira.mastergymsys.dto.UserRequestDTO;
import com.devjoliveira.mastergymsys.dto.UserResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

// @formatter:off

@Tag(name = "User", description = "Operations for registration, consultation, updating, deletion and filtering of user")
public interface UserControllerDoc {

        // Find All
        @Operation(
                summary = "List users",
                description = "List users in a paginated way",
                responses = {
                        @ApiResponse(responseCode = "200", description = "List of users returned successfully")
                })
        ResponseEntity<Page<UserResponseDTO>> findAll(@Parameter(description = "Pagination and sorting information") Pageable pageable);


        // Find By ID
        @Operation(
                summary = "Search user by ID",
                description = "Returns the summary data of a specific user",
                responses = {
                        @ApiResponse(responseCode = "200", description = "user found"),
                        @ApiResponse(
                                responseCode = "400",
                                description = "User not found or invalid ID",
                                content = @Content(schema = @Schema(implementation =  ErrorResponse.class))
                        )})
        ResponseEntity<UserResponseDTO> findById(@Parameter(description = "User ID", example = "2", required = true) Long id);



        // Save
        @Operation(
            summary = "Save user", description = "Create a new user in the gym system",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "User created successfully"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Validation error or business rule violation",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )})
        ResponseEntity<UserResponseDTO> save(
                        @RequestBody
                        @Valid
                        @io.swagger.v3.oas.annotations.parameters.RequestBody(
                              description = "Necessary data to register a user", required = true,
                              content = @Content(schema = @Schema(implementation = UserRequestDTO.class),
                              examples = @ExampleObject(
                                      name = "User valid",
                                      value = """
                                              {
                                                    "name" : "João da Silva",
                                                    "cpf": "455465500-80",
                                                    "phone": "48991645543",
                                                    "email": "joao@email.com",
                                                    "password": "senha-do-usuario",
                                                    "birthDate": "1995-08-15",
                                                    "address": "Rua das Flores",
                                                    "number": "123",
                                                    "complement": "Apartamento 202",
                                                    "city": "Criciúma",
                                                    "state": "SC",
                                                    "zipCode": "88802410"
                                              }
                                              """ )))
                        UserRequestDTO userRequest);

        // Change
        @Operation(
            summary = "Change user", description = "Update an existing user in the gym system",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User updated successfully"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Validation error or business rule violation",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )})
        ResponseEntity<UserResponseDTO> change(
                        @Parameter(description = "ID of the user to be changed", required = true)
                        Long id,
                        @RequestBody @Valid
                        @io.swagger.v3.oas.annotations.parameters.RequestBody(
                              description = "Necessary data to update a user",
                              required = true,
                              content = @Content(schema = @Schema(implementation = UserRequestDTO.class),
                              examples = @ExampleObject(
                                      name = "Student valid",
                                      value = """
                                              {
                                                    "name" : "João da Silva",
                                                    "cpf": "455465500-80",
                                                    "phone": "48991645543",
                                                    "email": "joao@email.com",
                                                    "password": "senha-do-usuario",
                                                    "birthDate": "1995-08-15",
                                                    "address": "Rua das Flores",
                                                    "number": "123",
                                                    "complement": "Apartamento 202",
                                                    "city": "Criciúma",
                                                    "state": "SC",
                                                    "zipCode": "88802410"
                                              }
                                              """)))
                                UserRequestDTO userRequest);
        
        // Delete
        @Operation(
                summary = "Delete user", description = "Remove a user from the gym system",
                responses = {
                        @ApiResponse(
                                responseCode = "200",
                                description = "User deleted successfully"
                        ),
                        @ApiResponse(
                                responseCode = "400",
                                description = "Validation error or business rule violation",
                                content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                        )})
        ResponseEntity<Void> deleteById(
                @Parameter(description = "ID of the user to be deleted", required = true)
                Long id);

}

// @formatter:on