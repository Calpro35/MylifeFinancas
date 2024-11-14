package br.com.fiap.mylifefinancas.model;

public class TipoServico {
    private int cd_tipo_servico;
    private String tipo_servico;

    public TipoServico(int cd_tipo_servico, String tipo_servico) {
        this.cd_tipo_servico = cd_tipo_servico;
        this.tipo_servico = tipo_servico;
    }

    public TipoServico(int cd_tipo_servico) {
        this.cd_tipo_servico = cd_tipo_servico;
    }

    public TipoServico(String tipo_servico) {
        this.tipo_servico = tipo_servico;
    }

    public int getCd_tipo_servico() {
        return cd_tipo_servico;
    }

    public void setCd_tipo_servico(int cd_tipo_servico) {
        this.cd_tipo_servico = cd_tipo_servico;
    }

    public String getTipo_servico() {
        return tipo_servico;
    }

    public void setTipo_servico(String tipo_servico) {
        this.tipo_servico = tipo_servico;
    }
}
