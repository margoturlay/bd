package by.softclub.menu_project.service;

import by.softclub.menu_project.entity.Dish;
import by.softclub.menu_project.entity.MenuCategory;
import by.softclub.menu_project.entity.dto.MenuCategoryDto;
import by.softclub.menu_project.repository.DishRepository;
import by.softclub.menu_project.repository.MenuCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuCategoryService {

    private final MenuCategoryRepository menuCategoryRepository;
    private final DishRepository dishRepository;
    private final RestaurantService restaurantService;

    public void add(MenuCategoryDto menuCategoryDto){
        MenuCategory menuCategory = new MenuCategory();
        convertDtoToObject(menuCategoryDto, menuCategory);
        List<Dish> dishes = dishRepository.findAllByIds(menuCategoryDto.getDishes());
        menuCategory.setDishes(dishes);
        menuCategoryRepository.save(menuCategory);
    }

    public List<MenuCategory> getAll(){
        return menuCategoryRepository.findAll();
    }

    public MenuCategory getById(Long id){
        return menuCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu category not found"));
    }

    public MenuCategory update(MenuCategoryDto menuCategoryDto, Long id){
        MenuCategory menuCategory = menuCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu category not found"));
        convertDtoToObject(menuCategoryDto, menuCategory);
        menuCategoryRepository.save(menuCategory);
        return menuCategory;
    }

    public void delete(Long id){
        menuCategoryRepository.deleteById(id);
    }

    public void convertDtoToObject(MenuCategoryDto menuCategoryDto, MenuCategory menuCategory){
        BeanUtils.copyProperties(menuCategoryDto, menuCategory, "dishes");
        menuCategory.setRestaurant(restaurantService.getById(menuCategoryDto.getRestaurant()));
    }

}
