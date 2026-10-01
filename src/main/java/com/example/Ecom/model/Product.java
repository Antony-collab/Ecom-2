package com.example.Ecom.model;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data 
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private int id;

    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date releaseDate;
    private boolean available;
    private String imageName;
    private String imageType;
@Lob 
    private byte[] imageData;

    
}