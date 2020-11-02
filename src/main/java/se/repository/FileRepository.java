package se.repository;

import se.domain.File;

import java.util.List;

public interface FileRepository {

    void save(File order);

    void delete(Integer id);

    List<File> getAll();

    File getById(Integer id);

    void update(Integer id, String title, String date);

    List<File> select(Integer id, String title, String date);
}