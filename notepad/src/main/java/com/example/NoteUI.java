package com.example;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class NoteUI extends UI {

    private VerticalLayout layout;

    @Autowired
    NoteList todoList;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        addHeader();
        addForm();
        addTodoList();
        addActionButtons();
    }

    private void setupLayout() {
        layout = new VerticalLayout();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(layout);
    }

    private void addHeader() {
        Label header = new Label("My Notes");
        header.addStyleName(ValoTheme.LABEL_H1);
        layout.addComponent(header);

    }

    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("80%");

        TextField taskField = new TextField();
        taskField.focus();
        Button addButton = new Button("New Note");

        formLayout.addComponentsAndExpand(taskField);
        formLayout.addComponent(addButton);
        layout.addComponent(formLayout);

        addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        addButton.setIcon(VaadinIcons.PLUS);

        addButton.addClickListener(click -> {
            todoList.addTodo(new Note(taskField.getValue()));
            taskField.setValue("");
            taskField.focus();
        });
        addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    }

    private void addTodoList() {
        layout.addComponent(todoList);
    }

    private void addActionButtons() {
        Button deleteButton = new Button("Delete checked notes");

        deleteButton.addClickListener(click->todoList.deleteCompleted());

        layout.addComponent(deleteButton);

    }
}
