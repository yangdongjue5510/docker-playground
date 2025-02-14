package com.yangdongjue.sampleweb;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SimpleController {

	private final MessageRepository messageRepository;

	@GetMapping("/messages")
	public List<Message> messages() {
		return messageRepository.findAll();
	}

	@PostMapping("/messages")
	public Message createMessage(@RequestBody Message message) {
		return messageRepository.save(message);
	}
}
