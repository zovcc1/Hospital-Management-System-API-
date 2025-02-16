package com.hospital.management.system.service;

import com.hospital.management.system.dto.RoomDto;
import com.hospital.management.system.model.Room;

import java.util.List;

public interface RoomService {
     RoomDto createRoom(RoomDto room);
     List<RoomDto> getAllAvailableRooms();
     List<RoomDto> getAllRooms();
     RoomDto getRoomById(Integer id);
     RoomDto updateRoom(RoomDto dto);
     void deleteRoom(Integer id);



}
