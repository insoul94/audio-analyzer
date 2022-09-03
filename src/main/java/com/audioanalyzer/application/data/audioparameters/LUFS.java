package com.audioanalyzer.application.data.audioparameters;

import java.io.InputStream;

public class LUFS extends AudioParameter{

    public LUFS() {
        super(Type.LUFS);
    }

    @Override
    public void calculate(InputStream data) {
    }
}
