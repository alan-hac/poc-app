package br.com.falconer.document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.falconer.dto.Message;
import lombok.Data;

@Document(collection = "chat")
@Table(indexes = {@Index(name = "email", columnList="email", unique = true)})
@Data
public class Chat {
	
	@Id
	public ObjectId id;
	
	private String email;
	private List<Message> messages;
	private Date startedAt;
	
	public Chat(String email) {
		this.id = ObjectId.get();
		this.email = email;
		this.messages = new ArrayList<>();
		this.startedAt = new Date();
	}
}


