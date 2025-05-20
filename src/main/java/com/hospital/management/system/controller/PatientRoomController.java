package com.hospital.management.system.controller;

import com.hospital.management.system.dto.PatientRoomDto;
import com.hospital.management.system.model.PatientRoom;
import com.hospital.management.system.response.ApiResponse;
import com.hospital.management.system.service.PatientRoomServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("patients-rooms")
public class PatientRoomController {
    private final PatientRoomServiceImpl patientRoomService;

    public PatientRoomController(PatientRoomServiceImpl patientRoomService) {
        this.patientRoomService = patientRoomService;
    }
    @PostMapping
    public ResponseEntity<ApiResponse> createPatientRoom(@RequestParam Integer roomId, @RequestParam Integer patientFileId){
        try {
            PatientRoom patientRoom = patientRoomService.createPatientRoom(roomId, patientFileId);
            return ResponseEntity.status(OK).body(new ApiResponse("created", patientRoom));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),INTERNAL_SERVER_ERROR));
        }

    }




    //todo need fix because the get is not returning a response body.

    @GetMapping("patient-room/id")
    public ResponseEntity<ApiResponse> getPatientRoomById(@RequestParam Integer patientRoomId){
        try {
            PatientRoom patientRoom = patientRoomService.getPatientRoomById(patientRoomId);
            if (patientRoom !=null)
                return ResponseEntity.status(OK).body(new ApiResponse("found", patientRoom));
            else
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("not found ",null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),INTERNAL_SERVER_ERROR));
        }

    }

    // todo CRUD update INTERNAL_SERVER_ERROR, don't forget to handel INTERNAL_SERVER_ERROR .
    @PutMapping
    public ResponseEntity<ApiResponse> updateBookedRoom(@RequestBody PatientRoomDto request) {

        try {
            PatientRoomDto patientRoomDto = patientRoomService.updateBookedRoom(request);
            return ResponseEntity.status(OK).body(new ApiResponse("created", patientRoomDto));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }

    }
    @GetMapping("patient-room")
    public ResponseEntity<ApiResponse>findPatientRoomByPatientIdOrRoomID(@RequestParam Integer patientId, @RequestParam Integer roomId){
        try {
            PatientRoomDto patientRoomDto = patientRoomService.findPatientRoomByPatientIdOrRoomID(patientId,roomId);
            if (patientRoomDto != null )
                return ResponseEntity.status(OK).body(new ApiResponse("found", patientRoomDto));
            else
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("not found", null));

        } catch (Exception e) {
                return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }

    }
    @DeleteMapping("patient-room")
    public ResponseEntity<ApiResponse>deletePatientRoom(@RequestParam Integer id){
        try {
            patientRoomService.deletePatientRoom(id);
            if (id != null)
                return ResponseEntity.status(OK).body(new ApiResponse("deleted",null));
            else
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("not found",null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }


}
