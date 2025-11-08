package com.coingshuttle.projects.airBnbApp.dto;

import com.coingshuttle.projects.airBnbApp.entity.ContactInfo;
import lombok.Data;

@Data
public class HotelDto {
    private Long id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private ContactInfo contactInfo;
    private Boolean active;
}
