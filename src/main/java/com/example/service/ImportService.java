package com.example.service;

import com.example.domain.PrimaryKey;

import java.util.List;

public interface ImportService {
    List<PrimaryKey> findByNameWithNoPrimaryKey(String importName);
}
