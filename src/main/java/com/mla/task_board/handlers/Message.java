package com.mla.task_board.handlers;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class Message {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("messages");
    public static final String DEFAULT_KEY = "erro.";

    public static String getErro(String key, Object... params) {
        String mensagem = BUNDLE.getString(DEFAULT_KEY + key);
        return MessageFormat.format(mensagem, params);
    }
}
