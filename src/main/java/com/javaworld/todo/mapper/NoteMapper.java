package com.javaworld.todo.mapper;

import org.springframework.stereotype.Component;

import com.javaworld.todo.dto.NoteRequestDTO;
import com.javaworld.todo.dto.NoteResponseDTO;
import com.javaworld.todo.model.Note;

@Component
public class NoteMapper {

	public Note toEntity(NoteRequestDTO dto) {
        Note note = new Note();
        note.setId(dto.getId());
        note.setContent(dto.getContent());
        note.setOwnerUsername(dto.getOwnerUsername());
        return note;
    }

    public NoteResponseDTO toResponseDTO(Note note) {
        NoteResponseDTO dto = new NoteResponseDTO();
        dto.setId(note.getId());
        dto.setContent(note.getContent());
        dto.setOwnerUsername(note.getOwnerUsername());
        return dto;
    }
    
    
}
