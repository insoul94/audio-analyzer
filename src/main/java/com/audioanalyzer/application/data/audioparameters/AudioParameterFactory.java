package com.audioanalyzer.application.data.audioparameters;

import com.audioanalyzer.application.data.AudioFile;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AudioParameterFactory {

    private static final Logger LOGGER = Logger.getLogger(AudioParameterFactory.class.getName());

    public static Map<AudioParameter.Type, AudioParameter> calculateAll(AudioFile audioFile) {
        int[] audioData = prepareAudioData(audioFile);

        Map<AudioParameter.Type, AudioParameter> parameters = new HashMap<>();
        parameters.put(AudioParameter.Type.RMS, new RMS());
        parameters.put(AudioParameter.Type.LUFS, new LUFS());
        parameters.put(AudioParameter.Type.NoiseFloor, new NoiseFloor());
        parameters.put(AudioParameter.Type.PeakLevel, new PeakLevel());

        try {
            // TODO: crutch, this method shouldn't know about bitRate. Should be moved to prepareAudioData()
            int bitRate = AudioSystem.getAudioInputStream(audioFile.getInputStream()).getFormat().getSampleSizeInBits();

            for (AudioParameter parameter : parameters.values()) {
                parameter.calculate(audioData);

                // TODO: crutch
                int maxValue = (int) Math.pow(2, bitRate);
                parameter.setValue(linearToDecibel(parameter.getValue() / maxValue));
            }

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parameters;
    }

    static int[] prepareAudioData(AudioFile audioFile) {
        try {
            // TODO: enable MP3 support and other formats than WAV
            // Construct AudioInputStream specifically for audio data which includes file headers.
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile.getInputStream());

            AudioFormat format = audioInputStream.getFormat();
            boolean isBigEndian = format.isBigEndian();
            int bitrate = format.getSampleSizeInBits();
            int channels = format.getChannels();

            // TODO: should the buffer be used to read bytes to avoid memory overload?
            byte[] audioDataBytes = audioInputStream.readAllBytes();

            // Sample size in bytes. (16-bit PCM - 2 bytes, 24-bit PCM - 3 bytes, etc.)
            int sampleSize = bitrate / Byte.SIZE;

            // New int[] length has to match actual amount of samples stored
            int audioDataIntsLength =
                    audioDataBytes.length % sampleSize == 0 ?
                            audioDataBytes.length / sampleSize :
                            audioDataBytes.length / sampleSize + 1;
            int[] audioDataInts = new int[audioDataIntsLength];

            int n = 0;
            if (!isBigEndian) {
                for (int i = 0; i <= audioDataBytes.length - sampleSize; i += sampleSize) {
                    for (int j = 0; j < sampleSize; j++) {

                        // Shift byte according to Little Endian order
                        n = audioDataBytes[i + j] << j * 8;

                        // If MSB, preserve negativeness, if not MSB - use window to keep byte and zero out the rest
                        if (j != sampleSize - 1) {
                            n &= n & (0xFF << j * 8);
                        }

                        audioDataInts[i / sampleSize] |= n;
                    }
                }
            } else {
                // TODO: check correctness
                for (int i = 0; i <= audioDataBytes.length - sampleSize; i += sampleSize) {
                    for (int j = 0; j < sampleSize; j++) {
                        n = audioDataBytes[i + j] << (sampleSize - 1 - j) * 8;
                        audioDataInts[i / sampleSize] |= n;
                    }
                }
            }

            // TODO: add support for more channels
            if (channels == 2) {
                audioDataInts = stereoToMono(audioDataInts);
            }

            return audioDataInts;

        } catch (UnsupportedAudioFileException e) {
            LOGGER.log(Level.SEVERE, "UnsupportedAudioFileException in AudioParameterFactory.calculateAll(): " + e.getMessage());
            return null;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException in AudioParameterFactory.calculateAll(): " + e.getMessage());
            return null;
        }
    }

    public static int[] stereoToMono(int[] stereoArray) {
        int[] monoArray = new int[stereoArray.length / 2];

        for (int i = 0; i < monoArray.length; i += 2) {
            monoArray[i / 2] = (stereoArray[i] + stereoArray[i + 1]) / 2;
        }

        return monoArray;
    }

    public static double linearToDecibel(double linear) {
        return 20 * Math.log10(linear);
    }
}
