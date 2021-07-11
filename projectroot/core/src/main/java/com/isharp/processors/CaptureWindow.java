package com.isharp.processors;

import com.isharp.polozilla.components.KeyedPoloCaptureWindowTransformerSupplier;
import com.isharp.polozilla.components.PoloniexSerdes;
import com.isharp.polozilla.vo.Capture;
import com.isharp.polozilla.vo.KeyedPoloCaptureWindow;
import com.isharp.polozilla.vo.PoloCaptureWindow;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.*;

import java.time.Duration;
import java.util.function.Function;

public class CaptureWindow {

    TransformerSupplier<Windowed<String>, PoloCaptureWindow, KeyValue<String, KeyedPoloCaptureWindow>> keyedTransformerSupplier =
            new KeyedPoloCaptureWindowTransformerSupplier();

    private final TimeWindows tumblingWindow;

    public CaptureWindow(Duration timeWindow, Duration gracePeriod) {
        this.tumblingWindow  = TimeWindows.of(timeWindow).grace(gracePeriod);
    }

    public Function<KStream<String, Capture>, KStream<String, KeyedPoloCaptureWindow>> process(){

        return in -> in
                .groupByKey()
                .windowedBy(tumblingWindow)
                .aggregate(PoloCaptureWindow::new,(k, v, win)->win.push(v),Materialized.with(Serdes.String(),PoloniexSerdes.captureWindow))
                .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded().shutDownWhenFull()))
                .toStream()
                .transform(keyedTransformerSupplier);
    }

}
