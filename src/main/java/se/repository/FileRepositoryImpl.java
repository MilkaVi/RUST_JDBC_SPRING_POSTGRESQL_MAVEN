package se.repository;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import se.Mapping.FileMapping;
import se.config.SpringConfig;
import se.domain.File;

import java.util.ArrayList;
import java.util.List;

public class FileRepositoryImpl implements FileRepository {
    private List<File> files = new ArrayList<File>();
    public AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext(SpringConfig.class);
    private JdbcTemplate jdbcTemplate = a.getBean(JdbcTemplate.class);
    public FileRepositoryImpl() {
        String sql = "SELECT * FROM file";
        files.addAll(jdbcTemplate.query(sql, new FileMapping()));// UserMapping()- явно указать как парсить
    }



    public void save(File file) {
        jdbcTemplate.update(
                "INSERT INTO file (id, name, date) VALUES (?, ?, ?)",
                file.getId(), file.getName(), file.getDate()
        );
        files.add(file);
    }

    public void delete(Integer id) {
        jdbcTemplate.update("delete from file where id = ?", id);
        //files.remove(file);
    }

    public List<File> getAll() {
        List<File> file = new ArrayList<File>();
        String sql = "SELECT * FROM file";
        file.addAll(jdbcTemplate.query(sql, new FileMapping()));// UserMapping()- явно указать как парсить
        return file;
    }

    public File getById(Integer id) {
        String sql = "select * from file where id = " + id.toString();
        return jdbcTemplate.query(sql, new FileMapping()).get(0);
    }

    public void update(Integer id, String name,String date) {

        jdbcTemplate.update("update file set name = ?, date = ?, id = ? where id = ?",  name, date, id, id);

        }

    @Override
    public List<File> select(Integer id, String title,String date) {
        List<File> files = new ArrayList<File>();
        StringBuilder sql = new StringBuilder("select * from file");
        if (id == 0 && title.trim().isEmpty() && date.trim().isEmpty()){
            files.addAll(jdbcTemplate.query(sql.toString(), new FileMapping()));
            return files;
        }
        sql.append(" where");
        if (id != 0)
            sql.append(" id = ").append(id.toString()).append(" and");
        if (!title.trim().isEmpty())
            sql.append(" name = '").append(title).append("' and");
        if (!date.trim().isEmpty())
            sql.append(" date = '").append(date).append("'");
        if (sql.lastIndexOf("d") == sql.length()-1)
            sql.delete((sql.length()-4), sql.length());
        files.addAll(jdbcTemplate.query(sql.toString(), new FileMapping()));
        return files;
    }
}