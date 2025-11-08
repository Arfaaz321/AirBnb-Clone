package com.coingshuttle.projects.airBnbApp.service;

import com.coingshuttle.projects.airBnbApp.dto.HotelDto;
import com.coingshuttle.projects.airBnbApp.entity.Hotel;
import com.coingshuttle.projects.airBnbApp.entity.Room;
import com.coingshuttle.projects.airBnbApp.exception.ResourceNotFoundException;
import com.coingshuttle.projects.airBnbApp.repository.HotelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepository;

    private final ModelMapper modelMapper;

    private final InventoryService inventoryService;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating new hotel with name: {}", hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);
        hotelRepository.save(hotel);
        log.info("Hotel created with id: {}", hotel.getId());
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Fetching hotel with id: {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Hotel not found with id: "+id));
        log.info("Hotel found: {}", hotel.getName());
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("Updating hotel with id: {}", id);
        Hotel existingHotel = hotelRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Hotel not found with id: "+id));
        modelMapper.map(hotelDto, existingHotel);
        existingHotel.setId(id);
        existingHotel = hotelRepository.save(existingHotel);
        return modelMapper.map(existingHotel, HotelDto.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(Long id){
        log.info("Deleting hotel with id: {}", id);
        Hotel existingHotel = hotelRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Hotel not found with id: "+id));
        hotelRepository.delete(existingHotel);

        for (Room room : existingHotel.getRooms()) {
            inventoryService.deleteFutureInventories(room);
            log.info("Deleted future inventories for room with id: {}", room.getId());
        }

        log.info("Hotel deleted with id: {}", id);
    }

    @Override
    @Transactional
    public void activateHotelById(Long id){
        log.info("Activating hotel with id: {}", id);
        Hotel existingHotel = hotelRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Hotel not found with id: "+id));
        existingHotel.setActive(true);

        //assuming that you will do it only once
        for (Room room : existingHotel.getRooms()) {
            inventoryService.InitializeRoomForAYear(room);
            log.info("Initialized inventory for room with id: {} for one year", room.getId());
        }

        log.info("Hotel activated with id: {}", id);
    }
}
