package org.example.graduatemanage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.graduatemanage.components.LocalFileImportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/upload")
@CrossOrigin(origins = "http://localhost:8080")
public class FileUploadController {

    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public void fileUpload(@RequestParam("fileName") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        System.out.println("文件名称+"+fileName);
        try {
            InputStream inputStream = file.getInputStream();
            List<List<Object>> list = LocalFileImportUtil.getListByExcel(inputStream, fileName);
            list.forE

        ach((u)-> System.out.println(list.toString()));
        } catch (Exception e) {
            log.error("exception message", e);
        }
    }
}
