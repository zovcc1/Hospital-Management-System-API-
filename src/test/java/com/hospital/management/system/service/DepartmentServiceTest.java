//package com.hospital.management.system.service;
//
//import com.hospital.management.system.enums.RoomType;
//import com.hospital.management.system.enums.Status;
//import com.hospital.management.system.exceptions.AlreadyExistsException;
//import com.hospital.management.system.exceptions.ResourceNotFoundException;
//import com.hospital.management.system.model.Department;
//import com.hospital.management.system.model.Room;
//import com.hospital.management.system.repository.DepartmentRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.lang.reflect.Array;
//import java.rmi.AlreadyBoundException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class DepartmentServiceTest {
//    @Mock
//    private DepartmentRepository departmentRepository;
//    @InjectMocks
//    private DepartmentService departmentService;
//    private Department department;
//    @BeforeEach
//    void setUp() {
//        department = new Department();
//        department.setId(577);
//        department.setName("RongLima");
//
//        List<Room> rooms = Arrays.asList(
//                new Room(1, department, Status.VACANT, RoomType.ICU),
//                new Room(2, department, Status.VACANT, RoomType.ICU)
//        );
//        department.setRooms(rooms);
//    }
//
//    @Test
//    void DepartmentService_CreateDepartment_ShouldReturnDepartment() {
//
//        when(departmentRepository.save(Mockito.any(Department.class))).thenReturn(department);
//        Department createDepartment= departmentService.createDepartment(department);
//        assertEquals(department.getId() , createDepartment.getId());
//        assertEquals(department.getName() , createDepartment.getName());
//        assertEquals(department.getRooms() , createDepartment.getRooms());
//    }
//
//    @Test
//    void departmentService_CreateDepartment_ShouldReturnAlreadyExistsException() {
//        when(departmentRepository.existsByName(Mockito.anyString())).thenReturn(true);
//        assertThrows(AlreadyExistsException.class , () -> {
//            departmentService.createDepartment(department);
//        });
//    }
//
//
//    @Test
//    void departmentService_DeleteDepartmentById_ShouldReturnVoid() {
//        when(departmentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(department));
//
//        departmentService.deleteDepartment(577);
//
//        verify(departmentRepository, times(1)).deleteById(577);
//    }
//
//    @Test
//    void departmentService_DeleteDepartmentById_ShouldReturnResourceNotFoundException() {
//        when(departmentRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
//        assertThrows(ResourceNotFoundException.class , () -> {
//            departmentService.deleteDepartment(999);
//        });
//        verify(departmentRepository,never()).deleteById(Mockito.anyInt());
//    }
//}