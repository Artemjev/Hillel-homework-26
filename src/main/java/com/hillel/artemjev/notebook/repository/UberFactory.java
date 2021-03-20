package com.hillel.artemjev.notebook.repository;


import com.hillel.artemjev.notebook.repository.note.NotesRepository;
import com.hillel.artemjev.notebook.repository.note.NotesRepositoryDbImpl;
import com.hillel.artemjev.notebook.util.jdbcquery.JdbcTemplate;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;


public class UberFactory {
    private static final UberFactory INSTANCE = new UberFactory();

    public static UberFactory instance() {
        return INSTANCE;
    }

    private final NotesRepository NOTE_REPOSITORY;

    private UberFactory() {
        String dsn = "jdbc:postgresql://localhost:5432/notebook";
        String user = "postgres";
        String password = "0000";
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dsn);
        config.setUsername(user);
        config.setPassword(password);
        config.setMaximumPoolSize(8);
        config.setMinimumIdle(4);
        config.setDriverClassName("org.postgresql.Driver");
        DataSource dataSource = new HikariDataSource(config);
        NOTE_REPOSITORY = new NotesRepositoryDbImpl(
                new JdbcTemplate(dataSource)
        );
    }

    public NotesRepository getNotesRepository() {
        return NOTE_REPOSITORY;
    }
}
