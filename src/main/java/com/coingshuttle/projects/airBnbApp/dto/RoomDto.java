package com.coingshuttle.projects.airBnbApp.dto;

import com.coingshuttle.projects.airBnbApp.entity.Hotel;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RoomDto {
    private Long id;
    private String type;
    private BigDecimal basePrice;
    private Integer capacity;
    private String[] photos;
    private String[] amenities;
    private Integer totalCount;
}
