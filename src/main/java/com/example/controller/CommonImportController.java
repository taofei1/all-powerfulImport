package com.example.controller;

import com.example.domain.PrimaryKey;
import com.example.service.ImportService;
import com.example.service.impl.CommonImport;
import com.example.util.Result;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class CommonImportController {
    @Autowired
    private CommonImport commonImport;
    @Autowired
    private ImportService importService;

    @PostMapping("/import")
    public Result importExcel(@RequestParam("importName") String importName, @RequestParam("primaryKey") String primaryKey, @RequestParam("fileName") MultipartFile file) {

        if (file.isEmpty()) {
            Result result = new Result("04", "没有上传文件", "");
            return result;
        }
        try {
            commonImport.importExcel(importName, primaryKey, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success().addData("导入" + file.getOriginalFilename() + "数据成功！");
    }

    @GetMapping("/{importName}/primaryKeys")
    public Result getPrimaryKey(@PathVariable("importName") String importName) {
        List<PrimaryKey> list = importService.findByNameWithNoPrimaryKey(importName);
        return Result.success().addData(list);
    }
}
