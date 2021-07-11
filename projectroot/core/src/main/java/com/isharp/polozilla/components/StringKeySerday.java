package com.isharp.polozilla.components;
//com.isharp.polozilla.components.WindowedKeySerde
import org.apache.kafka.common.serialization.*;

import java.sql.Wrapper;

public class StringKeySerday implements Serde {
    @Override
    public Serializer serializer() {
        return Serdes.String().serializer();
    }

    @Override
    public Deserializer deserializer() {
        return Serdes.String().deserializer();
    }
}
