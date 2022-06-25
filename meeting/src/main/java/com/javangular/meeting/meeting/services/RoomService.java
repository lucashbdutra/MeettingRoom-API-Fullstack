package com.javangular.meeting.meeting.services;

import com.javangular.meeting.meeting.entities.Room;
import com.javangular.meeting.meeting.exceptions.RoomNotFound;
import com.javangular.meeting.meeting.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class RoomService {

    public final RoomRepository roomRepository;

    public List<Room> findAll(){
        return roomRepository.findAll();
    }

    public Room findById(Long id) throws RoomNotFound{

        Room room = roomRepository.findById(id).orElseThrow(() -> new RoomNotFound("Room not found for this id: " + id));

        return room;

    }

    public Room createRoom(Room room){

        roomRepository.save(room);
        return room;
    }

    public Room updateRoom(Long id, Room room) throws RoomNotFound{

        Room old = roomRepository.findById(id).orElseThrow(() -> new RoomNotFound("Room not found for this id: " + id));

        old.setName(room.getName());
        old.setDate(room.getDate());
        old.setStartHour(room.getStartHour());
        old.setEndHour(room.getEndHour());

        roomRepository.save(old);

        return old;
    }

    public Map<String, Boolean> deleteRoom(Long id){
        Room room = roomRepository.findById(id).orElseThrow(() -> new RoomNotFound("Room not found for this id: " + id));

        roomRepository.delete(room);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete", true);

        return response;
    }

}
