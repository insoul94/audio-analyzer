package com.audioanalyzer.application.data.audioparameters;

public class LUFS extends AudioParameter{

    public LUFS() {
        super(Type.LUFS);
    }

    @Override
    public void calculate(byte[] data) {
    }
}
