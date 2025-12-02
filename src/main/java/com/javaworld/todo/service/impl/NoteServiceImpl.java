package com.javaworld.todo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaworld.todo.exception.NoteNotFoundException;
import com.javaworld.todo.model.Note;
import com.javaworld.todo.repositories.NoteRepository;
import com.javaworld.todo.service.NoteService;
import com.javaworld.todo.utills.ApiUtils;

@Service
public class NoteServiceImpl implements NoteService {

	 @Autowired
	 private NoteRepository noteRepository;

	@Override
	public Note createNoteForUser(String username, String content) {
		
		Note note = new Note();
        note.setContent(content);
        note.setOwnerUsername(username);
        Note savedNote = noteRepository.save(note);
        return savedNote;
	}

	@Override
	public Note updateNoteForUser(Long noteId, String content, String username) {
		 Note note = noteRepository.findById(noteId).orElseThrow(()
	                -> new NoteNotFoundException(ApiUtils.NOTE_NOT_FOUND));
		 
	        note.setContent(content);
	        Note updatedNote = noteRepository.save(note);
	        return updatedNote;
	}

	@Override
	public void deleteNoteForUser(Long noteId, String username) {
		 noteRepository.deleteById(noteId);

	}

	@Override
	public List<Note> getNotesForUser(String username) {
		List<Note> personalNotes = noteRepository
                .findByOwnerUsername(username);
		
		if (personalNotes.isEmpty()) {
            throw new NoteNotFoundException(ApiUtils.NOTE_NOT_FOUND);
        }
		
        return personalNotes;
	}

}
