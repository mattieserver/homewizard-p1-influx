package com.mattieserver.dto.dto;

import java.time.Instant;

public class MeterDataActivePowerDto {
    public String unique_id;
    public short active_power_w;
    private Instant time;

    public void setTime(Instant time) {
        this.time = time;
        this.epochValue = time.toEpochMilli();
    }

    public long epochValue;
    
}
