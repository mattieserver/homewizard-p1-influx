package com.mattieserver.dto.dto;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MeterDataState {

    private MeterDataDto meterDataDto;

    public MeterDataDto getMeterDataDto() {
        return meterDataDto;
    }

    public void setMeterDataDto(MeterDataDto meterDataDto) {
        this.meterDataDto = meterDataDto;
    }
    
}
