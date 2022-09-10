package com.audioanalyzer.application.data;

public class AudioDataUtils {

    public static float[] stereoToMono(float[] stereoArray) {
        float[] monoArray = new float[stereoArray.length / 2];

        for (int i = 0; i < monoArray.length; i += 2) {
            monoArray[i / 2] = (stereoArray[i] + stereoArray[i + 1]) / 2;
        }

        return monoArray;
    }

    public static double linearToDecibel(double linear) {
        return (20 * Math.log10(linear));
    }
}
