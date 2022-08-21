package com.audioanalyzer.application.ui.views.main;

import com.audioanalyzer.application.data.entity.AudioFile;
import com.audioanalyzer.application.ui.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Main")
@Route(value = "main", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)

public class MainView extends VerticalLayout {

    Grid<AudioFile> grid = new Grid<>(AudioFile.class);

    public MainView() {
        addClassName("list-view");
        setSizeFull();

        add(grid);
    }
}
