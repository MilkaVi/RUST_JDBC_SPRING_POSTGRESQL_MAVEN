package se.Mapping;

import org.springframework.jdbc.core.RowMapper;
import se.domain.File;

import java.sql.ResultSet;
import java.sql.SQLException;

//парсер
public class FileMapping implements RowMapper<File> {

    @Override
    public File mapRow(ResultSet resultSet, int i) throws SQLException {
        File file = new File();
        file.setId(resultSet.getInt("id"));
        file.setName(resultSet.getString("name"));
        file.setDate(resultSet.getString("date"));
        file.setFile_user(resultSet.getInt("file_user"));
        return file;
    }
}
