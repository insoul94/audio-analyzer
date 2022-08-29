package com.audioanalyzer.application.data.audioparameters;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;

public class RMS extends AudioParameter {

    public RMS() {
        super(Type.RMS);
    }

    @Override
    public void calculate(byte[] data) {
    }
}
