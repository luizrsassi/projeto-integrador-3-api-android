package br.com.sassi.projeto_integrador_3_android_app.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import br.com.sassi.projeto_integrador_3_android_app.R;
import br.com.sassi.projeto_integrador_3_android_app.activities.LoginActivity;
import br.com.sassi.projeto_integrador_3_android_app.activities.MainActivity;
import br.com.sassi.projeto_integrador_3_android_app.activities.ProfileActivity;
import br.com.sassi.projeto_integrador_3_android_app.api.RetrofitClient;
import br.com.sassi.projeto_integrador_3_android_app.models.Cliente;
import br.com.sassi.projeto_integrador_3_android_app.models.DefaultResponse;
import br.com.sassi.projeto_integrador_3_android_app.models.LoginResponse;
import br.com.sassi.projeto_integrador_3_android_app.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragment extends Fragment implements View.OnClickListener{

    private EditText editTextNomeCliente, editTextNomeAmostra, editTextExame, editTextNumeroContrato;
    private EditText editTextConcetracaoComposto, editTextTempoExposicao, editTextObservacao;
    private EditText editTextCurrentPassword, editTextNewPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextNomeCliente = view.findViewById(R.id.editTextNomeCliente);
        editTextNomeAmostra = view.findViewById(R.id.editTextNomeAmostra);
        editTextExame = view.findViewById(R.id.editTextExame);
        editTextNumeroContrato = view.findViewById(R.id.editTextNumeroContrato);
        editTextConcetracaoComposto = view.findViewById(R.id.editTextConcetracaoComposto);
        editTextTempoExposicao = view.findViewById(R.id.editTextTempoExposicao);
        editTextObservacao = view.findViewById(R.id.editTextObservacao);
        editTextCurrentPassword = view.findViewById(R.id.editTextCurrentPassword);
        editTextNewPassword = view.findViewById(R.id.editTextNewPassword);

        view.findViewById(R.id.buttonSave).setOnClickListener(this);
        view.findViewById(R.id.buttonChangePassword).setOnClickListener(this);
        view.findViewById(R.id.buttonLogout).setOnClickListener(this);
        view.findViewById(R.id.buttonDelete).setOnClickListener(this);
    }

    private void updateProfile(){
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

        Cliente cliente = SharedPrefManager.getInstance(getActivity()).getCliente();

        Call<LoginResponse> call = RetrofitClient
                .getInstance()
                .getApi().updateCliente(
                        cliente.getId(),
                        nomeCliente,
                        nomeAmostra,
                        exame,
                        numeroContrato,
                        concetracaoComposto,
                        tempoExposicao,
                        Observacao
                );

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {

                assert response.body() != null;
                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                SharedPrefManager.getInstance(getActivity()).saveCliente(response.body().getCliente());

            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {

            }
        });
    }

    private void updatePassword() {
        String currentpassword = editTextCurrentPassword.getText().toString().trim();
        String newpassword = editTextNewPassword.getText().toString().trim();

        if(currentpassword.isEmpty()){
            editTextCurrentPassword.setError("Digite o password");
            editTextCurrentPassword.requestFocus();
            return;
        }

        if(newpassword.isEmpty()){
            editTextNewPassword.setError("Digite um novo password");
            editTextNewPassword.requestFocus();
            return;
        }

        if(newpassword.length() < 6){
            editTextNewPassword.setError("A senha deve ter 6 caracteres");
            editTextNewPassword.requestFocus();
            return;
        }

        Cliente cliente = SharedPrefManager.getInstance(getActivity()).getCliente();

        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .updatePassword(currentpassword, newpassword, cliente.getNumeroContrato());

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }

    private void logout() {
        SharedPrefManager.getInstance(getActivity()).clear();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void deleteAmostra() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Are you sure?");
        builder.setMessage("This action is irreversible...");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Cliente cliente = SharedPrefManager.getInstance(getActivity()).getCliente();

                Call<DefaultResponse> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .deleteCliente(cliente.getId());

                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        SharedPrefManager.getInstance(getActivity()).clear();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog ad = builder.create();
        ad.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSave:
                updateProfile();
                break;
            case R.id.buttonChangePassword:
                updatePassword();
                break;
            case R.id.buttonLogout:
                logout();
                break;
            case R.id.buttonDelete:
                deleteAmostra();
                break;
        }
    }


}
