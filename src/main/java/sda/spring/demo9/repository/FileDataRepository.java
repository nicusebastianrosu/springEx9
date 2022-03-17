package sda.spring.demo9.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sda.spring.demo9.model.FileData;

import java.util.List;
import java.util.UUID;


public interface FileDataRepository extends CrudRepository<FileData, UUID> {
    public List<FileData> findAll();

}
