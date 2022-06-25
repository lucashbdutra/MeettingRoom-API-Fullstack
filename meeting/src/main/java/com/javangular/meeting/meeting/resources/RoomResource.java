package com.javangular.meeting.meeting.resources;

import com.javangular.meeting.meeting.entities.Room;
import com.javangular.meeting.meeting.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/rooms")
public class RoomResource {

    public final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<Room>> findaAll(){

        return ResponseEntity.ok().body(roomService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable Long id){

        return ResponseEntity.ok().body(roomService.findById(id));
    }


    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room){

        return ResponseEntity.ok().body(roomService.createRoom(room));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room room){

        return ResponseEntity.ok().body(roomService.updateRoom(id,room));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteRoom(@PathVariable Long id){

        return ResponseEntity.ok().body(roomService.deleteRoom(id));
    }
    
}
