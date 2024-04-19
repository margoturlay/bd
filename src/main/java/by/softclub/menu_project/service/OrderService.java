package by.softclub.menu_project.service;

import by.softclub.menu_project.entity.Dish;
import by.softclub.menu_project.entity.Order;
import by.softclub.menu_project.entity.OrderStatus;
import by.softclub.menu_project.entity.dto.OrderDto;
import by.softclub.menu_project.entity.dto.OrderStatusDto;
import by.softclub.menu_project.entity.user.User;
import by.softclub.menu_project.repository.DishRepository;
import by.softclub.menu_project.repository.OrderRepository;
import by.softclub.menu_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final UserService userService;

    public void add(OrderDto orderDto){
        Order order = new Order();
        convertDtoToObject(orderDto, order);
        orderRepository.save(order);
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public Order getById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order update(OrderDto orderDto, Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        convertDtoToObject(orderDto, order);
        orderRepository.save(order);
        return order;
    }

    public Order updateStatus(OrderStatusDto orderStatusDto, Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        for(OrderStatus status: OrderStatus.values()){
            if (status.getCode() == orderStatusDto.getStatus()){
                order.setStatus(status);
                break;
            }
        }
        orderRepository.save(order);
        return order;
    }

    public void delete(Long id){
        orderRepository.deleteById(id);
    }

    public void convertDtoToObject(OrderDto orderDto,Order order){
        BeanUtils.copyProperties(orderDto, order, "status", "dishes", "user");
        User user = userService.getById(orderDto.getUser());
        order.setUser(user);
        for(OrderStatus status: OrderStatus.values()){
            if (status.getCode() == orderDto.getStatus()){
                order.setStatus(status);
                break;
            }
        }
        List<Dish> dishes = dishRepository.findAllByIds(orderDto.getDishes());
        order.setDishes(dishes);
        Double totalCost = 0d;
        for(Dish dish : dishes){
            totalCost += dish.getCost();
        }
        order.setCost(totalCost);
        order.setDate(LocalDateTime.now());
    }

}
