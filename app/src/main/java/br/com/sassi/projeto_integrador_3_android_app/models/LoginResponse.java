package br.com.sassi.projeto_integrador_3_android_app.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("amostra")
    private Cliente cliente;

    public LoginResponse(boolean error, String message, Cliente cliente) {
        this.error = error;
        this.message = message;
        this.cliente = cliente;
    }

    public boolean isError() { return error; }

    public String getMessage() {
        return message;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
