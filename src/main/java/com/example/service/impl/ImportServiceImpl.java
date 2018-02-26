package com.example.service.impl;

import com.example.domain.PrimaryKey;
import com.example.mapper.CommonInsertMapper;
import com.example.mapper.ImportMapper;
import com.example.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportServiceImpl implements ImportService {
    @Autowired
    private ImportMapper importMapper;

    @Override
    public List<PrimaryKey> findByNameWithNoPrimaryKey(String importName) {
        return importMapper.findByNameWithNoPrimaryKey(importName);
    }
}
