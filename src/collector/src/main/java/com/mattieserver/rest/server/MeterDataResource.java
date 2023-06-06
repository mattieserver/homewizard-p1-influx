package com.mattieserver.rest.server;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;

import java.io.Console;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;


import com.mattieserver.dto.mapper.MeterDataMapper;
import com.mattieserver.dto.dto.MeterDataMeasurement;
import com.mattieserver.dto.dto.MeterDataDto;
import com.mattieserver.dto.dto.MeterDataActivePowerDto;
import com.mattieserver.rest.server.helpers.MeterDataState;
import com.mattieserver.influxdb.InfluxReader;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.inject.Inject;

@Path("/live")
public class MeterDataResource {

    @Inject
    MeterDataMapper meterDataMapper;

    @Inject
    MeterDataState meterDataState;

    @Inject
    InfluxReader influxReader;

    @GET
    @Path("/data/")
    public MeterDataDto get() {
        return meterDataState.getMeterDataDto();
    }

    @GET
    @Path("/1h-data/")
    public List<MeterDataActivePowerDto> getLastHour() {
        List<MeterDataMeasurement> lasthourmeasurement = influxReader.ReadLastHourFromInflux();
        return meterDataMapper.toActivePowerDtoFromMeasurementList(lasthourmeasurement);
    }    
}
