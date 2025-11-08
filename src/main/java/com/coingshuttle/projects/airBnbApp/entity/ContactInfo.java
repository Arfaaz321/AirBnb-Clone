package com.coingshuttle.projects.airBnbApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ContactInfo {

    private String email;

    private String phoneNumber;

    private String address;

    private String location;
}
