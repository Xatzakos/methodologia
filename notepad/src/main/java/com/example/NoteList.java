package com.example;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@UIScope
@SpringComponent
class NoteList extends VerticalLayout implements NoteChangeListener {
    @Autowired
    NoteRepository repository;
    private List<Note> notes;

    @PostConstruct
    void init() {
        setWidth("80%");

        update();
    }

    private void update() {
        setTodos(repository.findAll());
    }

    private void setTodos(List<Note> notes) {
        this.notes = notes;
        removeAllComponents();
        notes.forEach(todo -> addComponent(new NoteLayout(todo, this)));
    }

     void addTodo(Note note) {
        repository.save(note);
        update();
    }

    @Override
    public void todoChanged(Note note) {
        addTodo(note);
    }


    public void deleteCompleted() {
        repository.deleteByDone(true);
        update();
    }
}
