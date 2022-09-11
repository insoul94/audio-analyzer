package com.audioanalyzer.application.data.audioparameters.impl;

import com.audioanalyzer.application.data.AudioDataUtils;
import com.audioanalyzer.application.data.audioparameters.AudioParameter;
import com.audioanalyzer.application.data.audioparameters.AudioParameterType;

public class RMS extends AudioParameter {

    public RMS() {
        super(AudioParameterType.RMS);
    }

    @Override
    public void calculate(float[] data) {

        double sum = 0;
        for (int i = 0; i < data.length - 1; i++) {
            sum += (double) data[i] * data[i];
        }

        double rms = Math.sqrt(sum / data.length);
        rms = AudioDataUtils.linearToDecibel(rms);
        this.setValue((float) rms);
    }
}
