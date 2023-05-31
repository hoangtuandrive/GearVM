package com.gearvmstore.GearVM.repository;

import com.gearvmstore.GearVM.model.Prompt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromptRepository extends JpaRepository<Prompt, String> {
    Prompt getPromptByQuestionContainingIgnoreCase(String question);
}
