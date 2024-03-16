package com.example.wordNext.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "word_list")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;

    private String meaning;

    private String category;

    private String status;

    @Column(name = "LOI")
    private String levelOfImportance;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "show_id")
    private Source source;

    public Word(String word, String meaning, String status, String levelOfImportance) {
        this.word = word;
        this.meaning = meaning;
        this.status = status;
        this.levelOfImportance = levelOfImportance;
    }

    public Word(String word, String meaning, String category, String status, String levelOfImportance) {
        this.word = word;
        this.meaning = meaning;
        this.category = category;
        this.status = status;
        this.levelOfImportance = levelOfImportance;
    }

    public Word(String word, String meaning, String status, String levelOfImportance, Source source) {
        this.word = word;
        this.meaning = meaning;
        this.status = status;
        this.levelOfImportance = levelOfImportance;
        this.source = source;
    }

    public Word(String word, String meaning, String category, String status, String levelOfImportance, Source source) {
        this.word = word;
        this.meaning = meaning;
        this.category = category;
        this.status = status;
        this.levelOfImportance = levelOfImportance;
        this.source = source;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", meaning='" + meaning + '\'' +
                ", status='" + status + '\'' +
                ", levelOfImportance='" + levelOfImportance + '\'' +
                '}';
    }
}
