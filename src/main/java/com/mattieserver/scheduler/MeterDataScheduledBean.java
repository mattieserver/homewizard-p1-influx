package com.mattieserver.scheduler;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.mattieserver.rest.client.MeterData;
import com.mattieserver.rest.client.MeterDataService;

import io.quarkus.scheduler.Scheduled;
import org.jboss.logging.Logger;

@ApplicationScoped              
public class MeterDataScheduledBean { 

    private static final Logger LOG = Logger.getLogger(MeterDataScheduledBean.class);

    @RestClient 
    MeterDataService meterDataService;

    @Scheduled(every="1s")     
    void increment() {
        MeterData meterDdata =  meterDataService.getMeterDataAsync().await().atMost(Duration.ofSeconds(1));
        LOG.info(meterDdata.active_voltage_l1_v);
    }

}