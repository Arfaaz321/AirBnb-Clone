package com.coingshuttle.projects.airBnbApp.controller;

import com.coingshuttle.projects.airBnbApp.dto.RoomDto;
import com.coingshuttle.projects.airBnbApp.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@PathVariable Long hotelId, @RequestBody RoomDto roomDto) {
        RoomDto createdRoom = roomService.createNewRoom(hotelId, roomDto);
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoom(@PathVariable Long roomId) {
        RoomDto room = roomService.getRoomById(roomId);
        return new ResponseEntity<>(room , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getRoomsByHotelId(@PathVariable Long hotelId) {
        List<RoomDto> rooms = roomService.getAllRoomsInHotel(hotelId);
        return new ResponseEntity<>(rooms , HttpStatus.OK);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable Long roomId) {
        roomService.deleteRoomById(roomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
