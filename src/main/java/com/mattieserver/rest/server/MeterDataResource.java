package com.mattieserver.rest.server;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.mattieserver.rest.client.MeterData;
import com.mattieserver.rest.client.MeterDataService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/live")
public class MeterDataResource {
    @RestClient 
    MeterDataService meterDataService;

    @GET
    @Path("/data/")
    @Blocking
    public MeterData id(String id) {
        return meterDataService.getMeterData();
    }

    @GET
    @Path("/data-async/")
    public Uni<MeterData> getAsync() {
        return meterDataService.getMeterDataAsync();
    }
    
}
