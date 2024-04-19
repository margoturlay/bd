package by.softclub.menu_project.controller;

import by.softclub.menu_project.entity.Order;
import by.softclub.menu_project.entity.dto.OrderDto;
import by.softclub.menu_project.entity.dto.OrderStatusDto;
import by.softclub.menu_project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders",
                produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<Void> addOrder(@RequestBody OrderDto orderDto){
        orderService.add(orderDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/all")
    public List<Order> getOrders(){
        return orderService.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long id){
        return ResponseEntity.ok(orderService.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody OrderDto orderDto, @PathVariable("id") Long id){
        return ResponseEntity.ok(orderService.update(orderDto, id));
    }

    @PutMapping("/update/status/{id}")
    public ResponseEntity<Order> updateOrderStatus(@RequestBody OrderStatusDto orderStatusDto, @PathVariable("id") Long id){
        return ResponseEntity.ok(orderService.updateStatus(orderStatusDto, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id){
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }
}
