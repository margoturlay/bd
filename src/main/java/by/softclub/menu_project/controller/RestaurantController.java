package by.softclub.menu_project.controller;

import by.softclub.menu_project.entity.Restaurant;
import by.softclub.menu_project.entity.dto.RestaurantDto;
import by.softclub.menu_project.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurants",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RestaurantController {
    
    private final RestaurantService restaurantService;

    @PostMapping("/add")
    public ResponseEntity<Void> addRestaurant(@RequestBody RestaurantDto restaurantDto) {
        restaurantService.add(restaurantDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/all")
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable("id") Long id) {
        return ResponseEntity.ok(restaurantService.getById(id));
    }

    @PutMapping("/update/{restId}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody RestaurantDto restaurantDto, @PathVariable Long restId) {
        Restaurant updatedRestaurant = restaurantService.update(restaurantDto, restId);
        return ResponseEntity.ok(updatedRestaurant);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable("id") Long restId) {
        try {
            restaurantService.delete(restId);
            return new ResponseEntity<>("Restaurant with ID " + restId + " has been deleted.", HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("Restaurant with ID " + restId + " not found.", HttpStatus.NOT_FOUND);
        }
    }

}
