package com.gearvmstore.GearVM.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@ToString
@Getter
@Setter
@Entity
@Table(name = "prompt")
public class Prompt {
    @Id
    private String question;
    @Column(columnDefinition = "LONGTEXT")
    private String answer;

    public Prompt() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prompt prompt = (Prompt) o;
        return question.equals(prompt.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question);
    }
}
