package br.com.fiap.mylifefinancas.model;

import java.time.LocalDate;

public class Servico {
    private int cd_servico;
    private int cd_usuario;
    private TipoServico tipoServico;
    private String nm_servico;
    private double vl_servico;
    private LocalDate dt_servico;
    private double vl_saida_servico;
    private LocalDate dt_saida_servico;
    private String dsc_servico;

    public Servico(int cd_servico, int cd_usuario, TipoServico tipoServico, String nm_servico,
                   double vl_servico, LocalDate dt_servico, double vl_saida_servico, LocalDate dt_saida_servico, String dsc_servico) {
        this.cd_servico = cd_servico;
        this.cd_usuario = cd_usuario;
        this.tipoServico = tipoServico;
        this.nm_servico = nm_servico;
        this.vl_servico = vl_servico;
        this.dt_servico = dt_servico;
        this.vl_saida_servico = vl_saida_servico;
        this.dt_saida_servico = dt_saida_servico;
        this.dsc_servico = dsc_servico;
    }

    public Servico(int cd_usuario, TipoServico tipoServico, String nm_servico,
                   double vl_servico, LocalDate dt_servico, double vl_saida_servico, LocalDate dt_saida_servico, String dsc_servico) {
        this.cd_usuario = cd_usuario;
        this.tipoServico = tipoServico;
        this.nm_servico = nm_servico;
        this.vl_servico = vl_servico;
        this.dt_servico = dt_servico;
        this.vl_saida_servico = vl_saida_servico;
        this.dt_saida_servico = dt_saida_servico;
        this.dsc_servico = dsc_servico;
    }

    public Servico(int cd_usuario, TipoServico tipoServico, String nm_servico,
                   double vl_servico, LocalDate dt_servico, String dsc_servico) {
        this.cd_usuario = cd_usuario;
        this.tipoServico = tipoServico;
        this.nm_servico = nm_servico;
        this.vl_servico = vl_servico;
        this.dt_servico = dt_servico;
        this.dsc_servico = dsc_servico;
    }

    public Servico(int cd_servico, int cd_usuario, String nm_servico,
                   double vl_servico, LocalDate dt_servico, double vl_saida_servico, LocalDate dt_saida_servico, String dsc_servico) {
        this.cd_servico = cd_servico;
        this.cd_usuario = cd_usuario;
        this.nm_servico = nm_servico;
        this.vl_servico = vl_servico;
        this.dt_servico = dt_servico;
        this.vl_saida_servico = vl_saida_servico;
        this.dt_saida_servico = dt_saida_servico;
        this.dsc_servico = dsc_servico;
    }

    public Servico(int cd_servico, int cd_usuario, int cd_tipo, String nm_servico,
                   double vl_servico, LocalDate dt_servico, double vl_saida_servico, LocalDate dt_saida_servico, String dsc_servico) {
        this.cd_servico = cd_servico;
        this.cd_usuario = cd_usuario;
        this.tipoServico = new TipoServico(cd_tipo);
        this.nm_servico = nm_servico;
        this.vl_servico = vl_servico;
        this.dt_servico = dt_servico;
        this.vl_saida_servico = vl_saida_servico;
        this.dt_saida_servico = dt_saida_servico;
        this.dsc_servico = dsc_servico;
    }

    public Servico(double valor){
        this.vl_servico = valor;
    }

    public Servico(){}

    public int getCd_servico() {
        return cd_servico;
    }

    public void setCd_servico(int cd_servico) {
        this.cd_servico = cd_servico;
    }

    public int getCd_usuario() {
        return cd_usuario;
    }

    public void setCd_usuario(int cd_usuario) {
        this.cd_usuario = cd_usuario;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getNm_servico() {
        return nm_servico;
    }

    public void setNm_servico(String nm_servico) {
        this.nm_servico = nm_servico;
    }

    public double getVl_servico() {
        return vl_servico;
    }

    public void setVl_servico(double vl_servico) {
        this.vl_servico = vl_servico;
    }

    public LocalDate getDt_servico() {
        return dt_servico;
    }

    public void setDt_servico(LocalDate dt_servico) {
        this.dt_servico = dt_servico;
    }

    public String getDsc_servico() {
        return dsc_servico;
    }

    public void setDsc_servico(String dsc_servico) {
        this.dsc_servico = dsc_servico;
    }

    public double getVl_saida_servico() {
        return vl_saida_servico;
    }

    public void setVl_saida_servico(double vl_saida_servico) {
        this.vl_saida_servico = vl_saida_servico;
    }

    public LocalDate getDt_saida_servico() {
        return dt_saida_servico;
    }

    public void setDt_saida_servico(LocalDate dt_saida_servico) {
        this.dt_saida_servico = dt_saida_servico;
    }
}
