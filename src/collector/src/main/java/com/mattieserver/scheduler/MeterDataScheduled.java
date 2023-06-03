package com.mattieserver.scheduler;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;
import java.time.Instant;
import jakarta.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.mattieserver.rest.client.MeterDataService;
import com.mattieserver.rest.client.MeterData;
import com.mattieserver.dto.mapper.MeterDataMapper;
import com.mattieserver.influxdb.InfluxWriter;
import com.mattieserver.dto.dto.MeterDataDto;
import com.mattieserver.rest.server.helpers.MeterDataState;

import io.quarkus.scheduler.Scheduled;
import org.jboss.logging.Logger;

@ApplicationScoped              
public class MeterDataScheduled { 

    private static final Logger LOG = Logger.getLogger(MeterDataScheduled.class);

    @RestClient 
    MeterDataService meterDataService;

    @Inject
    MeterDataMapper meterDataMapper;

    @Inject 
    InfluxWriter influxWriter;

    @Inject
    MeterDataState meterDataState;

    @Scheduled(every="1s")
    void increment() {
        MeterData meterData =  meterDataService.getMeterDataAsync().await().atMost(Duration.ofSeconds(1));
        LOG.info(meterData.active_power_w);
        influxWriter.WriteToInflux(meterData);

        MeterDataDto dtoData = meterDataMapper.toResource(meterData);
        dtoData.time = Instant.now();
        meterDataState.setMeterDataDto(dtoData);
    }

}