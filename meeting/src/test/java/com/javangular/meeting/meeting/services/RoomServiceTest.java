package com.javangular.meeting.meeting.services;

import com.javangular.meeting.meeting.entities.Room;
import com.javangular.meeting.meeting.exceptions.RoomNotFound;
import com.javangular.meeting.meeting.repositories.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
class RoomServiceTest {

    @InjectMocks
    RoomService service;

    @Mock
    RoomRepository repository;

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

    //Fazer teste de exception
    @Test
    void shouldReturnAListOfRoomsWhenIsCalled(){
        given(repository.findAll()).willReturn(list);

        List<Room> test = service.findAll();

        assertThat(test).isNotNull();
        assertThat(test.getClass()).isEqualTo(list.getClass());

        assertThat(test.get(INDEX).getId()).isEqualTo(list.get(INDEX).getId());
        assertThat(test.get(INDEX).getName()).isEqualTo(list.get(INDEX).getName());
        assertThat(test.get(INDEX).getDate()).isEqualTo(list.get(INDEX).getDate());
        assertThat(test.get(INDEX).getStartHour()).isEqualTo(list.get(INDEX).getStartHour());
        assertThat(test.get(INDEX).getEndHour()).isEqualTo(list.get(INDEX).getEndHour());
    }

    @Test
    void shouldReturnARoomWhenAnIdIsPassed(){
        given(repository.findById(anyLong())).willReturn(optional);

        Room test = service.findById(ID);

        assertThat(test).isNotNull();
        assertThat(test.getClass()).isEqualTo(room.getClass());

        assertThat(test.getId()).isEqualTo(room.getId());
        assertThat(test.getName()).isEqualTo(room.getName());
        assertThat(test.getDate()).isEqualTo(room.getDate());
        assertThat(test.getStartHour()).isEqualTo(room.getStartHour());
        assertThat(test.getEndHour()).isEqualTo(room.getEndHour());
    }

    @Test
    void shouldThrowAnExceptionWhenAnEmptyOptionIsPassed(){
        given(repository.findById(anyLong())).willReturn(Optional.empty());

        Assertions.assertThrows(RoomNotFound.class, () -> service.findById(ID), "Room not found for this id: " + ID);
    }

    @Test
    void shouldReturnAndSaveARoomWhenARoomIsPassed(){
        given(repository.save(any(Room.class))).willReturn(room);

        Room test = service.createRoom(room);

        assertThat(test).isNotNull();
        assertThat(test.getClass()).isEqualTo(room.getClass());

        assertThat(test.getId()).isEqualTo(room.getId());
        assertThat(test.getName()).isEqualTo(room.getName());
        assertThat(test.getDate()).isEqualTo(room.getDate());
        assertThat(test.getStartHour()).isEqualTo(room.getStartHour());
        assertThat(test.getEndHour()).isEqualTo(room.getEndHour());

    }

    @Test
    void shouldReturnAndUpdateARoomWhenARoomIsPassed(){
        given(repository.save(any(Room.class))).willReturn(room);
        given(repository.findById(anyLong())).willReturn(optional);

        Room test = service.updateRoom(ID, room);

        assertThat(test).isNotNull();
        assertThat(test.getClass()).isEqualTo(room.getClass());

        assertThat(test.getId()).isEqualTo(room.getId());
        assertThat(test.getName()).isEqualTo(room.getName());
        assertThat(test.getDate()).isEqualTo(room.getDate());
        assertThat(test.getStartHour()).isEqualTo(room.getStartHour());
        assertThat(test.getEndHour()).isEqualTo(room.getEndHour());
    }

    @Test
    void shouldReturnAndDeleteARoomWhenAnIdIsPassed(){
        given(repository.findById(anyLong())).willReturn(optional);
        doNothing().when(repository).delete(any(Room.class));

        Map<String, Boolean> test = service.deleteRoom(ID);

        assertThat(test.getClass()).isEqualTo(map.getClass());
        assertThat(test).isNotNull();

        assertThat(test.containsKey("Delete")).isEqualTo(map.containsKey("Delete"));
        assertThat(test.containsValue(true)).isEqualTo(map.containsValue(true));

    }
}