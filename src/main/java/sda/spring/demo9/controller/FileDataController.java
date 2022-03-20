package sda.spring.demo9.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.spring.demo9.exceptions.NotFoundException;
import sda.spring.demo9.exceptions.SdaException;
import sda.spring.demo9.model.FileCollection;
import sda.spring.demo9.model.FileData;
import sda.spring.demo9.repository.FileDataRepository;
import sda.spring.demo9.services.FileDataService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static sda.spring.demo9.controller.FileDataController.API_FILES_DATA;

@Slf4j
@RestController
@RequestMapping(API_FILES_DATA)
public class FileDataController {

    public static final String API_FILES_DATA = "/api/files-data";
    @Autowired
    private FileDataService fileDataService;

    @GetMapping()
    public FileCollection getAllData() {
        List<FileData> fileDataList = fileDataService.getAll();
        return new FileCollection(fileDataList);
    }

    @GetMapping("/{id}")
    public FileData getAllData(@PathVariable("id") UUID uuid) {

        return fileDataService.findById(uuid);
    }

    @PostMapping("/")
    public ResponseEntity<FileData> create(@RequestBody FileData fileData) throws URISyntaxException {
        return ResponseEntity.created(new URI(API_FILES_DATA + "/" + fileDataService.create(fileData).getId())).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody FileData fileData, @PathVariable("id") UUID id) {
        fileDataService.update(id, fileData);
    }

//    @PutMapping("/{id}")
//    public FileData getAllData(@RequestBody FileData fileData, @PathVariable("id") UUID uuid) {
//        Optional<FileData> fileDataOptional =  fileDataRepository.findById(uuid);
//        if(fileDataOptional.isPresent()){
//            //Verificam daca pe entitatea noastra avem id, ca sa putem sa facem merge
//            if (fileData.getId() ==null){
//                fileData.setId(uuid);
//            }
//            return fileDataRepository.save(fileData);
//        }else{
//            return null;
//        }
//    }
//
//    @DeleteMapping(API_FILES_DATA + "/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete (@PathVariable UUID id){
//        fileDataRepository.deleteById(id);
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final UUID id) {
        fileDataService.delete(id);
    }

    @ExceptionHandler(SdaException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleSdaException(final SdaException exception) {
        log.error(exception.getMessage(), exception);
        return exception.getMessage();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotFoundException(final NotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return exception.getMessage();
    }

}
