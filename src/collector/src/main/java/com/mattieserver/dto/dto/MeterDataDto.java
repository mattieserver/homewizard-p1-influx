package com.mattieserver.dto.dto;

import java.time.Instant;

public class MeterDataDto {
    public String unique_id;
    public float total_power_import_kwh;
    public float total_power_import_t1_kwh;
    public float total_power_import_t2_kwh;
    public float total_power_export_kwh;
    public float total_power_export_t1_kwh;
    public float total_power_export_t2_kwh;
    public short active_power_w;
    public short active_power_l1_w;
    public float active_voltage_l1_v;
    public float active_current_l1_a;
    public Instant timestamp;

    public MeterDataDto() {
        super();
        timestamp = Instant.now();
    }

    public MeterDataDto(Instant set_instant) {
        super();
        timestamp = set_instant;
    }
}
