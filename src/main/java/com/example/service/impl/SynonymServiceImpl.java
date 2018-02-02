package com.example.service.impl;

import com.example.domain.Synonym;
import com.example.mapper.SynonymMapper;
import com.example.service.SynonymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SynonymServiceImpl implements SynonymService {
    @Autowired
    private SynonymMapper synonymMapper;

    @Override
    public List<Synonym> findAll() {
        return this.synonymMapper.findAll();
    }

    @Override
    public Synonym findById(Integer id) {
        return this.synonymMapper.findById(id);
    }

    @Override
    public int updateSynonym(Synonym synonym) {
        return this.synonymMapper.updateSynonym(synonym);
    }

    @Override
    public int insertSynonym(Synonym synonym) {
        return this.synonymMapper.insertSynonym(synonym);
    }

    @Override
    public int deleteSynonym(Integer id) {
        return this.synonymMapper.deleteSynonym(id);
    }
}
