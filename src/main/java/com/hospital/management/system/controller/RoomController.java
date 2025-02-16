package com.hospital.management.system.controller;

import com.hospital.management.system.dto.RoomDto;
import com.hospital.management.system.exceptions.ResourceNotFoundException;
import com.hospital.management.system.response.ApiResponse;
import com.hospital.management.system.service.RoomServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("rooms")
public class RoomController {
    private final RoomServiceImpl roomServiceImpl;

    public RoomController(RoomServiceImpl roomServiceImpl) {
        this.roomServiceImpl = roomServiceImpl;
    }
    @PostMapping("room/create")
    public ResponseEntity<ApiResponse> createRoom(@RequestBody RoomDto request) {
        try {
            RoomDto roomDto = roomServiceImpl.createRoom(request);
            return ResponseEntity.status(CREATED).body(new ApiResponse("room created ", roomDto));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), INTERNAL_SERVER_ERROR));
        }

    }

    @DeleteMapping("room/delete")
    public ResponseEntity<ApiResponse> deleteRoom(@RequestParam Integer id) {
        try {
            roomServiceImpl.deleteRoom(id);
            return ResponseEntity.status(OK).body(new ApiResponse("room deleted.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }

    }
    @PutMapping("room/update")
    public ResponseEntity<ApiResponse>updateRoom(@RequestBody RoomDto dto){
        try {
            RoomDto room = roomServiceImpl.updateRoom(dto);
            return ResponseEntity.status(OK).body(new ApiResponse("rooms updated ", room));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), INTERNAL_SERVER_ERROR));

        }

    }

    @GetMapping("all")
    public ResponseEntity<ApiResponse> getAllRooms() {
        List<RoomDto> rooms = roomServiceImpl.getAllRooms();
        return ResponseEntity.status(OK).body(new ApiResponse("rooms retrieved ", rooms));

    }
    @GetMapping("room/available")
    public ResponseEntity<ApiResponse>getAvailableRooms(){
        try {
            List<RoomDto> rooms  = roomServiceImpl.getAllAvailableRooms();
            return ResponseEntity.status(OK).body(new ApiResponse("rooms retrieved ", rooms));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("room/status/{search}")
    public ResponseEntity<ApiResponse>searchForStatus(@PathVariable String search){
        try {
            List<RoomDto> roomDtos = roomServiceImpl.getRoomByStatus(search);
            return ResponseEntity.ok(new ApiResponse("found",roomDtos));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage(),null));
        }

    }
//    @GetMapping("room")
//    public ResponseEntity<ApiResponse> getRoomById(@RequestParam Integer id){
//        try {
//            RoomDto roomDto = roomServiceImpl.getRoomById(id);
//            return ResponseEntity.status(OK).body(new ApiResponse("rooms found ", roomDto));
//        } catch (Exception e) {
//            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
//        }
//    }


}
