package com.hospital.management.system.mapper;

import com.hospital.management.system.dto.RoomDto;
import com.hospital.management.system.model.Room;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoomMapper {
    RoomMapper ROOM_MAPPER = Mappers.getMapper(RoomMapper.class);

    @Mapping(source = "departmentId" , target = "department.id")
    @Mapping(source = "departmentName",target = "department.name")
    @Mapping(source = "departmentDescription",target = "department.description")
    Room toEntity(RoomDto roomDto);

    List<Room> toEntity(List<RoomDto> roomDto);
    @Mapping(source = "department.id" , target = "departmentId")
    @Mapping(source = "department.name",target = "departmentName")
    @Mapping(source = "department.description",target = "departmentDescription")
    RoomDto toDto(Room room);

    List<RoomDto> toDto(List<Room> room);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)Room partialUpdate(RoomDto roomDto, @MappingTarget Room room);
}
