package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.Prompt;
import com.gearvmstore.GearVM.repository.PromptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromptService {
    private final PromptRepository promptRepository;

    @Autowired
    public PromptService(PromptRepository promptRepository) {
        this.promptRepository = promptRepository;
    }

    public List<Prompt> findAllPrompts() {
        return promptRepository.findAll();
    }

    public Prompt createPrompt(Prompt prompt) {
        return promptRepository.save(prompt);
    }

    public Prompt updatePrompt(String question, String answer) {
        Prompt prompt = promptRepository.findById(question).get();
        prompt.setAnswer(answer);
        return promptRepository.save(prompt);
    }

    public String getAnswerFromPromptQuestion(String promptQuestion) {
        Prompt prompt = promptRepository.getPromptByQuestionContainingIgnoreCase(promptQuestion);
        return prompt != null ? prompt.getAnswer() : null;
    }
}
