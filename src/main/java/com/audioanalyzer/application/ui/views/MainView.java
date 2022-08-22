package com.audioanalyzer.application.ui.views;

import com.audioanalyzer.application.controller.Controller;
import com.audioanalyzer.application.data.AudioFile;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.io.InputStream;

@PageTitle("Main")
@Route(value = "main", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)

public class MainView extends VerticalLayout implements View {

    private Controller controller;

    Grid<AudioFile> grid;

    Upload singleFileUpload;

    public MainView(Controller controller) {
        setController(controller);
        addClassName("list-view");
        setSizeFull();

        addGrid();
        addUploadForm();
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    private void addGrid() {
        grid = new Grid<>(AudioFile.class);
        add(grid);
    }

    private void addUploadForm() {
        MemoryBuffer memoryBuffer = new MemoryBuffer();
        singleFileUpload = new Upload(memoryBuffer);

        singleFileUpload.addSucceededListener(event -> {
            // Get information about the uploaded file
            controller.onAudioFileUpload(
                    memoryBuffer.getInputStream(),
                    event.getFileName(),
                    event.getContentLength(),
                    event.getMIMEType());

            // Do something with the file data
            // processFile(fileData, fileName, contentLength, mimeType);
        });

        add(singleFileUpload);
    }
}
