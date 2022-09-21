package com.microcode.customer;

import com.microcode.clients.fraud.FraudCheckResponse;
import com.microcode.clients.fraud.FraudClient;
import com.microcode.clients.notification.NotificationClient;
import com.microcode.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final NotificationClient notificationClient;
    private final FraudClient fraudClient;
    public void registerCustomer(CustomerRegistrationRequest request) {

        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        //todo :store customer in database
        customerRepository.saveAndFlush(customer);
        //todo :check if fraudster
       /* FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
            "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );*/


        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(customer.getId());
        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

        //todo: send notification
        notificationClient.sendNotification(
                new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to microcode...",
                        customer.getFirstName())
                )
        );
    }
}
