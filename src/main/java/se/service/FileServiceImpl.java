package se.service;

import se.domain.File;
import se.repository.FileRepository;
import se.repository.FileRepositoryImpl;

import java.util.List;

public class FileServiceImpl implements FileService {

    private FileRepository fileRepository = new FileRepositoryImpl();

    public void save(File file) {
        if(file!=null) {
            List<File> files = fileRepository.getAll();
            fileRepository.save(file);
        }
    }

    public void delete(Integer id) {
        fileRepository.delete(id);
    }

    public List<File> getAll() {
        return fileRepository.getAll();
    }

    public File getById(Integer id) {
        if(id!=null) {
            return fileRepository.getById(id);
        }
        return null;
    }

    public void update(Integer id, String title,String date) {
        fileRepository.update(id,title,date);
    }

    @Override
    public List<File> select(Integer id, String title,String date) {
        return fileRepository.select(id, title, date);
    }
}