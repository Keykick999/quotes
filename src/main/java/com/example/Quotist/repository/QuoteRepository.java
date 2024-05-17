package com.example.Quotist.repository;

import com.example.Quotist.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findByQuoteSpeaker(String quoteSpeaker);
}
