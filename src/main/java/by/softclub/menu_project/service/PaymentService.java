package by.softclub.menu_project.service;

import by.softclub.menu_project.entity.Payment;
import by.softclub.menu_project.entity.dto.PaymentDto;
import by.softclub.menu_project.repository.OrderRepository;
import by.softclub.menu_project.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderService orderService;

    public void add(PaymentDto paymentDto){
        Payment payment = new Payment();
        convertDtoToObject(paymentDto, payment);
        paymentRepository.save(payment);
    }

    public Payment getById(Long id){
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public List<Payment> getAll(){
        return paymentRepository.findAll();
    }

    public Payment update(PaymentDto paymentDto, Long id){
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        convertDtoToObject(paymentDto, payment);
        paymentRepository.save(payment);
        return payment;
    }

    public void delete(Long id){
        paymentRepository.deleteById(id);
    }

    public void convertDtoToObject(PaymentDto paymentDto, Payment payment){
        BeanUtils.copyProperties(paymentDto, payment, "order", "date");
        payment.setDate(LocalDateTime.now());
        payment.setOrder(orderService.getById(paymentDto.getOrder()));
    }
}
