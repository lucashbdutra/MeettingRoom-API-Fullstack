package com.javangular.meeting.meeting.resources;

import com.javangular.meeting.meeting.entities.Room;
import com.javangular.meeting.meeting.exceptions.RoomNotFound;
import com.javangular.meeting.meeting.services.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class RoomResourceTest {

    @InjectMocks
    RoomResource resource;

    @Mock
    RoomService service;

    private final Integer INDEX = 0;
    private final Long ID = 1L;
    private final String NAME = "test";
    private final String DATE = "30/02/2023";
    private final String STARTHOUR = "12PM";
    private final String ENDHOUR = "1AM";

    private Room room;
    private List<Room> list;
    private Optional<Room> optional;
    private Map<String, Boolean> map = new HashMap<>();

    @BeforeEach
    void setUp() {
        room = new Room(ID,NAME,DATE,STARTHOUR,ENDHOUR);
        list = List.of(room);
        optional = Optional.of(room);
        map.put("Delete", true);
    }

    @Test
    void shouldReturnAListOfRoomsWhenIsCalled(){
        given(service.findAll()).willReturn(list);

        ResponseEntity<List<Room>> response = resource.findaAll();
        List<Room> test = response.getBody();

        assertThat(response).isNotNull();
        assertThat(test).isNotNull();
        assertThat(response.getClass()).isEqualTo(ResponseEntity.class);
        assertThat(test.getClass()).isEqualTo(list.getClass());
        assertThat(test.get(INDEX).getId()).isEqualTo(list.get(INDEX).getId());
        assertThat(test.get(INDEX).getName()).isEqualTo(list.get(INDEX).getName());
        assertThat(test.get(INDEX).getDate()).isEqualTo(list.get(INDEX).getDate());
        assertThat(test.get(INDEX).getStartHour()).isEqualTo(list.get(INDEX).getStartHour());
        assertThat(test.get(INDEX).getEndHour()).isEqualTo(list.get(INDEX).getEndHour());

    }

    @Test
    void shouldReturnARoomWhenAnIdIsPassed(){
        given(service.findById(anyLong())).willReturn(room);

        ResponseEntity<Room> response = resource.findById(ID);
        Room test = response.getBody();

        assertThat(response).isNotNull();
        assertThat(response.getClass()).isEqualTo(ResponseEntity.class);

        assertThat(test.getClass()).isEqualTo(room.getClass());
        assertThat(test.getId()).isEqualTo(room.getId());
        assertThat(test.getName()).isEqualTo(room.getName());
        assertThat(test.getDate()).isEqualTo(room.getDate());
        assertThat(test.getStartHour()).isEqualTo(room.getStartHour());
        assertThat(test.getEndHour()).isEqualTo(room.getEndHour());

    }

    @Test
    void shouldReturnAndSaveARoomWhenARoomIsPassed(){
        given(service.createRoom(any(Room.class))).willReturn(room);

        ResponseEntity<Room> response = resource.createRoom(room);
        Room test = response.getBody();

        assertThat(response).isNotNull();
        assertThat(test).isNotNull();
        assertThat(response.getClass()).isEqualTo(ResponseEntity.class);

        assertThat(test.getClass()).isEqualTo(room.getClass());
        assertThat(test.getId()).isEqualTo(room.getId());
        assertThat(test.getName()).isEqualTo(room.getName());
        assertThat(test.getDate()).isEqualTo(room.getDate());
        assertThat(test.getStartHour()).isEqualTo(room.getStartHour());
        assertThat(test.getEndHour()).isEqualTo(room.getEndHour());
    }

    @Test
    void shouldReturnAndUpdateARoomWhenARoomIsPassed(){
        given(service.updateRoom(anyLong(),any(Room.class))).willReturn(room);

        ResponseEntity<Room> response = resource.updateRoom(ID,room);
        Room test = response.getBody();

        assertThat(response).isNotNull();
        assertThat(test).isNotNull();
        assertThat(response.getClass()).isEqualTo(ResponseEntity.class);

        assertThat(test.getClass()).isEqualTo(room.getClass());
        assertThat(test.getId()).isEqualTo(room.getId());
        assertThat(test.getName()).isEqualTo(room.getName());
        assertThat(test.getDate()).isEqualTo(room.getDate());
        assertThat(test.getStartHour()).isEqualTo(room.getStartHour());
        assertThat(test.getEndHour()).isEqualTo(room.getEndHour());
    }

    @Test
    void shouldReturnAndDeleteARoomWhenAnIdIsPassed(){
        given(service.deleteRoom(anyLong())).willReturn(map);

        ResponseEntity<Map<String, Boolean>> response = resource.deleteRoom(ID);
        Map<String, Boolean> test = response.getBody();

        assertThat(response).isNotNull();
        assertThat(test).isNotNull();
        assertThat(response.getClass()).isEqualTo(ResponseEntity.class);

        assertThat(test.getClass()).isEqualTo(map.getClass());
        assertThat(test.containsKey("Delete")).isEqualTo(map.containsKey("Delete"));
        assertThat(test.containsValue(true)).isEqualTo(map.containsValue(true));
    }
}