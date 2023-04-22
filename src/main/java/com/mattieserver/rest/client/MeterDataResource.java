package com.mattieserver.rest.client;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

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
