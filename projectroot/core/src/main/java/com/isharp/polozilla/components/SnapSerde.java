package com.isharp.polozilla.components;

//com.isharp.polozilla.components.SnapSerde
import com.isharp.polozilla.vo.Snap;

public class SnapSerde extends PoloniexSerdes.GsonSerde<Snap> {
    public SnapSerde() {
        super(Snap.class);
    }
}
