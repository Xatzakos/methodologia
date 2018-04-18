package com.example;

import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.xml.soap.Text;


public class NoteLayout extends HorizontalLayout {
    private final CheckBox done;
    private final TextField text;
    private Button editButton,okButton;
    private Window subWindow;
    private TextArea subTextField;

    public NoteLayout(Note note, NoteChangeListener changeListener) {
        setWidth("100%");
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        done = new CheckBox();

        text = new TextField();
        text.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        editButton=new Button("Edit");

        editButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        editButton.setIcon(VaadinIcons.EDIT);

        okButton= new Button("Finish");

        TextArea subTextField = new TextArea();
        subTextField.setWordWrap(true);
        //subTextField.addStyleName(ValoTheme.TEXTFIELD_HUGE);

        Window subWindow = new Window("EDIT WINDOW");
        VerticalLayout subContent = new VerticalLayout();
        subWindow.setContent(subContent);
        subContent.addComponentsAndExpand(subTextField);
        subContent.addComponent(okButton);
        subWindow.center();
        subWindow.setHeight("300px");
        subWindow.setWidth("500px");

        subTextField.setSizeFull();


        Binder<Note> binder = new Binder<>(Note.class);
        binder.bindInstanceFields(this);
        binder.setBean(note);

        addComponent(done);
        addComponentsAndExpand(text);
        addComponent(editButton);

        editButton.addClickListener(clickEvent ->UI.getCurrent().addWindow(subWindow));
        okButton.addClickListener(clickEvent -> subWindow.close());
        binder.addValueChangeListener(event -> changeListener.todoChanged(note));
    }
}
