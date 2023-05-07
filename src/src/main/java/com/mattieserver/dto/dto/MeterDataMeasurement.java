package com.mattieserver.dto.dto;

import java.time.Instant;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

@Measurement(name = "meterdata")
public class MeterDataMeasurement {
    @Column(tag = true)
    public String unique_id;
    @Column
    public float total_power_import_kwh;
    @Column
    public float total_power_import_t1_kwh;
    @Column
    public float total_power_import_t2_kwh;
    @Column
    public float total_power_export_kwh;
    @Column
    public float total_power_export_t1_kwh;
    @Column
    public float total_power_export_t2_kwh;
    @Column
    public short active_power_w;
    @Column
    public short active_power_l1_w;
    @Column
    public float active_voltage_l1_v;
    @Column
    public float active_current_l1_a;

    @Column(timestamp = true)
    Instant time = Instant.now();
}
