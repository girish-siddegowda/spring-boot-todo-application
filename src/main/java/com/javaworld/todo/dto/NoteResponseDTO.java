package com.javaworld.todo.dto;

import lombok.Data;

@Data
public class NoteResponseDTO {

	  private Long id;
	  private String content;
	  private String ownerUsername;
}
