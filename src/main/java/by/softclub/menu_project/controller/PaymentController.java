package by.softclub.menu_project.controller;

import by.softclub.menu_project.entity.Payment;
import by.softclub.menu_project.entity.dto.PaymentDto;
import by.softclub.menu_project.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/payments",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/add")
    public ResponseEntity<Void> addPayment(@RequestBody PaymentDto paymentDto){
        paymentService.add(paymentDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/all")
    public List<Payment> getPayments(){
        return paymentService.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable("id") Long id){
        return ResponseEntity.ok(paymentService.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Payment> updatePayment(@RequestBody PaymentDto paymentDto, @PathVariable("id") Long id){
        return ResponseEntity.ok(paymentService.update(paymentDto, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable("id") Long id){
        paymentService.delete(id);
        return ResponseEntity.ok().build();
    }
}
