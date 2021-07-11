package com.isharp.processors;

import com.isharp.polozilla.components.PoloniexSerdes;
import com.isharp.polozilla.vo.KeyedPoloCaptureWindow;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.*;

import java.time.Duration;
import java.util.function.Function;

public class Snapper {
    private final Duration timeWindow;
    private final Duration gracePeriod;

    public Snapper(Duration timeWindow, Duration gracePeriod) {
        this.timeWindow = timeWindow;
        this.gracePeriod = gracePeriod;
    }

    private KTable<Windowed<String>, com.isharp.polozilla.vo.Snap> aggregatedTable(KStream<String, KeyedPoloCaptureWindow> inputStream){
        TimeWindows tumblingWindow = TimeWindows.of(timeWindow).grace(gracePeriod);
        KTable<Windowed<String>, com.isharp.polozilla.vo.Snap> snapWindows = inputStream
                .groupByKey()
                .windowedBy(tumblingWindow)
                .aggregate(
                        () -> new com.isharp.polozilla.vo.Snap(),
                        (key, value, aggregate) ->aggregate.add(value.getTicker(),value.getCaptureWindow()),
                        Materialized.with(Serdes.String(), PoloniexSerdes.snap))
                .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded().shutDownWhenFull()));

        return snapWindows;
    }


    public Function<KStream<String, KeyedPoloCaptureWindow>, KStream<Windowed<String>, com.isharp.polozilla.vo.Snap>> process(){
         return in->  aggregatedTable(in).toStream();
    }

}
