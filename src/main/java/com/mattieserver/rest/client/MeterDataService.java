package com.mattieserver.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import io.smallrye.mutiny.Uni;

@Path("/v1/data")
@RegisterRestClient(configKey="meter-api")
public interface MeterDataService {

    @GET
    MeterData getMeterData();

    @GET
    Uni<MeterData> getMeterDataAsync();
    
}
