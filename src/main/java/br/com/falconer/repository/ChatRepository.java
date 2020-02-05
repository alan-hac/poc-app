package br.com.falconer.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.falconer.document.Chat;

public interface ChatRepository extends MongoRepository<Chat, Long> { 
	
	public Optional<Chat> findFirstByEmailOrderByStartedAtDesc(String email);
	
}
