package com.isharp.processors;

import com.isharp.polozilla.components.FlatCaptureTransformerSupplier;
import com.isharp.polozilla.components.RawDataTransformerSupplier;
import com.isharp.polozilla.vo.Capture;
import org.apache.kafka.streams.kstream.KStream;

import java.util.function.Function;

public class TransformAndFlatten {
    public  Function<KStream<String, String>, KStream<String, Capture>> process(){
        return input ->
                input.filter((k,v)->(v.length()> 50))
                .transform(new RawDataTransformerSupplier())
                .filter((k,v)->v.getMsgType()==1002)
                .flatTransform(new FlatCaptureTransformerSupplier());
    }
}
