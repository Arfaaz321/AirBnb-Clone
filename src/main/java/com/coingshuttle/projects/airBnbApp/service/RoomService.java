package com.coingshuttle.projects.airBnbApp.service;

import com.coingshuttle.projects.airBnbApp.dto.RoomDto;

import java.util.List;

public interface RoomService {
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto);
    public RoomDto getRoomById(Long RoomId);
    public List<RoomDto> getAllRoomsInHotel(Long hotelId);
    public RoomDto updateRoomById(Long roomId, RoomDto roomDto);
    public void deleteRoomById(Long roomId);
}
