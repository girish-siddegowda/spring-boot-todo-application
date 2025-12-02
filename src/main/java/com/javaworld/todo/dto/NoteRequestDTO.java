package com.javaworld.todo.dto;


import lombok.Data;

@Data
public class NoteRequestDTO {

	private Long id;
    private String content;
    private String ownerUsername;
}
