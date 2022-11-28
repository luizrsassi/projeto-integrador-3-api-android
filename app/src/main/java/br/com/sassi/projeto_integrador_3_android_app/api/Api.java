package br.com.sassi.projeto_integrador_3_android_app.api;

import br.com.sassi.projeto_integrador_3_android_app.models.ClientesResponse;
import br.com.sassi.projeto_integrador_3_android_app.models.DefaultResponse;
import br.com.sassi.projeto_integrador_3_android_app.models.LoginResponse;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    @FormUrlEncoded
    @POST("createamostra")
    Call<DefaultResponse> createAmostra(
            @Field("password") String password,
            @Field("nomeCliente") String nomeCliente,
            @Field("nomeAmostra") String nomeAmostra,
            @Field("exame") String exame,
            @Field("numeroContrato") String numeroContrato,
            @Field("concetracaoComposto") String concetracaoComposto,
            @Field("tempoExposicao") String tempoExposicao,
            @Field("Observacao") String Observacao
    );

    @FormUrlEncoded
    @POST("clientelogin")
    Call<LoginResponse> clienteLogin(
            @Field("numeroContrato") String numeroContrato,
            @Field("password") String password
    );

    @GET("allamostras")
    Call<ClientesResponse> getClientes();

    @FormUrlEncoded
    @PUT("updateamostra/{id}")
    Call<LoginResponse> updateCliente(
            @Path("id") int id,
            @Field("nomeCliente") String nomeCliente,
            @Field("nomeAmostra") String nomeAmostra,
            @Field("exame") String exame,
            @Field("numeroContrato") String numeroContrato,
            @Field("concetracaoComposto") String concetracaoComposto,
            @Field("tempoExposicao") String tempoExposicao,
            @Field("Observacao") String Observacao
    );

    @FormUrlEncoded
    @PUT("updatepassword")
    Call<DefaultResponse> updatePassword(
            @Field("currentpassword") String currentpassword,
            @Field("newpassword") String newpassword,
            @Field("numeroContrato") String numeroContrato
    );

    @DELETE("deleteamostra/{id}")
    Call<DefaultResponse> deleteCliente(@Path("id") int id);

}
