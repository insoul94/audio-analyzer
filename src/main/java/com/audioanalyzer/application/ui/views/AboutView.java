package com.audioanalyzer.application.ui.views;

import com.audioanalyzer.application.controller.Controller;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
@Component
public class AboutView extends VerticalLayout implements View {

    private Controller controller;

    public AboutView(Controller controller) {
        setController(controller);
        init();
    }

    protected void init() {
        setSpacing(false);
        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("200px");
        add(img);
        add(new H2("Header 2"));
        add(new Paragraph("Placeholder to extend UI 🤗"));
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
