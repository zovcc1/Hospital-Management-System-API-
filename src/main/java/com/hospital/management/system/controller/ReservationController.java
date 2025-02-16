package com.hospital.management.system.controller;

import com.hospital.management.system.dto.ReservationDto;
import com.hospital.management.system.enums.ReservationStatus;
import com.hospital.management.system.exceptions.ResourceNotFoundException;
import com.hospital.management.system.model.Reservation;
import com.hospital.management.system.response.ApiResponse;
import com.hospital.management.system.service.ReservationServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("reservations")
public class ReservationController  {
    private final ReservationServiceImpl reservationServiceImpl;
    
    public ReservationController(ReservationServiceImpl reservationServiceImpl) {
        this.reservationServiceImpl = reservationServiceImpl;
    }


    @GetMapping("reservation/all")
    public ResponseEntity<ApiResponse> getAllReservations(@RequestParam int page){
        int size = 10;
        Pageable pageable = PageRequest.of(page,size);
        try {
            Page<ReservationDto> reservationDto = reservationServiceImpl.getAllReservations(pageable);
            return ResponseEntity.ok(new ApiResponse("found",reservationDto));


        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(),null));
        }

    }

    @PostMapping("reservation/create")

    public ResponseEntity<ApiResponse> createReservation(@RequestBody ReservationDto reservation){

        try {
            ReservationDto reservationDto = reservationServiceImpl.createReservation(reservation);
            return ResponseEntity.status(OK).body(new ApiResponse("reservation created successfully", reservationDto ));
        }
        catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage() , null));
        }

    }

    @GetMapping("patient-reservation")
    public ResponseEntity<ApiResponse> getAllPatientReservation(@RequestParam Integer patientId, @RequestParam int page, @RequestParam int size) {
        try {
            Pageable pageable = PageRequest.of(page,size);
            Page<ReservationDto>  reservationDto = reservationServiceImpl.getAllPatientReservation(patientId,pageable);
            if (reservationDto.hasContent())
                return ResponseEntity.status(OK).body(new ApiResponse("patient found", reservationDto ));
            else
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("patient not found" , null));
        }
        catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), INTERNAL_SERVER_ERROR));
        }

    }
    @GetMapping("reservation/room/all")
    public ResponseEntity<ApiResponse> getAllRoomReservation(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page,10);
        try {
            Page<ReservationDto> reservationDto = reservationServiceImpl.getAllRoomReservation(pageable);
            if (!reservationDto.isEmpty()) {
                return ResponseEntity.status(OK).body(new ApiResponse("rooms found", reservationDto));
            }
            else {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("rooms not found", null));
            }
        }
        catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), INTERNAL_SERVER_ERROR));
        }

    }

    @GetMapping("reservation/doctor/all")
    public ResponseEntity<ApiResponse> getAllDoctorReservation(@RequestParam String doctorName, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ReservationDto> reservationDto = reservationServiceImpl.getAllDoctorReservation(doctorName, pageable);
        if (reservationDto != null)
            return ResponseEntity.status(OK).body(new ApiResponse("found", reservationDto));
        else
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("not found", null));

    }

    @PostMapping("reservation/update")
    public ResponseEntity<ApiResponse> updateReservation(@RequestBody ReservationDto newReservation) {
        try {
            ReservationDto reservationDto = reservationServiceImpl.updateReservation(newReservation);
            return ResponseEntity.status(OK).body(new ApiResponse("reservation created", reservationDto));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), INTERNAL_SERVER_ERROR));
        }

    }
    @DeleteMapping("reservation/delete")
    public ResponseEntity<ApiResponse> deleteReservation(@RequestParam Integer reservationId) {
        try{
            reservationServiceImpl.deleteReservation(reservationId);
            return ResponseEntity.status(OK).body(new ApiResponse("deleted", null));
        }
        catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("reservation")
    public ResponseEntity<ApiResponse> getReservationById(@RequestParam Integer reservationId) {
        try {
            Reservation reservationDto = reservationServiceImpl.getReservationById(reservationId);
            return ResponseEntity.status(OK).body(new ApiResponse("found", reservationDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }


    @GetMapping("reservation/by-date")
    public ResponseEntity<ApiResponse> getReservationsByDate(@RequestParam LocalDate startDate,@RequestParam LocalDate endDate, @RequestParam int page, @RequestParam int size) {
        try {
            Pageable pageable = PageRequest.of(page,size);
            Page<ReservationDto> reservationDto = reservationServiceImpl.getReservationsByDate(startDate,endDate,pageable);
            if (reservationDto.isEmpty())
                return ResponseEntity.status(OK).body(new ApiResponse("found", reservationDto));
            else
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("not found",null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("reservation/patient/{patientName}")
    public ResponseEntity<ApiResponse> getPatientReservationsByDate(
    @PathVariable String patientName,
    @RequestParam LocalDate startDate,
    @RequestParam LocalDate endDate,
    @RequestParam int page,
    @RequestParam int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page,size);
            Page<ReservationDto> reservationDto = reservationServiceImpl.getPatientReservationsByDate(patientName, startDate, endDate,pageable);
            if (reservationDto.isEmpty()) {
                return ResponseEntity.status(OK).body(new ApiResponse("found", reservationDto));
            }
            else {
                return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse("not found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/reservations")
    public ResponseEntity<ApiResponse> searchReservations(
            @RequestParam(required = false) String patientName,
            @RequestParam int page,
            @RequestParam int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ReservationDto> reservationDtoList;

            // التحقق إذا تم إرسال اسم المريض لتصفية النتائج
            if (patientName != null && !patientName.isEmpty()) {
                // البحث الجزئي عن الاسم
                reservationDtoList = reservationServiceImpl.getReservationsByPatientName(patientName, pageable);
            } else {
                // إذا لم يتم إرسال اسم المريض، إرجاع كل الحجوزات
                reservationDtoList = reservationServiceImpl.getAllReservations(pageable);
            }

            // تحقق إذا كانت النتائج تحتوي على بيانات
            if (reservationDtoList.hasContent()) {
                return ResponseEntity.status(OK).body(new ApiResponse("Reservations found", reservationDtoList));
            } else {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No reservations found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @GetMapping("reservation/status")
    public ResponseEntity<ApiResponse> findByStatus(@RequestParam ReservationStatus status,@RequestParam int page) {
        try {
            Pageable pageable = PageRequest.of(page - 1 , 9);
            Page<ReservationDto> reservationDto = reservationServiceImpl.findByStatus(status,pageable);
            if (reservationDto != null) {
                return ResponseEntity.status(OK).body(new ApiResponse("found" , reservationDto));
            }
            else {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("not found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }

    }
}
