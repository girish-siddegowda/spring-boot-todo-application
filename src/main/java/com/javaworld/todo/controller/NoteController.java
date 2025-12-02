package com.javaworld.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaworld.todo.dto.ResponsePayload;
import com.javaworld.todo.model.Note;
import com.javaworld.todo.service.NoteService;


@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public ResponseEntity<?> createNote(@RequestBody String content,
                           @AuthenticationPrincipal UserDetails userDetails) {
        
    	String username = userDetails.getUsername();

        Note createdNote = noteService.createNoteForUser(username, content);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponsePayload<>(
                        HttpStatus.CREATED.value(),
                        HttpStatus.CREATED.name(),
                        null,
                        createdNote
                )
        );
    }

    @GetMapping
    public ResponseEntity<?> getUserNotes(@AuthenticationPrincipal UserDetails userDetails) {
        
    	String username = userDetails.getUsername();

        List<Note> notes = noteService.getNotesForUser(username);

        return ResponseEntity.ok(
                new ResponsePayload<>(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        null,
                        notes
                )
        );
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<?> updateNote(@PathVariable Long noteId,
                           @RequestBody String content,
                           @AuthenticationPrincipal UserDetails userDetails) {
        
    	String username = userDetails.getUsername();

        Note updatedNote = noteService.updateNoteForUser(noteId, content, username);

        return ResponseEntity.ok(
                new ResponsePayload<>(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        null,
                        updatedNote
                )
        );
        
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<?> deleteNote(@PathVariable Long noteId,
                           @AuthenticationPrincipal UserDetails userDetails) {
        
    	String username = userDetails.getUsername();

        noteService.deleteNoteForUser(noteId, username);

        return ResponseEntity.ok(
                new ResponsePayload<>(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.name(),
                        null,
                        "Note deleted successfully"
                )
        );
    }
}