package com.hospital.management.system.service;
import com.hospital.management.system.dto.RoomDto;
import com.hospital.management.system.enums.Status;
import com.hospital.management.system.exceptions.AlreadyExistsException;
import com.hospital.management.system.exceptions.ResourceNotFoundException;
import com.hospital.management.system.mapper.RoomMapper;
import com.hospital.management.system.model.Department;
import com.hospital.management.system.model.Room;
import com.hospital.management.system.repository.DepartmentRepository;
import com.hospital.management.system.repository.RoomRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService{
    private static final Logger log = LogManager.getLogger(RoomServiceImpl.class);
    private final RoomRepository roomRepository;
    private final DepartmentRepository departmentRepository;


    public RoomServiceImpl(RoomRepository roomRepository, DepartmentRepository departmentRepository) {
        this.roomRepository = roomRepository;
        this.departmentRepository = departmentRepository;
    }

    /**
     * @param room
     * @return {@link RoomDto}
     */
    public RoomDto createRoom(RoomDto room) {
        Department department = departmentRepository.findById(room.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("department not found"));
        room.setDepartmentId(department.getId());
        department.setDescription(department.getDescription());
        return Optional.of(room)
                .map(dto -> RoomMapper.ROOM_MAPPER.toEntity(dto))
                .map(roomRepository::save)
                .map(room1 -> RoomMapper.ROOM_MAPPER.toDto(room1))
                .orElseThrow(() -> new AlreadyExistsException("room already exist"));
    }



    /**
     * @return 
     */
    @Override
    public List<RoomDto> getAllAvailableRooms() {
            return roomRepository.findByStatus(Status.VACANT)
                    .stream().map(room -> RoomMapper.ROOM_MAPPER.toDto(room))
                    .collect(Collectors.toList());
    }
    public List<RoomDto> getRoomByStatus(String status){
        return roomRepository.findByStatusLike(status).stream().map(room -> RoomMapper.ROOM_MAPPER.toDto(room)).collect(Collectors.toList());
    }
    /**
     * @return
     */
    @Override
    public List<RoomDto> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return RoomMapper.ROOM_MAPPER.toDto(rooms);
    }

    /**
     * @return 
     */
    @Override
    public RoomDto getRoomById(Integer id) {
        return roomRepository.findById(id).map(room -> RoomMapper.ROOM_MAPPER.toDto(room)).orElseThrow(() -> new ResourceNotFoundException("room with id " +
                id + "not found"));}

    /**
     * @param patch
     * @param patch
     * @return
     */
    @Override
    public RoomDto updateRoom(RoomDto patch) {
        Room room = roomRepository.findById(patch.getId()).orElseThrow(() -> new ResourceNotFoundException("room not found"));
        Room patchedRoom = RoomMapper.ROOM_MAPPER.partialUpdate(patch , room);
        Room savedRoom = roomRepository.save(patchedRoom);
        return RoomMapper.ROOM_MAPPER.toDto(room);
    }

    /**
     * @param id
     */
    @Override
    public void deleteRoom(Integer id) {
        roomRepository.findById(id).ifPresentOrElse(roomRepository::delete,() -> {throw new ResourceNotFoundException("room not found");});
    }


}
