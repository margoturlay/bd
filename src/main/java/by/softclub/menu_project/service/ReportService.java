package by.softclub.menu_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public List<ReportData> getInfoAboutRestaurantsAndDishes(){
        String sql = """
        SELECT
        rest.name    as restaurantName,
        mc.name      as menuCategoryName,
        d.name       as dishName,
        d.cost       as dishCost,
        rest.address as restaurantAddress,
        rest.phone   as restaurantPhone
        FROM restaurant rest
                 INNER JOIN menu_category mc
                            ON rest.id = mc.restaurant_id
                 INNER JOIN dish d
                            ON mc.id = d.menu_category_id
                """;

        return jdbcTemplate.query(sql, DataClassRowMapper.newInstance(ReportData.class));
    }


    public record ReportData(
        String restaurantName,
        String menuCategoryName,
        String dishName,
        double dishCost,
        String restaurantAddress,
        String restaurantPhone) {}
}
