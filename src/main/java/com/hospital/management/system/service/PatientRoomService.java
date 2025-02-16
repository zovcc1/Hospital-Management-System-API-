package com.hospital.management.system.service;


import com.hospital.management.system.dto.PatientRoomDto;
import com.hospital.management.system.model.PatientRoom;

import java.util.List;

/**@author kasem
 *
*
*
* */
public interface PatientRoomService  {

PatientRoom createPatientRoom(Integer roomId , Integer patientFileId);
List<PatientRoomDto> getALlPatientRooms();
PatientRoom getPatientRoomById(Integer PatientRoomId);
PatientRoomDto updateBookedRoom(PatientRoomDto request);
PatientRoomDto findPatientRoomByPatientIdOrRoomID(Integer patientId, Integer roomId);
void deletePatientRoom(Integer id);

}
