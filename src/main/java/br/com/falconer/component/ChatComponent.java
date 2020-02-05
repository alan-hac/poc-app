package br.com.falconer.component;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.falconer.document.Chat;
import br.com.falconer.dto.Message;
import br.com.falconer.entity.User;
import br.com.falconer.repository.ChatRepository;

@Component
public class ChatComponent {
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private ChatRepository chatRepository;
	
	public void initChat(User user) {
		if (user.getEmail() == null) 
			return;
		createChatHistory(user);
	}

	public void recuperaChat(User user) {
		Optional<Chat> chat = chatRepository.findFirstByEmailOrderByStartedAtDesc(user.getEmail());
		if (chat.isPresent()) 
			request.setAttribute("chat", chat.get());
		else
			createChatHistory(user);
	}
	
	private void createChatHistory(User user) {
		Chat chat = new Chat(user.getEmail());
		chat.getMessages().add(new Message("BOT", "Hello, " + user.getName() + "! My name is Test Robot. How can I help you?", new Date()));
		chatRepository.save(chat);
		request.setAttribute("chat", chat);
	}
}
