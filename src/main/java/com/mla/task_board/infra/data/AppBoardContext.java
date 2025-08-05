package com.mla.task_board.infra.data;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class AppBoardContext extends JdbcTemplate {

    public AppBoardContext(DataSource dataSource){
        super(dataSource);
    }
}
