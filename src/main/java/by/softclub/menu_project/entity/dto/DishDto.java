package by.softclub.menu_project.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DishDto {

    private Long restaurant;

    private String name;

    private Double cost;

    private Long menuCategory;
}
