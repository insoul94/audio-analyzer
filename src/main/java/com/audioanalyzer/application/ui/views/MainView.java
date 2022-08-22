package com.audioanalyzer.application.ui.views;

import com.audioanalyzer.application.controller.Controller;
import com.audioanalyzer.application.data.AudioFile;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.stereotype.Component;

@PageTitle("Main")
@Route(value = "main", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)

public class MainView extends VerticalLayout implements View {

    private Controller controller;

    H2 currentFileName;
    Grid<AudioFile> grid;
    Upload singleFileUpload;

    public MainView(Controller controller) {
        setController(controller);
        addClassName("list-view");
        setSizeFull();

        addCurrentFileName();
//        addGrid();
        addUploadForm();
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    private void addCurrentFileName() {
        currentFileName = new H2("File name");
        Div content = new Div();
        content.add(currentFileName);

        add(currentFileName);
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

            currentFileName.setText(controller.getCurrentAudioFileName());
        });

        add(singleFileUpload);
    }
}
