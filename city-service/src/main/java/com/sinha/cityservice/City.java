package com.sinha.cityservice;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String cityName;
}
