package com.mattieserver.dto.mapper;

import com.mattieserver.dto.dto.MeterDataDto;
import com.mattieserver.rest.client.MeterData;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface MeterDataMapper {
    MeterDataDto toResource(MeterData data);
}