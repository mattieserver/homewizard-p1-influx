package com.mattieserver.dto.mapper;

import com.mattieserver.dto.dto.MeterDataDto;
import com.mattieserver.dto.dto.MeterDataMeasurement;
import com.mattieserver.dto.dto.MeterDataActivePowerDto;
import com.mattieserver.rest.client.MeterData;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface MeterDataMapper {
    @Mapping(target = "time", ignore = true)
    MeterDataDto toResource(MeterData data);
       
    MeterDataMeasurement toMeasurementFromDto(MeterDataDto data);

    @Mapping(target = "time", ignore = true)
    MeterDataMeasurement toMeasurement(MeterData data);

    List<MeterDataDto> toResourceFromMeasurementList(List<MeterDataMeasurement> measurement);
    
    @Mapping(target = "epochValue", ignore = true)
    List<MeterDataActivePowerDto> toActivePowerDtoFromMeasurementList(List<MeterDataMeasurement> data);
}