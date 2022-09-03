package com.audioanalyzer.application.data.services;

import com.audioanalyzer.application.data.audioparameters.AudioParameter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class AudioFileService {

    private static final Logger LOGGER = Logger.getLogger(AudioFileService.class.getName());

    //TODO: create DB caching AudioParameters connected to an AudioFile

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
}
