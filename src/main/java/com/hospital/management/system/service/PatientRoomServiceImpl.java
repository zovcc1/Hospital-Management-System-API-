package com.hospital.management.system.service;

import com.hospital.management.system.dto.PatientFileDto;
import com.hospital.management.system.dto.PatientRoomDto;
import com.hospital.management.system.dto.RoomDto;
import com.hospital.management.system.enums.Status;
import com.hospital.management.system.exceptions.ResourceNotFoundException;
import com.hospital.management.system.mapper.PatientFileMapper;
import com.hospital.management.system.mapper.PatientRoomMapper;
import com.hospital.management.system.mapper.RoomMapper;
import com.hospital.management.system.model.PatientRoom;
import com.hospital.management.system.model.Room;
import com.hospital.management.system.repository.PatientRoomRepository;
import com.hospital.management.system.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientRoomServiceImpl implements PatientRoomService {
    private final PatientFileServiceImp patientFileServiceImp;
    private final RoomServiceImpl roomServiceImpl;
    private final PatientRoomRepository patientRoomRepository;
    private final RoomRepository roomRepository;

    public PatientRoomServiceImpl(PatientFileServiceImp patientFileServiceImp, RoomServiceImpl roomServiceImpl, PatientRoomRepository patientRoomRepository, RoomRepository roomRepository) {
        this.patientFileServiceImp = patientFileServiceImp;
        this.roomServiceImpl = roomServiceImpl;
        this.patientRoomRepository = patientRoomRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public PatientRoom createPatientRoom(Integer roomId, Integer patientFileId) {
        PatientFileDto patientFileDto = patientFileServiceImp.getPatientFileById(patientFileId);
        RoomDto roomDto = roomServiceImpl.getRoomById(roomId);
        if (roomDto.getStatus() != Status.VACANT) {
            throw new IllegalStateException("cannot book, because room is not vacant.");
        }
        roomDto.setStatus(Status.OCCUPIED);
        roomServiceImpl.updateRoom(roomDto);
        PatientRoom patientRoom = new PatientRoom();
        patientRoom.setRoom(RoomMapper.ROOM_MAPPER.toEntity(roomDto));
        patientRoom.setPatientFile(PatientFileMapper.MAPPER.toEntity(patientFileDto));
        PatientRoom savedPatientRoom = patientRoomRepository.save(patientRoom);
        return savedPatientRoom;
    }

    @Override
    public List<PatientRoomDto> getALlPatientRooms() {
        return patientRoomRepository.findAll().stream().map(patientRoom -> PatientRoomMapper.MAPPER.toDto(patientRoom)).collect(Collectors.toList());
    }

    /**
     * @param patientRoomId
     * @return {@link PatientRoom}
     */
    @Override
    public PatientRoom getPatientRoomById(Integer patientRoomId) {
        return patientRoomRepository.findById(patientRoomId).orElseThrow(() -> new ResourceNotFoundException("patient room with id" + patientRoomId + " not found"));
    }


    /**
     * @param request
     * @return PatientRoomDto
     */
    @Override
    public PatientRoomDto updateBookedRoom(PatientRoomDto request) {
        //check if the patient exist in the database.
        PatientRoom patientRoom = getPatientRoomById(request.getId());
        // to make partial update we need to have the entity and dto to make partial update using mapper.
        PatientRoomDto patientRoomDto = PatientRoomMapper.MAPPER.toDto(patientRoom);
        // applying update from the dto to the fetched entity in the database.
        PatientRoom patchedRoom = PatientRoomMapper.MAPPER.partialUpdate(patientRoomDto, patientRoom);
        // saving changes in the database .
        PatientRoom savedRoom = patientRoomRepository.save(patchedRoom);

        return PatientRoomMapper.MAPPER.toDto(savedRoom);
    }

    /**
     * @param patientId
     * @param roomId
     * @return
     */
    @Override
    public PatientRoomDto findPatientRoomByPatientIdOrRoomID(Integer patientId, Integer roomId) {
        PatientRoom patientRoom = patientRoomRepository.findByPatientFile_IdOrRoom_Id(patientId, roomId);
        return PatientRoomMapper.MAPPER.toDto(patientRoom);
    }

    /**
     * @param id
     */
    @Override
    public void deletePatientRoom(Integer id) {
        PatientRoom patientRoom = getPatientRoomById(id);

        if (patientRoom==null){
            throw new ResourceNotFoundException("patient room with this id:"+" "+id);
        }
        else {
             patientRoomRepository.deleteById(id);
             Room room  = patientRoom.getRoom();
             room.setStatus(Status.VACANT);
             roomRepository.save(room);
        }
        
    }
}
