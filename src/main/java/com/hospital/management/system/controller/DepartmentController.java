package com.hospital.management.system.controller;

import com.hospital.management.system.dto.DepartmentDto;
import com.hospital.management.system.exceptions.ResourceNotFoundException;
import com.hospital.management.system.model.Department;
import com.hospital.management.system.response.ApiResponse;
import com.hospital.management.system.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("departments")
public class DepartmentController {
    private static final Logger log = LogManager.getLogger(DepartmentController.class);
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("all")
    public ResponseEntity<ApiResponse> getAllDepartments(){
        try {
            List<DepartmentDto> dto =  departmentService.getAllDepartments();
            return ResponseEntity.status(OK).body(new ApiResponse("Ok" , dto));
        } catch (Exception e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Not found" , e.getMessage()));
        }

    }
    @GetMapping("department")
    public ResponseEntity<ApiResponse>getDepartmentById(@RequestParam Integer id){
        try {

            DepartmentDto dto = departmentService.getDepartment(id);
            return ResponseEntity.status(OK).body(new ApiResponse("department found with id: " +
                    id , dto));

        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("department not found",null));
        }
    }

    @PostMapping("/department/add")
    public ResponseEntity<ApiResponse> addDepartment(@RequestBody Department request){
        try {
            DepartmentDto departmentDto = departmentService.createDepartment(request);
            log.info(" creating department with :{}",departmentDto);

            return ResponseEntity.status(OK).body(new ApiResponse("department created", departmentDto));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), INTERNAL_SERVER_ERROR));
        }
    }
    @DeleteMapping("department/delete")
    public ResponseEntity<ApiResponse> deleteDepartment(@RequestParam Integer id){
        try {
            departmentService.deleteDepartment(id);
            return ResponseEntity.status(OK).body(new ApiResponse("department deleted.", null));

        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("/department/update")
    public ResponseEntity<ApiResponse> updateDepartment(@RequestBody DepartmentDto request){
        try {
            DepartmentDto updateDepartment = departmentService.updateDepartment(request);
            return ResponseEntity.status(OK).body(new ApiResponse("Department updated", updateDepartment));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
        
    }


}
