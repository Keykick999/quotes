package com.example.Quotist.controller;

import com.example.Quotist.dto.QuoteRequest;
import com.example.Quotist.entity.Quote;
import com.example.Quotist.repository.QuoteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// localhost:8080/swagger-ui/index.html (여기서 api 문서 확인)
@RestController
@RequestMapping("/api/quotes")
@Tag(name = "quote", description = "Quote API")
public class QuoteController {

    @Autowired
    private QuoteRepository quoteRepository;

    // 전체 조회
    @Operation(summary = "Get all quotes")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<List<Quote>> findAllQuotes() {
        List<Quote> quotes = quoteRepository.findAll();
        if (quotes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(quotes);
    }

    // ID로 조회
    @Operation(summary = "Get quote by quoteId")
    @GetMapping("/{quoteId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json")),
    })
    @Parameters({
            @Parameter(name = "quoteId", description = "Quote ID", example = "1"),
    })
    public ResponseEntity<Quote> findByQuoteId(@PathVariable("quoteId") Long quoteId) {
        if (quoteId == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Quote> quote = quoteRepository.findById(quoteId);
        if (quote.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(quote.get());
    }

    // Speaker로 조회
    @Operation(summary = "Get quote by quoteSpeaker")
    @GetMapping("/speaker/{quoteSpeaker}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json")),
    })
    @Parameters({
            @Parameter(name = "quoteSpeaker", description = "Quote Speaker", example = "청명"),
    })
    public ResponseEntity<List<Quote>> findByQuoteSpeaker(@PathVariable("quoteSpeaker") String quoteSpeaker) {
        if (quoteSpeaker == null) {
            return ResponseEntity.badRequest().build();
        }
        List<Quote> quotes = quoteRepository.findByQuoteSpeaker(quoteSpeaker);
        if (quotes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(quotes);
    }

    // 저장
    @Operation(summary = "Add a new quote")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    public ResponseEntity<String> saveQuotes(@RequestBody QuoteRequest quoteRequest) {
        if (quoteRequest == null) {
            return ResponseEntity.badRequest().build();
        }
        Quote quote = new Quote(quoteRequest.getQuoteText(), quoteRequest.getQuoteSpeaker());
        quoteRepository.save(quote);
        return ResponseEntity.ok("Successfully saved");
    }

    // 업데이트
    @Operation(summary = "Update a quote")
    @PatchMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    public ResponseEntity<String> updateQuote(@RequestBody Quote newQuote) {
        if (newQuote == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Quote> quote = quoteRepository.findById(newQuote.getQuoteId());
        if (quote.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        quoteRepository.save(newQuote);
        return ResponseEntity.ok("Successfully updated the quote");
    }

    // 삭제
    @Operation(summary = "Delete a quote")
    @DeleteMapping("/{quoteId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    public ResponseEntity<String> deleteQuoteById(@PathVariable Long quoteId) {
        if (quoteId == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Quote> quote = quoteRepository.findById(quoteId);
        if (quote.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        quoteRepository.deleteById(quoteId);
        return ResponseEntity.ok("Quote deleted successfully");
    }
}
