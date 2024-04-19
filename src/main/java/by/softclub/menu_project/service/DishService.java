package by.softclub.menu_project.service;

import by.softclub.menu_project.entity.Dish;
import by.softclub.menu_project.entity.dto.DishDto;
import by.softclub.menu_project.repository.DishRepository;
import by.softclub.menu_project.repository.MenuCategoryRepository;
import by.softclub.menu_project.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuCategoryRepository menuCategoryRepository;

    public void add(DishDto dishDto) {
        Dish dish = new Dish();
        convertDtoToObject(dishDto, dish);
        dishRepository.save(dish);
    }


    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    public Dish getById(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found"));
    }

    public Dish update(DishDto dishDto, Long id){
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found"));
        convertDtoToObject(dishDto, dish);
        dishRepository.save(dish);
        return dish;
    }

    public void delete(Long restId) {
        dishRepository.deleteById(restId);
    }

    public void convertDtoToObject(DishDto dishDto, Dish dish){
        BeanUtils.copyProperties(dishDto, dish, "restaurant", "menuCategory");
        dish.setRestaurant(restaurantRepository.findById(dishDto.getRestaurant())
                .orElseThrow(() -> new RuntimeException("Restaurant not found")));
        dish.setMenuCategory(menuCategoryRepository.findById(dishDto.getMenuCategory())
                .orElseThrow(() -> new RuntimeException("Menu category not found")));
    }
}
