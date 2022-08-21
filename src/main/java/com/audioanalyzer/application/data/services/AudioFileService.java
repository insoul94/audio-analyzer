package com.audioanalyzer.application.data.services;

import org.springframework.stereotype.Service;

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
//    public void populateTestData() {
//        if (count() == 0) {
//            audioFileRepository.saveAll(
//                    Stream.of("AB01", "AB02", "AB03")
//                            .map(AudioFile::new)
//                            .collect(Collectors.toList())
//            );
//        }
//    }
}
