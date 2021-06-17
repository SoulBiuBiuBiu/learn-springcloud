package com.example.eurekaclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DiscoveryClient client;

    @GetMapping(value = "/hello")
    public String index() {
        List<String> services = client.getServices();
        logger.info(services.toString());
        for (String service :
                services) {
            List<ServiceInstance> instances = client.getInstances(service);
            logger.info(instances.toString());
        }
        return "Hello world!";
    }

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello(@RequestParam String name) {
        return "hello" + name;
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public User hello(@RequestHeader String name, @RequestHeader Integer age) {
        return new User(name, age);
    }

    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    public String hello(@RequestBody User user) {
        return "hello " + user.getName() + ", " + user.getAge();
    }

}
