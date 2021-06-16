package com.example.serviceribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/ribbon-consumer")
    @HystrixCommand(fallbackMethod = "hiError")
    public String helloConsumer(String name) {
        return restTemplate.getForEntity("http://SERVICE-HI/hello", String.class).getBody();
    }

    /**
     * Throwable e 针对不同异常做针对处理
     * @param name
     * @param e
     * @return
     */
    public String hiError(String name, Throwable e) {
        if (e instanceof IllegalArgumentException) {
            return "1";
        }

        return "hi," + name + ",sorry,error!";
    }

}
