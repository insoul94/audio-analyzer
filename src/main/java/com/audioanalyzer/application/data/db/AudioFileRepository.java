package com.audioanalyzer.application.data.db;

import com.audioanalyzer.application.data.AudioFile;

import java.util.List;
import java.util.logging.Logger;

// Stub class
// TODO: create DB caching AudioParameters connected to an AudioFile

public class AudioFileRepository {

    private static final Logger LOGGER = Logger.getLogger(AudioFileRepository.class.getName());

    List<AudioFile> findAll() {
        return null;
    }

    List<AudioFile> search(String stringFilter) {
        return null;
    }

    long count() {
        return 0L;
    }

    void save(AudioFile audioFile) {
    }
}
