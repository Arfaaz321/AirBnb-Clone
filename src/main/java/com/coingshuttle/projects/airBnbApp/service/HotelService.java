package com.coingshuttle.projects.airBnbApp.service;

import com.coingshuttle.projects.airBnbApp.dto.HotelDto;
import com.coingshuttle.projects.airBnbApp.entity.Hotel;

public interface HotelService {
    public HotelDto createNewHotel(HotelDto hotelDto);
    public HotelDto getHotelById(Long id);
    public HotelDto updateHotelById(Long id, HotelDto hotelDto);
    public void deleteHotelById(Long id);
    public void activateHotelById(Long id);
}

