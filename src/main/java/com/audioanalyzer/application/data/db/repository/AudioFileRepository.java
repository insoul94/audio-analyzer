package com.audioanalyzer.application.data.db.repository;

import com.audioanalyzer.application.data.audiofile.AudioFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface AudioFileRepository extends JpaRepository<AudioFile, BigInteger> {
}
