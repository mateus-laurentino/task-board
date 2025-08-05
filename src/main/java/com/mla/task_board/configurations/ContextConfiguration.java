package com.mla.task_board.configurations;

import com.mla.task_board.infra.data.AppBoardContext;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Configuration
public class ContextConfiguration {

    @Bean
    public AppBoardContext boardContext(DataSource ds) {
        return new AppBoardContext(ds);
    }

    @Bean(initMethod = "migrate")
    public Flyway flywayDbBoard(DataSource ds) {
        return Flyway.configure()
                .dataSource(ds)
                .locations("classpath:db/migrations")
                .baselineOnMigrate(true)
                .load();
    }
}
