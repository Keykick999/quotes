package com.example.Quotist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "quotes")
@NoArgsConstructor
@Getter
@Setter
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUOTEID")
    private Long quoteId;

    @Column(name = "QUOTETEXT")
    private String quoteText;

    @Column(name = "QUOTESPEAKER")
    private String quoteSpeaker;

    // 생성자
    public Quote(String quoteText, String quoteSpeaker) {
        this.quoteText = quoteText;
        this.quoteSpeaker = quoteSpeaker;
    }
}
