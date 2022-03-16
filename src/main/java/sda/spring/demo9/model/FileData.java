package sda.spring.demo9.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "files_data")
public class FileData {

    @Id
    @GeneratedValue()
    private UUID id;

    @Column(name = "file_name")
    private String fileName;

    private String extension;

    @Column(name = "size_in_kb")
    private Long sizeInKb;

    public FileData() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Long getSizeInKb() {
        return sizeInKb;
    }

    public void setSizeInKb(Long sizeInKb) {
        this.sizeInKb = sizeInKb;
    }
}
