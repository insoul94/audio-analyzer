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
        try {
            //TODO: enable MP3 support and other formats than WAV

            // Construct AudioInputStream specifically for audio data which includes file headers.
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile.getInputStream());
            AudioFormat format = audioInputStream.getFormat();
//            AudioFormat format = audioFileFormat.getFormat();
            boolean isBigEndian = format.isBigEndian();
            int bitrate = format.getSampleSizeInBits();
            int channels = format.getChannels();


            byte[] audioDataBytes = audioInputStream.readAllBytes();

            int step = bitrate / Byte.SIZE;
            int bufferSize = 1024;
            int[] buffer = new int[bufferSize];

            // New int[] length has to match actual amount of samples stored
            int audioDataIntsLength =
                    audioDataBytes.length % step == 0 ?
                            audioDataBytes.length / step :
                            audioDataBytes.length / step + 1;

            int[] audioDataInts = new int[audioDataIntsLength];


            for (int i = 0; i <= audioDataBytes.length-step; i+=step) {
                if (!isBigEndian) {
                    for (int j = 0; j < step; j++) {
                        audioDataInts [i / step] |= audioDataBytes[i + j] << j * Byte.SIZE;
                    }
                } else {
                    for (int j = step - 1; j >= 0; j--) {
                        audioDataInts [i / step] |= audioDataBytes[i + j] << j * Byte.SIZE;
                    }
                }
            }

            // TODO: add support for more channels
            if (channels == 2) {
                audioDataInts = stereoToMono(audioDataInts);
            }

            double rms = calculateRMS(audioDataInts, bitrate);


            Map<AudioParameter.Type, AudioParameter> parameters = new HashMap<>();
            parameters.put(AudioParameter.Type.RMS, new RMS(rms));

            return parameters;


        } catch (UnsupportedAudioFileException e) {
            LOGGER.log(Level.SEVERE, "UnsupportedAudioFileException in AudioParameterFactory.calculateAll(): " + e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException in AudioParameterFactory.calculateAll(): " + e.getMessage());
        }




        return null;
    }

    void init() {

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

    //TODO: correct RMS
    public static double calculateRMS(int[] audioData, int bitRate) {
        long sum = 0;
        for (int i = 0; i < audioData.length - 1; i++) {
            sum += (long) audioData[i] * audioData[i];
        }
        double rms = Math.sqrt(sum * 1.0 / audioData.length) / Math.pow(2, bitRate);
        rms = linearToDecibel(rms);
        return rms;
    }
    public static double calculateLUFS(int[] audioData) {
        return 0;
    }
    public static double calculateNoiseFloor(int[] audioData) {
        return 0;
    }
    public static double calculatePeakLevel(int[] audioData) {
        return 0;
    }
}
