package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Transactional
    void deleteByDone(boolean done);
}
