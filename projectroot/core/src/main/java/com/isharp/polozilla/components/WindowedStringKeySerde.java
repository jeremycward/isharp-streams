package com.isharp.polozilla.components;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.kstream.WindowedSerdes;

public class WindowedStringKeySerde implements Serde {
    @Override
    public Serializer serializer() {
        return WindowedSerdes.timeWindowedSerdeFrom(String.class).serializer();
    }

    @Override
    public Deserializer deserializer() {
        return WindowedSerdes.timeWindowedSerdeFrom(String.class).deserializer();
    }
}
