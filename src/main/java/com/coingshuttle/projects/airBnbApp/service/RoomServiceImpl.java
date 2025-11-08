package com.coingshuttle.projects.airBnbApp.service;

import com.coingshuttle.projects.airBnbApp.dto.RoomDto;
import com.coingshuttle.projects.airBnbApp.entity.Hotel;
import com.coingshuttle.projects.airBnbApp.entity.Room;
import com.coingshuttle.projects.airBnbApp.exception.ResourceNotFoundException;
import com.coingshuttle.projects.airBnbApp.repository.HotelRepository;
import com.coingshuttle.projects.airBnbApp.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;

    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()->new ResourceNotFoundException("Hotel not found with id: "+hotelId));
        log.info("Creating new room in hotel with id: {}", hotelId);
        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);
        roomRepository.save(room);
        log.info("Room created with id: {}", room.getId());

        if (hotel.getActive()){
            inventoryService.InitializeRoomForAYear(room);
            log.info("Initialized inventory for room with id: {} for one year", room.getId());
        }

        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public RoomDto getRoomById(Long RoomId) {
        log.info("Fetching room with id: {}", RoomId);
        Room room = roomRepository
                .findById(RoomId)
                .orElseThrow(()->new ResourceNotFoundException("Room not found with id: "+RoomId));
        log.info("Room found with id: {}", room.getId());
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Fetching all rooms in hotel with id: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()->new ResourceNotFoundException("Hotel not found with id: "+hotelId));
        return hotel.getRooms()
                .stream()
                .map(room -> modelMapper.map(room, RoomDto.class))
                .toList();
    }

    @Override
    public RoomDto updateRoomById(Long roomId, RoomDto roomDto) {
        return null;
    }

    @Override
    @Transactional
    public void deleteRoomById(Long roomId) {
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(()->new ResourceNotFoundException("Room not found with id: "+roomId));
        log.info("Deleting room with id: {}", roomId);
        inventoryService.deleteFutureInventories(room);
        roomRepository.deleteById(roomId);
        log.info("Room deleted with id: {}", roomId);
    }
}
