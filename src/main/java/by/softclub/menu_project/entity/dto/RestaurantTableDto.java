package by.softclub.menu_project.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestaurantTableDto {

    private Integer tableNumber;

    private Integer seatsNumber;

    private Boolean isFree;

    private Long restaurant;
}
