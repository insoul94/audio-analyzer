package com.audioanalyzer.application.data.audioparameters;

import java.io.InputStream;

public class PeakLevel extends AudioParameter {

    public PeakLevel() {
        super(Type.PeakLevel);
    }

    @Override
    public void calculate(InputStream data) {
    }
}
