package com.app.sample.recipe.Conexao.Models;

public class exibir_comentarios {

    private String id;
    private String id_usuarios;
    private String id_publicacao;
    private String date_time;
    private String nome;
    private String comentario;
    private String ativacao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(String id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public String getId_publicacao() {
        return id_publicacao;
    }

    public void setId_publicacao(String id_publicacao) {
        this.id_publicacao = id_publicacao;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getAtivacao() {
        return ativacao;
    }

    public void setAtivacao(String ativacao) {
        this.ativacao = ativacao;
    }
}
