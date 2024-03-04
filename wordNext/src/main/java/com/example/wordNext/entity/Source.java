package com.example.wordNext.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "shows")
@Entity
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String genre;

    @Column(name = "show_name")
    private String name;

    @OneToMany(mappedBy = "source", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    private List<Word> words;

    public Source(String genre, String name) {
        this.genre = genre;
        this.name = name;
    }

    public Source(Long id, String genre, String name) {
        this.id = id;
        this.genre = genre;
        this.name = name;
    }

    public void addWord(Word word){
        if(words == null){
            words = new ArrayList<>();
        }

        words.add(word);
    }

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                ", name='" + name + '\'' +
                ", words=" + words +
                '}';
    }
}
