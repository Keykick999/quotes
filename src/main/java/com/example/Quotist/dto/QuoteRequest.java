package com.example.Quotist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class QuoteRequest {
    private String quoteSpeaker;
    private String quoteText;
}
