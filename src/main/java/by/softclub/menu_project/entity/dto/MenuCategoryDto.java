package by.softclub.menu_project.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MenuCategoryDto {

    @NotBlank
    private String name;

    private Long restaurant;

    private List<Long> dishes;

}
