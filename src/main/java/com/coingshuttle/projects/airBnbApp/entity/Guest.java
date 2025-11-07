package com.coingshuttle.projects.airBnbApp.entity;

import com.coingshuttle.projects.airBnbApp.entity.utils.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "guests", fetch = FetchType.LAZY)
    private Set<Booking> bookings;
    //do not make another join table here, use the existing booking table
}
