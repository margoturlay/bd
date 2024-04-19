package by.softclub.menu_project.controller;

import by.softclub.menu_project.entity.MenuCategory;
import by.softclub.menu_project.entity.dto.MenuCategoryDto;
import by.softclub.menu_project.service.MenuCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/menu_categories",
                produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class MenuCategoryController {

    private final MenuCategoryService menuCategoryService;

    @PostMapping("/add")
    @Operation(summary = "Add menu category")
    public ResponseEntity<Void> addMenuCategory(@RequestBody MenuCategoryDto menuCategoryDto){
        menuCategoryService.add(menuCategoryDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/all")
    @Operation(summary = "Get all categories")
    public List<MenuCategory> getMenuCategories(){
        return menuCategoryService.getAll();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get category")
    public ResponseEntity<MenuCategory> getMenuCategory(@PathVariable("id") Long id){
        return ResponseEntity.ok(menuCategoryService.getById(id));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update category")
    public ResponseEntity<MenuCategory> updateMenuCategory(@RequestBody MenuCategoryDto menuCategoryDto, @PathVariable("id") Long id){
        return ResponseEntity.ok(menuCategoryService.update(menuCategoryDto, id));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete category")
    public ResponseEntity<Void> deleteMenuCategory(@PathVariable("id") Long id){
        menuCategoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
