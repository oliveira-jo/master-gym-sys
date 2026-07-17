package com.devjoliveira.mastergymsys.doc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.devjoliveira.mastergymsys.domain.exception.ErrorResponse;
import com.devjoliveira.mastergymsys.dto.request.StudentFilterRequest;
import com.devjoliveira.mastergymsys.dto.request.StudentRequestDTO;
import com.devjoliveira.mastergymsys.dto.response.StudentResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

// @formatter:off

@Tag(name = "Students", description = "Operations for registration, consultation, updating, deletion and filtering of students")
public interface StudentControllerDoc {

        // Find All
        @Operation(
                summary = "List students",
                description = "List students in a paginated way, allowing optional filters by " +
                                "name, email, phone, city and state",
                responses = {
                        @ApiResponse(responseCode = "200", description = "List of students returned successfully")
                })
        ResponseEntity<Page<StudentResponseDTO>> findAll(
                @Parameter(description = "Optional filters for searching students") 
                StudentFilterRequest filtro,

                @Parameter(description = "Pagination and sorting information")
                Pageable pageable);


        // Find By ID
        @Operation(
                summary = "Search student by ID",
                description = "Returns the summary data of a specific student",
                responses = {
                        @ApiResponse(responseCode = "200", description = "Student found"),
                        @ApiResponse(
                                responseCode = "400",
                                description = "Student not found or invalid ID",
                                content = @Content(schema = @Schema(implementation =  ErrorResponse.class))
                        )})
        ResponseEntity<StudentResponseDTO> findById(
            @Parameter(description = "Student ID", example = "2", required = true) Long id);



        // Save
        @Operation(
            summary = "Save student", description = "Create a new student in the gym system",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Student created successfully"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Validation error or business rule violation",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )})
        ResponseEntity<StudentResponseDTO> save(
                        @RequestBody
                        @Valid
                        @io.swagger.v3.oas.annotations.parameters.RequestBody(
                              description = "Necessary data to register a student", required = true,
                              content = @Content(schema = @Schema(implementation = StudentRequestDTO.class),
                              examples = @ExampleObject(
                                      name = "Student valid",
                                      value = """
                                              {
                                                    "name" : "João da Silva",
                                                    "birthDate": "1995-08-15",
                                                    "genre": "M",
                                                    "phone": "48991645543",
                                                    "email": "joao@email.com",
                                                    "observation": "Aluno iniciante",
                                                    "address": "Rua das Flores",
                                                    "number": "123",
                                                    "complement": "Apartamento 202",
                                                    "city": "Criciúma",
                                                    "state": "SC",
                                                    "zipCode": "88802410"
                                              }
                                              """ )))
                        StudentRequestDTO studentRequest);

        // Change
        @Operation(
            summary = "Change student", description = "Update an existing student in the gym system",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Student updated successfully"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Validation error or business rule violation",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )})
        ResponseEntity<StudentResponseDTO> change(
                        @Parameter(description = "ID of the student to be changed", required = true)
                        Long id,
                        @RequestBody @Valid
                        @io.swagger.v3.oas.annotations.parameters.RequestBody(
                              description = "Necessary data to update a student",
                              required = true,
                              content = @Content(schema = @Schema(implementation = StudentRequestDTO.class),
                              examples = @ExampleObject(
                                      name = "Student valid",
                                      value = """
                                              {
                                                    "name" : "João da Silva",
                                                    "birthDate": "1995-08-15",
                                                    "genre": "M",
                                                    "phone": "48991645543",
                                                    "email": "joao@email.com",
                                                    "observation": "Aluno iniciante",
                                                    "address": "Rua das Flores",
                                                    "number": "123",
                                                    "complement": "Apartamento 202",
                                                    "city": "Criciúma",
                                                    "state": "SC",
                                                    "zipCode": "88802410"
                                              }
                                              """)))
                                StudentRequestDTO studentRequest);
        
        // Delete
        @Operation(
                summary = "Delete student", description = "Remove a student from the gym system",
                responses = {
                        @ApiResponse(
                                responseCode = "200",
                                description = "Student deleted successfully"
                        ),
                        @ApiResponse(
                                responseCode = "400",
                                description = "Validation error or business rule violation",
                                content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                        )})
        ResponseEntity<Void> deleteById(
                @Parameter(description = "ID of the student to be deleted", required = true)
                Long id);

}

// @formatter:on