package com.audioanalyzer.application.data.audioparameters;

import com.audioanalyzer.application.data.AudioDataHelper;
import com.audioanalyzer.application.data.AudioFile;
import com.audioanalyzer.application.data.audioparameters.impl.LUFS;
import com.audioanalyzer.application.data.audioparameters.impl.NoiseFloor;
import com.audioanalyzer.application.data.audioparameters.impl.PeakLevel;
import com.audioanalyzer.application.data.audioparameters.impl.RMS;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AudioParameterFactory {

    private AudioParameterFactory() {
    }

    public static Map<AudioParameterType, AudioParameter> calculateAll(AudioFile audioFile)
            throws UnsupportedAudioFileException, IOException {

        float[] audioData;
        if (audioFile != null) {
            audioData = AudioDataHelper.prepareAudioData(audioFile);
        } else {
            return null;
        }

        Map<AudioParameterType, AudioParameter> parameters = new HashMap<>();

        parameters.put(AudioParameterType.RMS, new RMS(audioFile));
        parameters.put(AudioParameterType.LUFS, new LUFS(audioFile));
        parameters.put(AudioParameterType.NoiseFloor, new NoiseFloor(audioFile));
        parameters.put(AudioParameterType.PeakLevel, new PeakLevel(audioFile));

        for (AudioParameter parameter : parameters.values()) {
            parameter.calculate(audioData);
        }

        return parameters;
    }
}
