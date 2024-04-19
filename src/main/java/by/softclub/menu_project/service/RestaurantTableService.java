package by.softclub.menu_project.service;

import by.softclub.menu_project.entity.Restaurant;
import by.softclub.menu_project.entity.RestaurantTable;
import by.softclub.menu_project.entity.dto.RestaurantTableDto;
import by.softclub.menu_project.repository.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantTableService {
    private final RestaurantTableRepository tableRepository;

    public RestaurantTable addTable(RestaurantTableDto tableDto) {
        RestaurantTable table = new RestaurantTable();
        convertDtoToObject(tableDto, table);
        return tableRepository.save(table);
    }

    public RestaurantTable getTable(Long id) {
        return tableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Table not found with ID: " + id));
    }

    public List<RestaurantTable> getTables() {
        return tableRepository.findAll();
    }

    public RestaurantTable updateTable(Long id, RestaurantTableDto tableDto) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Table not found with ID: " + id));
        convertDtoToObject(tableDto, table);
        return tableRepository.save(table);
    }

    public void deleteTable(Long id) {
        tableRepository.deleteById(id);
    }

    private void convertDtoToObject(RestaurantTableDto tableDto, RestaurantTable table){
        table.setTableNumber(tableDto.getTableNumber());
        table.setSeatsNumber(tableDto.getSeatsNumber());
        table.setIsFree(tableDto.getIsFree());
        // Set the restaurant using the restaurant ID from the DTO
        Restaurant restaurant = new Restaurant();
        restaurant.setId(tableDto.getRestaurant());
        table.setRestaurant(restaurant);
    }
}
