package br.com.sassi.projeto_integrador_3_android_app.models;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {

    @SerializedName("error")
    private boolean erro;

    @SerializedName("message")
    private String msg;

    public DefaultResponse(boolean err, String msg) {
        this.erro = err;
        this.msg = msg;
    }

    public boolean isErro() {
        return erro;
    }

    public String getMsg() {
        return msg;
    }

}
