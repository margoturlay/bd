package by.softclub.menu_project.controller;

import by.softclub.menu_project.entity.RestaurantTable;
import by.softclub.menu_project.entity.dto.RestaurantTableDto;
import by.softclub.menu_project.service.RestaurantTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tables")
public class RestaurantTableController {
    private final RestaurantTableService tableService;

    @PostMapping("/add")
    public ResponseEntity<RestaurantTable> addTable(@RequestBody RestaurantTableDto tableDto) {
        RestaurantTable table = tableService.addTable(tableDto);
        return ResponseEntity.ok(table);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RestaurantTable> getTable(@PathVariable("id") Long id) {
        RestaurantTable table = tableService.getTable(id);
        return ResponseEntity.ok(table);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<RestaurantTable>> getTables() {
        List<RestaurantTable> tables = tableService.getTables();
        return ResponseEntity.ok(tables);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RestaurantTable> updateTable(@PathVariable("id") Long id, @RequestBody RestaurantTableDto tableDto) {
        RestaurantTable table = tableService.updateTable(id, tableDto);
        return ResponseEntity.ok(table);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable("id") Long id) {
        tableService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }
}
