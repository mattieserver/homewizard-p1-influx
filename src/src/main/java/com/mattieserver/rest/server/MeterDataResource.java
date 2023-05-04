package com.mattieserver.rest.server;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.mattieserver.rest.client.MeterData;
import com.mattieserver.rest.client.MeterDataService;
import com.mattieserver.dto.mapper.MeterDataMapper;
import com.mattieserver.dto.dto.MeterDataDto;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.inject.Inject;

@Path("/live")
public class MeterDataResource {
    @RestClient 
    MeterDataService meterDataService;

    @Inject
    MeterDataMapper meterDataMapper;

    @GET
    @Path("/data/")
    public Uni<MeterDataDto> getAsync() {
        return meterDataService.getMeterDataAsync().map(meterDataMapper::toResource);
    }
    
}
