package com.hospital.management.system.repository;

import com.hospital.management.system.dto.RoomDto;
import com.hospital.management.system.enums.Status;
import com.hospital.management.system.model.Department;
import com.hospital.management.system.model.Room;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByStatus(Status status);
    @Query("SELECT r FROM Room r " +
            " where upper(r.status) like UPPER(concat('%' ,:search, '%') ) ")
    List<Room>findByStatusLike(@Param("search") String search);

}
