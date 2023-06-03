package com.mattieserver.influxdb;


import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.reactive.InfluxDBClientReactive;
import com.influxdb.client.reactive.InfluxDBClientReactiveFactory;
import com.influxdb.client.reactive.QueryReactiveApi;
import com.influxdb.query.dsl.Flux;
import com.influxdb.query.dsl.functions.restriction.Restrictions;

import com.mattieserver.dto.dto.MeterDataMeasurement;
import com.mattieserver.dto.mapper.MeterDataMapper;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import org.jboss.logging.Logger;
import org.reactivestreams.Publisher;

@Singleton
public class InfluxReader {

    
    private String InfluxToken;

    private String InfluxOrg;

    private String InfluxBucket;

    private String InfluxHost;

    private InfluxDBClientReactive InfluxDBClient;

    private QueryReactiveApi ReaderApi;
    
    @Inject
    MeterDataMapper meterDataMapper;

    private static final Logger LOG = Logger.getLogger(InfluxWriter.class);

    public InfluxReader(@ConfigProperty(name = "influxdb.host") String host, @ConfigProperty(name = "influxdb.token") String token, @ConfigProperty(name = "influxdb.org") String org, @ConfigProperty(name = "influxdb.bucket") String bucket) throws Exception {
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
        LOG.info("Created reader influxDBClient");
        
        this.ReaderApi = this.InfluxDBClient.getQueryReactiveApi();
    }

    public List<MeterDataMeasurement> ReadLastHourFromInflux() {
        List<MeterDataMeasurement> results = new ArrayList<>();

        Flux flux = Flux
            .from("P1")
            .range(-1L, ChronoUnit.HOURS)
            .filter(Restrictions.and(Restrictions.measurement().equal("meterdata"), Restrictions.tag("unique_id").equal("3153414731313035333131313637")))
            //.filter(Restrictions.and(Restrictions.field().equal("active_power_w")))
            //.toInt()
            .aggregateWindow()
                .withEvery("1s")
                .withAggregateFunction("last")
                .withCreateEmpty(false)
            .pivot()
                .withRowKey(new String[]{"_time"})
                .withColumnKey(new String[]{"_field"})
                .withValueColumn("_value");

        Publisher<MeterDataMeasurement> query = this.ReaderApi.query(flux.toString(), MeterDataMeasurement.class);

        Flowable.fromPublisher(query)
                .take(10)
                .subscribe(meterdata -> {
                    results.add(meterdata);
                });
        return results;
    }

}
