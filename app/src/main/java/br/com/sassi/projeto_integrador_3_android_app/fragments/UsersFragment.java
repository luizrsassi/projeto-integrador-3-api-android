package br.com.sassi.projeto_integrador_3_android_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.sassi.projeto_integrador_3_android_app.R;
import br.com.sassi.projeto_integrador_3_android_app.adapters.UsersAdapter;
import br.com.sassi.projeto_integrador_3_android_app.api.RetrofitClient;
import br.com.sassi.projeto_integrador_3_android_app.models.Cliente;
import br.com.sassi.projeto_integrador_3_android_app.models.ClientesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersFragment extends Fragment {

    private RecyclerView recyclerView;
    private UsersAdapter adapter;
    private List<Cliente> clienteList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.users_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<ClientesResponse> call = RetrofitClient.getInstance().getApi().getClientes();

        call.enqueue(new Callback<ClientesResponse>() {
            @Override
            public void onResponse(@NonNull Call<ClientesResponse> call, @NonNull Response<ClientesResponse> response) {

                assert response.body() != null;
                clienteList = response.body().getClientes();

                adapter = new UsersAdapter(getActivity(), clienteList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<ClientesResponse> call, @NonNull Throwable t) {

            }
        });

    }
}
