package ru.kpfu.itis.config;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.kpfu.itis.service.TripsService;
import ru.kpfu.itis.service.impl.TripsServiceImpl;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    @Autowired
    TripsService tripsService;

    @Bean
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public Endpoint endpoint() {
        Endpoint endpoint = new EndpointImpl(springBus(),
                tripsService);
        endpoint.publish("http://localhost:8081/services/tripService");
        return endpoint;
    }
}
