package com.example.serviceribbon;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.client.RestTemplate;

public class UserCommand extends HystrixCommand<String> {

    private RestTemplate template;
    private Long id;


    protected UserCommand(Setter setter, RestTemplate restTemplate, Long id) {
        super(setter);
        template = restTemplate;
        this.id = id;
    }

    @Override
    protected String run() throws Exception {
        return template.getForObject("http://service-hi/hello", String.class);
    }

    /**
     * 服务降级处理
     * @return
     */
    @Override
    protected String getFallback() {
        return Strings.EMPTY;
    }
}
