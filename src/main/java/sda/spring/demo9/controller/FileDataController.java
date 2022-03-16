package sda.spring.demo9.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.spring.demo9.model.FileData;
import sda.spring.demo9.repository.FileDataRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FileDataController {

    @Autowired
    private FileDataRepository fileDataRepository;

    @RequestMapping("/api/files-data")
    public List<FileData> getAllData() {
        List<FileData> fileDataList = (ArrayList) fileDataRepository.findAll();
        return fileDataList;
    }
}
