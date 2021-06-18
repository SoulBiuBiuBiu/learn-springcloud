package com.example.feignconsumer.refactor;

import com.example.servicehiapi.dto.User;
import com.example.servicehiapi.service.HelloService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "SERVICE-HI")
public interface RefactorHelloService extends HelloService {

}
