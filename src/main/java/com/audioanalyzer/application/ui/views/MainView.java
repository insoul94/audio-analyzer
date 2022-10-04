package com.audioanalyzer.application.ui.views;

import com.audioanalyzer.application.controller.Controller;
import com.audioanalyzer.application.data.audioparameters.AudioParameter;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@PageTitle("Main")
@Route(value = "main", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)

public class MainView extends VerticalLayout implements View {

    private static final Logger LOGGER = Logger.getLogger(MainView.class.getName());

    private Controller controller;

    H2 currentFileName;

    Grid<AudioParameter> grid;

    Upload singleFileUpload;

    public MainView(Controller controller) {
        setController(controller);
        addClassName("list-view");
        setSizeFull();

        addCurrentFileName();
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

    private void addUploadForm() {
        MemoryBuffer memoryBuffer = new MemoryBuffer();
        singleFileUpload = new Upload(memoryBuffer);

        singleFileUpload.addSucceededListener(event -> {

            try {
                controller.onAudioFileUpload(event.getFileName(), memoryBuffer.getInputStream());

            } catch (UnsupportedAudioFileException e) {
                LOGGER.log(Level.SEVERE,
                        "The file's stream does not point to a valid audio data recognized by the system." + e);
                showErrorMessage("Cannot recognize audio data");

            } catch (IOException e) {
                LOGGER.log(Level.SEVERE,
                        "Error while reading from InputStream." + e);
                showErrorMessage("Error while reading the file.");
            }

            currentFileName.setText(controller.getCurrentAudioFileName());
            setAudioParameters(controller.getAudioParameters());
        });

        singleFileUpload.addFailedListener(event -> {
            System.out.println("FAILED :" + event.getReason());
        });

        singleFileUpload.addFileRejectedListener(event -> {
            System.out.println("FAILED REJECTED :" + event.getErrorMessage());
        });

        add(singleFileUpload);
    }

    private void setAudioParameters(List<AudioParameter> audioParameters) {
        if (grid == null) {

            grid = new Grid<>(AudioParameter.class, false);
            grid.addColumn(AudioParameter::getType).setHeader("Type");
            grid.addColumn(AudioParameter::getValue).setHeader("Value");

            add(grid);
        }

        grid.setItems(audioParameters);
    }

    private void showErrorMessage(String msg) {
        // Configure Upload element to show error in a native Vaadin way
    }
}
