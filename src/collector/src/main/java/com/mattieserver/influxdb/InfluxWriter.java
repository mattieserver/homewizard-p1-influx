package com.mattieserver.influxdb;


import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.reactive.InfluxDBClientReactive;
import com.influxdb.client.reactive.InfluxDBClientReactiveFactory;
import com.influxdb.client.reactive.WriteReactiveApi;

import com.mattieserver.dto.dto.MeterDataMeasurement;
import com.mattieserver.dto.mapper.MeterDataMapper;
import com.mattieserver.rest.client.MeterData;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import org.jboss.logging.Logger;
import org.reactivestreams.Publisher;

@Singleton
public class InfluxWriter {

    
    private String InfluxToken;

    private String InfluxOrg;

    private String InfluxBucket;

    private String InfluxHost;

    private InfluxDBClientReactive InfluxDBClient;

    private WriteReactiveApi WriteApi;
    
    @Inject
    MeterDataMapper meterDataMapper;

    private static final Logger LOG = Logger.getLogger(InfluxWriter.class);

    public InfluxWriter(@ConfigProperty(name = "influxdb.host") String host, @ConfigProperty(name = "influxdb.token") String token, @ConfigProperty(name = "influxdb.org") String org, @ConfigProperty(name = "influxdb.bucket") String bucket) throws Exception {
        super();

        this.InfluxHost = host;
        this.InfluxToken = token;
        this.InfluxOrg = org;
        this.InfluxBucket = bucket;

        if (token == null) {
           throw new Exception("token is not set");
        }

        char[] real_token = this.InfluxToken.toCharArray();
        this.InfluxDBClient = InfluxDBClientReactiveFactory.create("http://localhost:8086", real_token, this.InfluxOrg, this.InfluxBucket);
        LOG.info("influxDBClient");
        
        this.WriteApi = this.InfluxDBClient.getWriteReactiveApi();
    }

    public void WriteToInflux(MeterData meterDdata) {
        MeterDataMeasurement data = meterDataMapper.toMeasurement(meterDdata);
        Publisher<WriteReactiveApi.Success> publisher = WriteApi.writeMeasurement(WritePrecision.NS, data);
        Disposable subscriber = Flowable.fromPublisher(publisher)
                .subscribe(success -> LOG.info("Write P1 data to DB"));
    }

}
