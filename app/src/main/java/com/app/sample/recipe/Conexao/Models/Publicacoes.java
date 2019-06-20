/*
 *
 *  *                                     /@
 *  *                      __        __   /\/
 *  *                     /==\      /  \_/\/
 *  *                   /======\    \/\__ \__
 *  *                 /==/\  /\==\    /\_|__ \
 *  *              /==/    ||    \=\ / / / /_/
 *  *            /=/    /\ || /\   \=\/ /
 *  *         /===/   /   \||/   \   \===\
 *  *       /===/   /_________________ \===\
 *  *    /====/   / |                /  \====\
 *  *  /====/   /   |  _________    /      \===\
 *  *  /==/   /     | /   /  \ / / /         /===/
 *  * |===| /       |/   /____/ / /         /===/
 *  *  \==\             /\   / / /          /===/
 *  *  \===\__    \    /  \ / / /   /      /===/   ____                    __  _         __  __                __
 *  *    \==\ \    \\ /____/   /_\ //     /===/   / __ \__  ______  ____ _/ /_(_)____   / / / /__  ____ ______/ /_
 *  *    \===\ \   \\\\\\\/   ///////     /===/  / / / / / / / __ \/ __ `/ __/ / ___/  / /_/ / _ \/ __ `/ ___/ __/
 *  *      \==\/     \\\\/ / //////       /==/  / /_/ / /_/ / / / / /_/ / /_/ / /__   / __  /  __/ /_/ / /  / /_
 *  *      \==\     _ \\/ / /////        |==/   \___\_\__,_/_/ /_/\__,_/\__/_/\___/  /_/ /_/\___/\__,_/_/   \__/
 *  *        \==\  / \ / / ///          /===/
 *  *        \==\ /   / / /________/    /==/
 *  *          \==\  /               | /==/
 *  *          \=\  /________________|/=/
 *  *            \==\     _____     /==/
 *  *           / \===\   \   /   /===/
 *  *          / / /\===\  \_/  /===/
 *  *         / / /   \====\ /====/
 *  *        / / /      \===|===/
 *  *        |/_/         \===/
 *  *                       =
 *  *
 *  * Copyright(c) Developed by John Alves at 2018/$today.mouth/10 at 8:50:17 for quantic heart studios
 *
 */

package com.app.sample.recipe.Conexao.Models;

import java.io.Serializable;

public class Publicacoes implements Serializable {
    private String id;
    private String id_usuario;
    private String titulo;
    private String ingrendies;
    private String descricao;
    private String imagem;
    private String ativacao;

    public Publicacoes(String id, String id_usuario, String titulo, String ingrendies, String descricao, String imagem, String ativacao) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.titulo = titulo;
        this.ingrendies = ingrendies;
        this.descricao = descricao;
        this.imagem = imagem;
        this.ativacao = ativacao;
    }

    public Publicacoes( String id, String titulo, String ingrendies, String descricao, String imagem) {
        this.id = id;
        this.titulo = titulo;
        this.ingrendies = ingrendies;
        this.descricao = descricao;
        this.imagem = imagem;
    }
    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIngrendies() {
        return ingrendies;
    }

    public void setIngrendies(String ingrendies) {
        this.ingrendies = ingrendies;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getAtivacao() {
        return ativacao;
    }

    public void setAtivacao(String ativacao) {
        this.ativacao = ativacao;
    }

    @Override
    public String toString() {
        return "Publicacoes{" +
                "id='" + id + '\'' +
                ", id_usuario='" + id_usuario + '\'' +
                ", titulo='" + titulo + '\'' +
                ", ingrendies='" + ingrendies + '\'' +
                ", descricao='" + descricao + '\'' +
                ", imagem='" + imagem + '\'' +
                ", ativacao='" + ativacao + '\'' +
                '}';
    }
}
