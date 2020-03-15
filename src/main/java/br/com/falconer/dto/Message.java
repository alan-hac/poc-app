package br.com.falconer.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
	private String sender;
	private String content;
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/YYYY HH:mm:ss")
	private Date sentAt;
}
