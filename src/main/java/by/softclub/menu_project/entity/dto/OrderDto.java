package by.softclub.menu_project.entity.dto;

import by.softclub.menu_project.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDto {

    private LocalDateTime date;

    private int status;

    private List<Long> dishes;

    private final Long user;
}
