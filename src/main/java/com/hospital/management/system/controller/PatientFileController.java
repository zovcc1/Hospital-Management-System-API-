package com.hospital.management.system.controller;

import com.hospital.management.system.dto.PatientFileDto;
import com.hospital.management.system.response.ApiResponse;
import com.hospital.management.system.service.PatientFileServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController()
@RequestMapping("patient-files")
public class PatientFileController {

    private final PatientFileServiceImp patientFileServiceImp;

    public PatientFileController(PatientFileServiceImp patientFileServiceImp) {
        this.patientFileServiceImp = patientFileServiceImp;
    }

    @PostMapping("patient-file/create")
    public ResponseEntity<ApiResponse> createPatientFile(@RequestBody PatientFileDto request) {
        try {
            PatientFileDto patientFileDto = patientFileServiceImp.createPatientFile(request);
            return ResponseEntity.status(OK).body(new ApiResponse("created.", patientFileDto));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }

    }

    @GetMapping("all")
    public ResponseEntity<ApiResponse> getAllPatientFiles() {
        try {
            List<PatientFileDto> patientFileDto = patientFileServiceImp.getAllPatientFiles();
            if (!patientFileDto.isEmpty())
                return ResponseEntity.status(OK).body(new ApiResponse("found", patientFileDto));
            else
                return ResponseEntity.status(NO_CONTENT).body(new ApiResponse("not found", null));

        } catch (Exception e) {

            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

        }


    }

    @GetMapping("patient-file")
    public ResponseEntity<ApiResponse> getPatientFileById(@RequestParam Integer id) {
        try {
            PatientFileDto patientFileDto = patientFileServiceImp.getPatientFileById(id);
            return ResponseEntity.status(OK).body(new ApiResponse("found", patientFileDto));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("patient-file/update")
    public ResponseEntity<ApiResponse> updatePatientFile(@RequestBody PatientFileDto request) {
        try {
            PatientFileDto updatePatientFile = patientFileServiceImp.updatePatientFile(request);
            return ResponseEntity.status(OK).body(new ApiResponse("updated", updatePatientFile));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }

    }

    @DeleteMapping("patient-file")
    public ResponseEntity<ApiResponse> deletePatientFile(@RequestParam Integer id) {
        try {
            patientFileServiceImp.deletePatientFile(id);
            return ResponseEntity.status(OK).body(new ApiResponse("deleted", null));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("not found", null));
        }

    }


}
