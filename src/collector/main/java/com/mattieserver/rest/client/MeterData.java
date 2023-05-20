package com.mattieserver.rest.client;

public class MeterData {
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
    public short active_tariff;
}