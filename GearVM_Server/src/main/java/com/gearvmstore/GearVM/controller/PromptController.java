package com.gearvmstore.GearVM.controller;

import com.gearvmstore.GearVM.model.Prompt;
import com.gearvmstore.GearVM.service.PromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prompts")
public class PromptController {
    private final PromptService promptService;

    @Autowired
    public PromptController(PromptService promptService) {
        this.promptService = promptService;
    }

    @GetMapping
    public List<Prompt> getAllPrompts() {
        return promptService.findAllPrompts();
    }

    @GetMapping(value = "get-prompt")
    public String getAnswerFromPromptQuestion(@RequestParam String question) {
        return promptService.getAnswerFromPromptQuestion(question);
    }

    @PostMapping
    public Prompt createPrompt(@RequestBody Prompt prompt) {
        return promptService.createPrompt(prompt);
    }

    @PutMapping
    public Prompt updatePrompt(@RequestBody Prompt prompt) {
        return promptService.updatePrompt(prompt.getQuestion(), prompt.getAnswer());
    }
}
