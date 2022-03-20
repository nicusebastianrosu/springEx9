package sda.spring.demo9.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.spring.demo9.exceptions.NotFoundException;
import sda.spring.demo9.exceptions.SdaException;
import sda.spring.demo9.model.FileData;
import sda.spring.demo9.repository.FileDataRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileDataService {
    @Autowired
    private FileDataRepository fileDataRepository;

    public List<FileData> getAll() {
        return new ArrayList<FileData>(fileDataRepository.findAll());
    }

    public FileData findById(final UUID uuid) {
        return fileDataRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Object not found: " + String.valueOf(uuid)));
    }

    public FileData create(FileData fileData) {
        fileData.setId(null);
        return fileDataRepository.save(fileData);
    }

    public void delete(final UUID id) {
        fileDataRepository.findById(id).orElseThrow(() -> new SdaException("Cannot delete non existing file data"));
        fileDataRepository.deleteById(id);
    }

    public void update(UUID id, FileData fileData) {
        FileData existingFileData = fileDataRepository.findById(id)
                .orElseThrow(() -> new SdaException("Cannot update non existing file data"));
        existingFileData.setFileName(fileData.getFileName());
        existingFileData.setExtension(fileData.getExtension());
        existingFileData.setSizeInKb(fileData.getSizeInKb());
        fileDataRepository.save(existingFileData);
    }
}
