package com.example.service;

import com.example.domain.Synonym;

import java.util.List;

public interface SynonymService {
    List<Synonym> findAll();

    Synonym findById(Integer id);

    int updateSynonym(Synonym synonym);

    int insertSynonym(Synonym synonym);

    int deleteSynonym(Integer id);
}
