package com.audioanalyzer.application.data.services;

import com.audioanalyzer.application.data.AudioParameter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class AudioFileService {

    private static final Logger LOGGER = Logger.getLogger(AudioFileService.class.getName());

//    private final AudioFileRepository audioFileRepository;

//    public AudioFileService(AudioFileRepository audioFileRepository) {
//        this.audioFileRepository = audioFileRepository;
//    }
//
//    public List<AudioFile> findAll(String stringFilter) {
//        if (stringFilter == null || stringFilter.isEmpty()) {
//            return audioFileRepository.findAll();
//        } else {
////            TODO
////            return audioFileRepository.search(stringFilter);
//            return null;
//        }
//    }
//
//    public long count() {
//        return audioFileRepository.count();
//    }

//    public void save(AudioFile audioFile) {
//        if (audioFile == null) {
//            LOGGER.log(Level.SEVERE, "AudioFile is null while saving.");
//            return;
//        }
//        audioFileRepository.save(audioFile);
//    }
//
    public Map<AudioParameter.Type, AudioParameter> populateTestData() {
        HashMap<AudioParameter.Type, AudioParameter> testParameters = new HashMap<>();
        testParameters.put(AudioParameter.Type.LUFS, new AudioParameter(AudioParameter.Type.LUFS) {
            @Override
            public Object calculate() {
                return 10;
            }
        });
        testParameters.put(AudioParameter.Type.RMS, new AudioParameter(AudioParameter.Type.RMS) {
            @Override
            public Object calculate() {
                return 20;
            }
        });
        testParameters.put(AudioParameter.Type.NoiseFloor, new AudioParameter(AudioParameter.Type.NoiseFloor) {
            @Override
            public Object calculate() {
                return 30;
            }
        });
        testParameters.put(AudioParameter.Type.PeakLevel, new AudioParameter(AudioParameter.Type.PeakLevel) {
            @Override
            public Object calculate() {
                return 40;
            }
        });

        return testParameters;
    }
}
