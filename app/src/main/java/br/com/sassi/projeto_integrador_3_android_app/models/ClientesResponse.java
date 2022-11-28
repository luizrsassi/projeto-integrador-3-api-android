package br.com.sassi.projeto_integrador_3_android_app.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClientesResponse {

    @SerializedName("error")
    private boolean error;

    @SerializedName("users")
    private List<Cliente> clientes;

    public ClientesResponse(boolean error, List<Cliente> clientes) {
        this.error = error;
        this.clientes = clientes;
    }

    public boolean isError() {
        return error;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}
