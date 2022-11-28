package br.com.sassi.projeto_integrador_3_android_app.storage;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.sassi.projeto_integrador_3_android_app.models.Cliente;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "my_shared_preff";

    private static SharedPrefManager mInstance;
    private Context mCtx;

    private SharedPrefManager(Context mCtx){
        this.mCtx = mCtx;
    }

    public static synchronized SharedPrefManager getInstance(Context mCtx){
        if (mInstance == null){
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }

    public void saveCliente(Cliente cliente){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id", cliente.getId());
        editor.putString("nomeCliente", cliente.getNomeCliente());
        editor.putString("nomeAmostra", cliente.getNomeAmostra());
        editor.putString("exame", cliente.getExame());
        editor.putString("numeroContrato", cliente.getNumeroContrato());
        editor.putString("concetracaoComposto", cliente.getConcetracaoComposto());
        editor.putString("tempoExposicao", cliente.getTempoExposicao());
        editor.putString("Observacao", cliente.getObservacao());

        editor.apply();

    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getInt("id", -1) != -1;
    }

    public Cliente getCliente(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return new Cliente(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("nomeCliente", null),
                sharedPreferences.getString("nomeAmostra", null),
                sharedPreferences.getString("exame", null),
                sharedPreferences.getString("numeroContrato", null),
                sharedPreferences.getString("concetracaoComposto", null),
                sharedPreferences.getString("tempoExposicao", null),
                sharedPreferences.getString("Observacao", null)
        );
    }

    public void clear(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();
    }
}
