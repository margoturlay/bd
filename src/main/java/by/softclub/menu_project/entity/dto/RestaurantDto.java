package by.softclub.menu_project.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RestaurantDto {

    private String name;

    private String phone;

    private String address;

    private String info;

    private List<Long> dishes;

    private List<Long> menuCategories;

    private List<Long> restaurantTables;
}
