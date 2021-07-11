package com.isharp.polozilla.components;
//com.isharp.polozilla.components.CaptureWindowSerde
import com.isharp.polozilla.vo.Capture;
import com.isharp.processors.CaptureWindow;

public class CaptureWindowSerde extends PoloniexSerdes.GsonSerde<CaptureWindow> {
    public CaptureWindowSerde() {
        super(CaptureWindow.class);
    }
}
