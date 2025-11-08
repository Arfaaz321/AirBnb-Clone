package com.coingshuttle.projects.airBnbApp.controller;

import com.coingshuttle.projects.airBnbApp.dto.HotelDto;
import com.coingshuttle.projects.airBnbApp.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/hotels")
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDto> createHotel(@RequestBody HotelDto hotelDto) {
        log.info("Received request to create hotel: {}", hotelDto.getName());
        HotelDto createdHotel = hotelService.createNewHotel(hotelDto);
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long hotelId) {
        HotelDto hotelDto = hotelService.getHotelById(hotelId);
        return new ResponseEntity<>(hotelDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDto> updateHotelByHotelId(@PathVariable Long id, @RequestBody HotelDto hotelDto) {
        HotelDto hotelDt = hotelService.updateHotelById(id, hotelDto);
        return new ResponseEntity<>(hotelDt, HttpStatus.OK);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotelById(@PathVariable Long hotelId) {
        hotelService.deleteHotelById(hotelId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{hotelId}")
    public ResponseEntity<Void> activateHotelById(@PathVariable Long hotelId) {
        hotelService.activateHotelById(hotelId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
