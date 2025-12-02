package com.javaworld.todo.service;

import java.util.List;

import com.javaworld.todo.model.Note;

public interface NoteService {

	Note createNoteForUser(String username, String content);

    Note updateNoteForUser(Long noteId, String content, String username);

    void deleteNoteForUser(Long noteId, String username);

    List<Note> getNotesForUser(String username);
}
