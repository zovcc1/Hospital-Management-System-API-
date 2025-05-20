package com.hospital.management.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hospital.management.system.enums.Status;
import com.hospital.management.system.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByStatus(Status status);
    @Query("SELECT r FROM Room r " +
            " where upper(r.status) like UPPER(concat('%' ,:search, '%') ) ")
    List<Room>findByStatusLike(@Param("search") String search);

}
