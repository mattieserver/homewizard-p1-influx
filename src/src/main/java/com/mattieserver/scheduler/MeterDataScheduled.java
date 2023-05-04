package com.mattieserver.scheduler;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;
import jakarta.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.mattieserver.rest.client.MeterData;
import com.mattieserver.rest.client.MeterDataService;
import com.mattieserver.dto.mapper.MeterDataMapper;
import com.mattieserver.dto.dto.MeterDataDto;

import io.quarkus.scheduler.Scheduled;
import org.jboss.logging.Logger;

@ApplicationScoped              
public class MeterDataScheduled { 

    private static final Logger LOG = Logger.getLogger(MeterDataScheduled.class);

    @RestClient 
    MeterDataService meterDataService;

    @Inject
    MeterDataMapper meterDataMapper;

    @Scheduled(every="1s")     
    void increment() {
        MeterData meterDdata =  meterDataService.getMeterDataAsync().await().atMost(Duration.ofSeconds(1));
        LOG.info(meterDdata.active_power_w);
        MeterDataDto meterDdataDto = meterDataMapper.toResource(meterDdata);

    }

}