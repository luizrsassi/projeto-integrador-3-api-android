package br.com.sassi.projeto_integrador_3_android_app.models;

public class Cliente {

    private int id;
    private String nomeCliente,
            nomeAmostra, exame,
            numeroContrato,
            concetracaoComposto,
            tempoExposicao,
            Observacao;

    public Cliente(int id, String nomeCliente, String nomeAmostra, String exame, String numeroContrato, String concetracaoComposto, String tempoExposicao, String observacao) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.nomeAmostra = nomeAmostra;
        this.exame = exame;
        this.numeroContrato = numeroContrato;
        this.concetracaoComposto = concetracaoComposto;
        this.tempoExposicao = tempoExposicao;
        this.Observacao = observacao;
    }

    public int getId() {
        return id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getNomeAmostra() {
        return nomeAmostra;
    }

    public String getExame() {
        return exame;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public String getConcetracaoComposto() {
        return concetracaoComposto;
    }

    public String getTempoExposicao() {
        return tempoExposicao;
    }

    public String getObservacao() {
        return Observacao;
    }
}
