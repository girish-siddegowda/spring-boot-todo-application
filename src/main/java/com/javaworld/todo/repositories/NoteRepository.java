package com.javaworld.todo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaworld.todo.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

	List<Note> findByOwnerUsername(String username);

}
