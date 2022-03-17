package sda.spring.demo9.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sda.spring.demo9.exceptions.SdaException;
import sda.spring.demo9.model.FileCollection;
import sda.spring.demo9.model.FileData;
import sda.spring.demo9.repository.FileDataRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
public class FileDataController {

    @Autowired
    private FileDataRepository fileDataRepository;

    @GetMapping("/api/files-data")
    public FileCollection getAllData() {
        List<FileData> fileDataList = (ArrayList) fileDataRepository.findAll();
        return new FileCollection(fileDataList);
    }

    @GetMapping("/api/files-data/{id}")
    public FileData getAllData(@PathVariable("id") UUID uuid) {
        return fileDataRepository.findById(uuid).orElseThrow(() ->new SdaException("Object not found: "+ String.valueOf(uuid)));
    }

    @PostMapping("/api/files-data/")
    public FileData getAllData(@RequestBody FileData fileData) {
          FileData savedEntity= fileDataRepository.save(fileData);
        if(savedEntity.getId() != null){
            return savedEntity;
        }else{
            return null;
        }
    }

    @PutMapping("/api/files-data/{id}")
    public FileData getAllData(@RequestBody FileData fileData, @PathVariable("id") UUID uuid) {
        Optional<FileData> fileDataOptional =  fileDataRepository.findById(uuid);
        if(fileDataOptional.isPresent()){
            //Verificam daca pe entitatea noastra avem id, ca sa putem sa facem merge
            if (fileData.getId() ==null){
                fileData.setId(uuid);
            }
            return fileDataRepository.save(fileData);
        }else{
            return null;
        }
    }

    @DeleteMapping("/api/files-data/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable UUID id){
        fileDataRepository.deleteById(id);
    }

    @ExceptionHandler(SdaException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleSdaException(final SdaException exception){
        log.error(exception.getMessage(),exception);
        return exception.getMessage();
    }

}
