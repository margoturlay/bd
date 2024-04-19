package by.softclub.menu_project.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "restaurant")
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "info")
    private String info;

    @OneToMany(mappedBy = "restaurant")
    @JsonManagedReference
    private List<Dish> dishes;

    @OneToMany(mappedBy = "restaurant")
    @JsonManagedReference
    private List<MenuCategory> menuCategories;

    @OneToMany(mappedBy = "restaurant")
    @JsonManagedReference
    private List<RestaurantTable> restaurantTables;
}