package com.hospital.management.system.service;

import com.hospital.management.system.dto.DoctorDto;
import com.hospital.management.system.dto.PatientFileDto;
import com.hospital.management.system.dto.ReservationDto;
import com.hospital.management.system.dto.RoomDto;
import com.hospital.management.system.enums.ReservationStatus;
import com.hospital.management.system.exceptions.ResourceNotFoundException;
import com.hospital.management.system.mapper.*;
import com.hospital.management.system.model.Reservation;
import com.hospital.management.system.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class ReservationServiceImpl implements  ReservationService{

    private static final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);
    private final ReservationRepository reservationRepository;
    private final RoomServiceImpl roomServiceImpl;
    private final DoctorServiceImpl doctorServiceImpl;
    private final PatientFileServiceImp patientFileServiceImp;
    private final PatientRoomServiceImpl patientRoomServiceImpl;


    public ReservationServiceImpl(ReservationRepository reservationRepository, RoomServiceImpl roomServiceImpl,  DoctorServiceImpl doctorServiceImpl, PatientFileServiceImp patientFileServiceImp, PatientRoomServiceImpl patientRoomServiceImpl) {
        this.reservationRepository = reservationRepository;
        this.roomServiceImpl = roomServiceImpl;
        this.doctorServiceImpl = doctorServiceImpl;
        this.patientFileServiceImp = patientFileServiceImp;
        this.patientRoomServiceImpl = patientRoomServiceImpl;
    }



    @Override
    @Transactional
    public ReservationDto createReservation(ReservationDto request) {
        // Validate that the reservation date is not in the past.
        PatientFileDto patientFileDto = patientFileServiceImp.getPatientFileById(request.getPatientFileId());

        DoctorDto doctorDto = doctorServiceImpl.getDoctorById(request.getDoctorId());

        LocalDateTime reservationDate = request.getReservationDate();

        if (request.getReservationDate().isBefore(LocalDateTime.now()) || request.getReservationDate().toLocalTime().isBefore(doctorDto.getShiftDateStart()) || request.getReservationDate().toLocalTime().isAfter(doctorDto.getShiftDateEnd())) {
            throw new IllegalArgumentException("Reservation date must be today or doesn't align with doctor's shift ");
        }

        // Create a new Reservation entity
        Reservation reservation = new Reservation();

        // Set the doctor and patient file details
        reservation.setDoctor(DoctorMapper.DOCTOR_MAPPER.toEntity(doctorDto));

        reservation.setPatientFile(PatientFileMapper.MAPPER.toEntity(patientFileDto));

        // Directly use the reservationDate from the request (no additional parsing needed)
        reservation.setReservationDate(reservationDate);

        // Set the default status
        reservation.setStatus(ReservationStatus.PENDING);

        // Validate and map the room if provided
        if (request.getRoomId() != null && isRoomAvailable(request.getRoomId(),reservation.getReservationDate(),request.getId())) {
            RoomDto roomDto = roomServiceImpl.getRoomById(request.getRoomId());
            reservation.setRoom(patientRoomServiceImpl.createPatientRoom(roomDto.getId(), patientFileDto.getId()));
        }else {
            throw new IllegalArgumentException("room at this time is Occupied");
        }

        // Update the reservation entity with any additional request values
        Reservation patchedReservation = ReservationMapper.MAPPER.partialUpdate(request, reservation);
        Reservation savedReservation = reservationRepository.save(patchedReservation);

        // Convert the saved reservation entity to ReservationDto and return it
        return ReservationMapper.MAPPER.toDto(savedReservation);
    }


    @Override
    public Page<ReservationDto> getAllReservations() {
        return (Page<ReservationDto>) reservationRepository.findAll().stream().map(reservation -> ReservationMapper.MAPPER.toDto(reservation));
    }


    @Override
    public Page<ReservationDto> getAllPatientReservation(Integer patientId , Pageable pageable) {
        return reservationRepository.findByPatientFile_Id(patientId, pageable)
                .map(reservation -> ReservationMapper.MAPPER.toDto(reservation));
    }



    @Override
    public Page<ReservationDto> getAllRoomReservation(Pageable pageable) {
        return reservationRepository
                .findByRoomIsNotNull(pageable)
                .map(reservation -> ReservationMapper.MAPPER.toDto(reservation));
    }

    @Override
    public Page<ReservationDto> getAllDoctorReservation(String doctorName , Pageable pageable) {
        return reservationRepository
                .findByDoctor_Name(doctorName,pageable)
                .map(reservation -> ReservationMapper.MAPPER.toDto(reservation));
    }
    public boolean isRoomAvailable(Integer roomId,LocalDateTime reservationDate,Integer reservationId){
        List<Reservation> reservations = reservationRepository.findByRoom_IdAndReservationDate(roomId,reservationDate);
        if (reservationId == null) {
            return reservations.isEmpty();
        }else {

         for (Reservation reservation : reservations){
            if (!(reservation.getId().equals(reservationId))){
                return false;//room occupied
            }
         }

        }
                return true;//room is vacant
    }


    @Override
    public ReservationDto updateReservation(ReservationDto newReservation) {
        Reservation reservation = getReservationById(newReservation.getId());
        if(!isRoomAvailable(newReservation.getId(),reservation.getReservationDate(),reservation.getId()))
        {

            throw new  IllegalArgumentException("room not found");
        }
        Reservation patchedReservation = ReservationMapper.MAPPER.partialUpdate(newReservation , reservation);
        Reservation savedReservation = reservationRepository.save(patchedReservation);
        return ReservationMapper.MAPPER.toDto(savedReservation);

    }


    @Override
    public void deleteReservation(Integer reservationId) {
        reservationRepository.findById(reservationId).ifPresentOrElse(reservationRepository::delete, () -> {
            throw new ResourceNotFoundException("reservation not found");
        });
    }

    @Override
    public Reservation getReservationById(Integer reservationId) {
        return reservationRepository
                .findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("reservation not found"));
    }

    @Override
    public Page<ReservationDto> getReservationsByDate(LocalDate startDate,LocalDate endDate, Pageable pageable) {
        return reservationRepository.findByReservationDateBetween(startDate,endDate,pageable)
                .map(reservation -> ReservationMapper.MAPPER.toDto(reservation));
    }


    @Override
    public Page<ReservationDto> getPatientReservationsByDate(String patientName, LocalDate startDate, LocalDate endDate,Pageable pageable) {
        return reservationRepository
                .findByPatientFile_NameContainsAndReservationDateBetween(patientName, startDate, endDate,pageable)
                .map(reservation -> ReservationMapper.MAPPER.toDto(reservation));
    }

    @Override
    public Page<ReservationDto> getReservationsByPatientName(String patientName,Pageable pageable) {
        return reservationRepository
                .findByPatientFile_NameContains(patientName,pageable)
                .map(reservation -> ReservationMapper.MAPPER.toDto(reservation));
    }




    @Override
    public Page<ReservationDto> findByStatus(ReservationStatus status, Pageable pageable) {
        return reservationRepository
                .findByStatus(status,pageable)
                .map(reservation -> ReservationMapper.MAPPER.toDto(reservation));
    }



    public Page<ReservationDto> getAllReservations(Pageable pageable) {
        return reservationRepository
                .findAll(pageable)
                .map(reservation -> ReservationMapper.MAPPER.toDto(reservation));
    }
}
