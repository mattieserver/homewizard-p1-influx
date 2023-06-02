package com.mattieserver.rest.server;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;


import com.mattieserver.dto.mapper.MeterDataMapper;
import com.mattieserver.dto.dto.MeterDataDto;
import com.mattieserver.dto.dto.MeterDataState;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.inject.Inject;

@Path("/live")
public class MeterDataResource {

    @Inject
    MeterDataMapper meterDataMapper;

    @Inject
    MeterDataState meterDataState;

    @GET
    @Path("/data/")
    public MeterDataDto get() {
        return meterDataState.getMeterDataDto();
    }
    
}
