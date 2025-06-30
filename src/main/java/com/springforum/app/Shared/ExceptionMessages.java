package com.springforum.app.Shared;

public enum ExceptionMessages {

    USER_NOT_FOUND("Usuário não encontrado na base de dados."),
    POST_NOT_FOUND("Postagem não encontrada na base de dados."),
    TOPIC_NOT_FOUND("Tópico não encontrado na base de dados."),
    REPLY_NOT_FOUND("Resposta não encontrada na base de dados."),
    AUTHENTICATION_ERROR("Erro na autenticação: credenciais inválidas");


    private final String exceptionMessage;

    ExceptionMessages(String message){
        this.exceptionMessage = message;
    }

    public String getMessage(){
        return exceptionMessage;
    }
}
