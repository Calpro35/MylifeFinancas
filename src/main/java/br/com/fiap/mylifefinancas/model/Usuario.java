package br.com.fiap.mylifefinancas.model;

import java.time.LocalDate;

public class Usuario {

    private int cd_usuario;
    private String nm_usuario;
    private String sobrenome_usuario;
    private LocalDate dt_nasc_usuario;
    private String email_usuario;
    private String senha_usuario;

    public Usuario(int cd_usuario, String nm_usuario, String sobrenome_usuario, LocalDate dt_nasc_usuario, String email_usuario, String senha_usuario) {
        this.cd_usuario = cd_usuario;
        this.nm_usuario = nm_usuario;
        this.sobrenome_usuario = sobrenome_usuario;
        this.dt_nasc_usuario = dt_nasc_usuario;
        this.email_usuario = email_usuario;
        this.senha_usuario = senha_usuario;
    }

    public Usuario(String nm_usuario, String sobrenome_usuario, LocalDate dt_nasc_usuario, String email_usuario, String senha_usuario) {
        this.nm_usuario = nm_usuario;
        this.sobrenome_usuario = sobrenome_usuario;
        this.dt_nasc_usuario = dt_nasc_usuario;
        this.email_usuario = email_usuario;
        this.senha_usuario = senha_usuario;
    }

    public Usuario(String email_usuario, String senha_usuario){
        this.email_usuario = email_usuario;
        this.senha_usuario = senha_usuario;
    }

    public Usuario(){
    }

    public int getCd_usuario() {
        return cd_usuario;
    }

    public void setCd_usuario(int cd_usuario) {
        this.cd_usuario = cd_usuario;
    }

    public String getNm_usuario() {
        return nm_usuario;
    }

    public void setNm_usuario(String nm_usuario) {
        this.nm_usuario = nm_usuario;
    }

    public String getSobrenome_usuario() {
        return sobrenome_usuario;
    }

    public void setSobrenome_usuario(String sobrenome_usuario) {
        this.sobrenome_usuario = sobrenome_usuario;
    }

    public LocalDate getDt_nasc_usuario() {
        return dt_nasc_usuario;
    }

    public void setDt_nasc_usuario(LocalDate dt_nasc_usuario) {
        this.dt_nasc_usuario = dt_nasc_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }
}
