package se.repository;

import se.domain.File;

import java.util.List;

public interface FileRepository {

    void save(File order);

    void delete(Integer id);

    List<File> getAll();

    List<File> getAllById(Integer id);


    File getById(Integer id);

    void update(Integer id, Integer user_id,String title, String date);

    List<File> select(Integer id, Integer user_id, String title, String date);
}