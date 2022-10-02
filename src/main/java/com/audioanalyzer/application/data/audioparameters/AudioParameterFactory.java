package com.audioanalyzer.application.data.audioparameters;

import com.audioanalyzer.application.data.AudioFile;
import com.audioanalyzer.application.data.audioparameters.impl.LUFS;
import com.audioanalyzer.application.data.audioparameters.impl.NoiseFloor;
import com.audioanalyzer.application.data.audioparameters.impl.PeakLevel;
import com.audioanalyzer.application.data.audioparameters.impl.RMS;

import java.util.HashMap;
import java.util.Map;

public class AudioParameterFactory {

    private AudioParameterFactory() {
    }

    public static Map<AudioParameterType, AudioParameter> provideAll(AudioFile audioFile) {

        Map<AudioParameterType, AudioParameter> parameters = new HashMap<>();

        for (AudioParameterType type : AudioParameterType.values()) {
            parameters.put(type, provide(type, audioFile));
        }

        return parameters;
    }

    public static AudioParameter provide(AudioParameterType type, AudioFile source) {
        switch (type) {
            case LUFS -> {return new LUFS(source);}
            case NoiseFloor -> {return new NoiseFloor(source);}
            case PeakLevel -> {return new PeakLevel(source);}
            case RMS -> {return new RMS(source);}
            default -> {throw new IllegalArgumentException("Type has to be of AudioParameterType");}
        }
    }
}
