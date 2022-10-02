package com.audioanalyzer.application.data.audioparameters.impl;

import com.audioanalyzer.application.data.AudioDataHelper;
import com.audioanalyzer.application.data.AudioFile;
import com.audioanalyzer.application.data.audioparameters.AudioParameter;
import com.audioanalyzer.application.data.audioparameters.AudioParameterType;

public class RMS extends AudioParameter {

    public RMS(AudioFile source) {
        super(AudioParameterType.RMS, source);
    }

    @Override
    public void calculate(float[] data) {

        double sum = 0;
        for (int i = 0; i < data.length - 1; i++) {
            sum += (double) data[i] * data[i];
        }

        double rms = Math.sqrt(sum / data.length);
        rms = AudioDataHelper.linearToDecibel(rms);
        this.setValue((float) rms);
    }
}
