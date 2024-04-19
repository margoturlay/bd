package by.softclub.menu_project.controller;

import by.softclub.menu_project.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/info")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/get")
    public List<ReportService.ReportData> getInfo(){
        return reportService.getInfoAboutRestaurantsAndDishes();
    }
}
