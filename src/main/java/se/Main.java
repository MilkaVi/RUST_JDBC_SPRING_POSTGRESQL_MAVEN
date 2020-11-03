package se;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import se.Mapping.FileMapping;
import se.Mapping.UserMapping;
import se.domain.File;
import se.domain.User;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


            DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
            driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/TEST_BD");
            driverManagerDataSource.setUsername("root");
            driverManagerDataSource.setPassword("4321rewq");
            driverManagerDataSource.setDriverClassName("org.postgresql.Driver");

            JdbcTemplate jdbcTemplate = new JdbcTemplate(driverManagerDataSource);

            List<User> file = new ArrayList<User>();
            String sql = "SELECT * FROM usr";
            file.addAll(jdbcTemplate.query(sql, new UserMapping()));

            System.out.println(file);


    }
}
