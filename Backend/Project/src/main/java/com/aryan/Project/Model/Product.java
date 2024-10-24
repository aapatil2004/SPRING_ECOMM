package com.aryan.Project.Model;

import java.math.BigDecimal;
import java.sql.Date;

// import org.hibernate.annotations.Columns;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // it is lombok annotation that provides you with all the common methods
      // automatically(toString , equals(),
      // hashCode())
@AllArgsConstructor // creates parameterized constructor
// public Product(int id, String name, String desc, String brand, BigDecimal
// price, String category, Date releaseDate, boolean available, int quantity)
@NoArgsConstructor // creates default constructor Product()
@Entity // (Java Persistence API (JPA)) tells that the given class is used as
        // relation(table in database) object relation model
@Table(name = "product")
public class Product {
    @Id // tels that id is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // used for autoincrement and allocate the unique id
    private Integer id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "product_available")
    private boolean productAvailable;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_type")
    private String imageType;

    @Column(name = "image_date")
    @Lob // For large objects
    private byte[] imageDate;

    public void setImage(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            throw new IllegalArgumentException("Image data must not be null or empty.");
        }
        this.imageDate = bytes;
    }

}
