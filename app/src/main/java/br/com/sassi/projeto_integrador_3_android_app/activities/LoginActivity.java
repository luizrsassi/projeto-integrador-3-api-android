package br.com.sassi.projeto_integrador_3_android_app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.sassi.projeto_integrador_3_android_app.R;
import br.com.sassi.projeto_integrador_3_android_app.api.RetrofitClient;

import br.com.sassi.projeto_integrador_3_android_app.models.LoginResponse;
import br.com.sassi.projeto_integrador_3_android_app.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNumeroContrato, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextNumeroContrato = findViewById(R.id.editTextNumeroContrato);
        editTextPassword = findViewById(R.id.editTextPassword);

        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.textViewRegister).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void clienteLogin(){
        String numeroContrato = editTextNumeroContrato.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


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

        if(numeroContrato.isEmpty()){
            editTextNumeroContrato.setError("School is required");
            editTextNumeroContrato.requestFocus();
            return;
        }

        Call<LoginResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .clienteLogin(numeroContrato, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                SharedPrefManager.getInstance(LoginActivity.this)
                        .saveCliente(loginResponse.getCliente());

                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<LoginResponse> call, @NonNull Throwable t) {

                Toast.makeText(LoginActivity.this, "Usuário ou senha incorretos", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonLogin:
                clienteLogin();
                break;
            case R.id.textViewRegister:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}