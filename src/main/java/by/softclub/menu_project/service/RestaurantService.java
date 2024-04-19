package by.softclub.menu_project.service;

import by.softclub.menu_project.entity.Dish;
import by.softclub.menu_project.entity.MenuCategory;
import by.softclub.menu_project.entity.Restaurant;
import by.softclub.menu_project.entity.RestaurantTable;
import by.softclub.menu_project.entity.dto.RestaurantDto;
import by.softclub.menu_project.repository.DishRepository;
import by.softclub.menu_project.repository.MenuCategoryRepository;
import by.softclub.menu_project.repository.RestaurantRepository;
import by.softclub.menu_project.repository.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;
    private final RestaurantTableRepository restaurantTableRepository;
    private final MenuCategoryRepository menuCategoryRepository;

    public void add(RestaurantDto restaurantDto){
        Restaurant restaurant = new Restaurant();
        convertDtoToObject(restaurantDto, restaurant);
        restaurantRepository.save(restaurant);
    }

    public Restaurant getById(Long id){
        return restaurantRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Restaurant nor found"));
    }

    public List<Restaurant> getAll(){
        return restaurantRepository.findAll();
    }

    public Restaurant update(RestaurantDto restaurantDto, Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Restaurant not found"));
        convertDtoToObject(restaurantDto, restaurant);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public void delete(Long id){
        restaurantRepository.deleteById(id);
    }

    public void convertDtoToObject(RestaurantDto restaurantDto, Restaurant restaurant) {
        BeanUtils.copyProperties(restaurantDto, restaurant, "dishes", "menuCategories", "restaurantTables");
        List<Dish> dishes = dishRepository.findAllByIds(restaurantDto.getDishes());
        restaurant.setDishes(dishes);
        List<MenuCategory> menuCategories = menuCategoryRepository.findAllByIds(restaurantDto.getMenuCategories());
        restaurant.setMenuCategories(menuCategories);
        List<RestaurantTable> restaurantTables = restaurantTableRepository.findAllByIds(restaurantDto.getRestaurantTables());
        restaurant.setRestaurantTables(restaurantTables);
    }
}
