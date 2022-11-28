package br.com.sassi.projeto_integrador_3_android_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import br.com.sassi.projeto_integrador_3_android_app.models.DefaultResponse;
import br.com.sassi.projeto_integrador_3_android_app.R;
import br.com.sassi.projeto_integrador_3_android_app.api.RetrofitClient;
import br.com.sassi.projeto_integrador_3_android_app.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNomeCliente, editTextPassword, editTextNomeAmostra, editTextExame, editTextNumeroContrato, editTextConcetracaoComposto, editTextTempoExposicao, editTextObservacao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPassword = findViewById(R.id.editTextPassword);
        editTextNomeCliente = findViewById(R.id.editTextNomeCliente);
        editTextNomeAmostra = findViewById(R.id.editTextNomeAmostra);
        editTextExame = findViewById(R.id.editTextExame);
        editTextNumeroContrato = findViewById(R.id.editTextNumeroContrato);
        editTextConcetracaoComposto = findViewById(R.id.editTextConcetracaoComposto);
        editTextTempoExposicao = findViewById(R.id.editTextTempoExposicao);
        editTextObservacao = findViewById(R.id.editTextObservacao);

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignUp:
                entrarLogin();
                break;
            case R.id.textViewLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

        private void entrarLogin() {
            String password = editTextPassword.getText().toString().trim();
            String nomeCliente = editTextNomeCliente.getText().toString().trim();
            String nomeAmostra = editTextNomeAmostra.getText().toString().trim();
            String exame = editTextExame.getText().toString().trim();
            String numeroContrato = editTextNumeroContrato.getText().toString().trim();
            String concetracaoComposto = editTextConcetracaoComposto.getText().toString().trim();
            String tempoExposicao = editTextTempoExposicao.getText().toString().trim();
            String Observacao = editTextObservacao.getText().toString().trim();


            if(nomeCliente.isEmpty()){
                editTextNomeCliente.setError("Preencher campo nome");
                editTextNomeCliente.requestFocus();
                return;
            }

            if(password.isEmpty()){
                editTextPassword.setError("Preencher com a senha");
                editTextPassword.requestFocus();
                return;
            }

            if(password.length() < 6){
                editTextPassword.setError("A senha deve ter 6 caracteres");
                editTextPassword.requestFocus();
                return;
            }

            if(nomeAmostra.isEmpty()){
                editTextNomeAmostra.setError("Name required");
                editTextNomeAmostra.requestFocus();
                return;
            }

            if(exame.isEmpty()){
                editTextExame.setError("School is required");
                editTextExame.requestFocus();
                return;
            }

            if(numeroContrato.isEmpty()){
                editTextNumeroContrato.setError("School is required");
                editTextNumeroContrato.requestFocus();
                return;
            }

            if(concetracaoComposto.isEmpty()){
                editTextConcetracaoComposto.setError("School is required");
                editTextConcetracaoComposto.requestFocus();
                return;
            }

            if(tempoExposicao.isEmpty()){
                editTextTempoExposicao.setError("School is required");
                editTextTempoExposicao.requestFocus();
                return;
            }

            if(Observacao.isEmpty()){
                editTextObservacao.setError("School is required");
                editTextObservacao.requestFocus();
                return;
            }

            Call<DefaultResponse> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .createAmostra(password, nomeCliente, nomeAmostra, exame, numeroContrato, concetracaoComposto, tempoExposicao, Observacao);

            call.enqueue(new Callback<DefaultResponse>() {
                @Override
                public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                    if(response.code() == 201){

                        DefaultResponse dr = response.body();
                        Toast.makeText(MainActivity.this, dr.getMsg(), Toast.LENGTH_LONG).show();

                    } else if(response.code() == 422){
                        Toast.makeText(MainActivity.this, "A amostra já existe", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<DefaultResponse> call, Throwable t) {

                }
            });


        }
}