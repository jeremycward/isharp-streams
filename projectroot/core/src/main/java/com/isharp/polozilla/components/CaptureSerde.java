package com.isharp.polozilla.components;

import com.isharp.polozilla.vo.Capture;

public class CaptureSerde extends PoloniexSerdes.GsonSerde<Capture> {
    public CaptureSerde() {
        super(Capture.class);
    }
}
