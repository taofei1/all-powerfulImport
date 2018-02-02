package com.example.controller;

import com.example.domain.Synonym;
import com.example.service.SynonymService;
import com.example.util.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/synonym")
public class SynonymController {
    @Autowired
    private SynonymService synonymService;

    @GetMapping("/{page}")
    public Result findByPage(@PathVariable("page") Integer page) {
        PageHelper.startPage(page, 2);
        List<Synonym> synonymList = synonymService.findAll();
        PageInfo<Synonym> p = new PageInfo<>(synonymList);
        return Result.success().addData(p);
    }

    @PostMapping("/add")
    public Result insert(Synonym synonym) {

        if (synonym != null && this.synonymService.insertSynonym(synonym) > 0) {
            return Result.success();
        }
        return Result.fail();
    }

    @PutMapping("/update")
    public Result update(Synonym synonym) {
        if (synonym != null && this.synonymService.updateSynonym(synonym) > 0) {
            return Result.success();
        }
        return Result.fail();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        if (id != null && this.synonymService.deleteSynonym(id) > 0) {
            return Result.success();
        }
        return Result.fail();
    }

}
