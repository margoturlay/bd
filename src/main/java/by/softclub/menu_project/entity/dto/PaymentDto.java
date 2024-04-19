package by.softclub.menu_project.entity.dto;

import by.softclub.menu_project.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PaymentDto {

    private Double cost;

    private Long order;
}
