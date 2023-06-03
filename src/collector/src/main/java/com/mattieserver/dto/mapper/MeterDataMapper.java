package com.mattieserver.dto.mapper;

import com.mattieserver.dto.dto.MeterDataDto;
import com.mattieserver.dto.dto.MeterDataMeasurement;
import com.mattieserver.rest.client.MeterData;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface MeterDataMapper {
    MeterDataDto toResource(MeterData data);
    
    MeterDataMeasurement toMeasurementFromDto(MeterDataDto data);
    MeterDataMeasurement toMeasurement(MeterData data);

    List<MeterDataDto> toResourceFromMeasurementList(List<MeterDataMeasurement> measurement);
}