package by.softclub.menu_project.controller;

import by.softclub.menu_project.entity.Dish;
import by.softclub.menu_project.entity.dto.DishDto;
import by.softclub.menu_project.service.DishService;
import by.softclub.menu_project.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/dishes",
                produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;
    private final RestaurantService restaurantService;

    @PostMapping(
            value = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addDish(@RequestBody DishDto dishDto) {
        dishService.add(dishDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/get/all")
    @Operation(summary = "Get list of dishes")
    public List<Dish> getAllDishes() {
        return dishService.getAll();
    }


    @GetMapping(value = "/get/{id}")
    @Operation(summary = "Get dish")
    public ResponseEntity<Dish> getDish(@PathVariable("id") Long dish_id) {
        return ResponseEntity.ok(dishService.getById(dish_id));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update dish")
    public ResponseEntity<Dish> updateDish(@RequestBody DishDto dishDto, @PathVariable("id") Long id) {
        Dish dish = dishService.update(dishDto, id);
        return ResponseEntity.ok(dish);
    }


    @Operation(summary = "Delete dish")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDish(@PathVariable("id") Long dishId) {
        try {
            dishService.delete(dishId);
            return new ResponseEntity<>("Dish with ID " + dishId + " has been deleted.", HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("Dish with ID " + dishId + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
