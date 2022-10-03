package com.audioanalyzer.application.data.audioparameters;

import com.audioanalyzer.application.data.audioparameters.impl.LUFS;
import com.audioanalyzer.application.data.audioparameters.impl.NoiseFloor;
import com.audioanalyzer.application.data.audioparameters.impl.PeakLevel;
import com.audioanalyzer.application.data.audioparameters.impl.RMS;

import java.util.HashMap;
import java.util.Map;

public class AudioParameterFactory {

    private AudioParameterFactory() {
    }

    public static Map<AudioParameterType, AudioParameter> calculateAll(float[] data) {

        Map<AudioParameterType, AudioParameter> audioParameters = new HashMap<>();

        for (AudioParameterType type : AudioParameterType.values()) {
            AudioParameter param = provide(type);
            param.setValue(param.calculate(data));
            audioParameters.put(type, param);
        }

        return audioParameters;
    }

    public static AudioParameter provide(AudioParameterType type) {
        switch (type) {
            case LUFS -> {return new LUFS();}
            case NoiseFloor -> {return new NoiseFloor();}
            case PeakLevel -> {return new PeakLevel();}
            case RMS -> {return new RMS();}
            default -> {throw new IllegalArgumentException("Type has to be of AudioParameterType");}
        }
    }
}
