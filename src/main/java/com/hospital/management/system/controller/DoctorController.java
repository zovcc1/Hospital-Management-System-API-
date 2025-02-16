package com.hospital.management.system.controller;

import com.hospital.management.system.dto.DoctorDto;
import com.hospital.management.system.model.Doctor;
import com.hospital.management.system.response.ApiResponse;
import com.hospital.management.system.service.DoctorService;
import com.hospital.management.system.service.DoctorServiceImpl;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    private final DoctorServiceImpl doctorServiceImpl;

    public DoctorController(DoctorServiceImpl doctorServiceImpl) {
        this.doctorServiceImpl = doctorServiceImpl;
    }

    @PostMapping("doctor/create")
    public ResponseEntity<ApiResponse> createDoctor(@RequestBody DoctorDto request) {
        try {
            DoctorDto doctorDto = doctorServiceImpl.createDoctor(request);
            return ResponseEntity.status(CREATED).body(new ApiResponse("doctor created successfully",doctorDto));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("doctor/available")
    public ResponseEntity<ApiResponse> getAllAvailableDoctors() {
        try {
            List<DoctorDto> doctorDto = doctorServiceImpl.getAllAvailableDoctors();
            return ResponseEntity.status(OK).body(new ApiResponse("available doctors found", doctorDto));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage() , null));
        }
    }
    @GetMapping("all")
    public ResponseEntity<ApiResponse> getAllDoctors() {
        try {
            List<DoctorDto> doctorDto = doctorServiceImpl.getAllDoctors();

            if (!doctorDto.isEmpty()){
                return ResponseEntity.status(OK).body(new ApiResponse("found successfully", doctorDto));

            }
            else {
                return ResponseEntity.status(NO_CONTENT).body(new ApiResponse("no content", null));
            }
        } catch (Exception e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage() , null));
        }
    }
    @GetMapping("doctor")
    public ResponseEntity<ApiResponse> getDoctorById(@RequestParam Integer id) {
        try {
            DoctorDto dto  = doctorServiceImpl.getDoctorById(id);
            return ResponseEntity.status(OK).body(new ApiResponse("doctor with id : " +
                    id + " found", dto));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage() ,null));
        }
    }
    @PutMapping("doctor/update")
    public ResponseEntity<ApiResponse> updateDoctor(@RequestBody DoctorDto updateDto) {
        try {
                DoctorDto doctorDto = doctorServiceImpl.updateDoctor(updateDto);
                return ResponseEntity.status(OK).body(new ApiResponse("updated successfully", doctorDto));
        } catch (Exception e) {
                return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage() ,null));
        }
    }
    @DeleteMapping("doctor")
    public ResponseEntity<ApiResponse> deleteDoctorById(@RequestParam Integer id) {
        try {
            doctorServiceImpl.deleteDoctorById(id);
            return ResponseEntity.status(OK).body(new ApiResponse("deleted successfully" , null));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage() , null));
        }
    }
}
