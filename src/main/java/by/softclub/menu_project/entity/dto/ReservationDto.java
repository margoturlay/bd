package by.softclub.menu_project.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationDto {

    private Integer guestsNumber;

    private int status;

    private Long user;
}
