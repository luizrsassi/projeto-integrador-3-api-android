package br.com.sassi.projeto_integrador_3_android_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import br.com.sassi.projeto_integrador_3_android_app.R;
import br.com.sassi.projeto_integrador_3_android_app.storage.SharedPrefManager;

public class HomeFragment extends Fragment {

    TextView textViewNomeCliente,
            textViewNomeAmostra,
            textViewExame,
            textViewNumeroContrato,
            textViewConcetracaoComposto,
            textViewTempoExposicao,
            textViewObservacao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewNomeCliente = view.findViewById(R.id.textViewNomeCliente);
        textViewNomeAmostra = view.findViewById(R.id.textViewNomeAmostra);
        textViewExame = view.findViewById(R.id.textViewExame);
        textViewNumeroContrato = view.findViewById(R.id.textViewNumeroContrato);
        textViewConcetracaoComposto = view.findViewById(R.id.textViewConcentracaoComposto);
        textViewTempoExposicao = view.findViewById(R.id.textViewTempoExposicao);
        textViewObservacao = view.findViewById(R.id.textViewObservacao);

        textViewNomeCliente.setText(SharedPrefManager.getInstance(getActivity()).getCliente().getNomeCliente());
        textViewNomeAmostra.setText(SharedPrefManager.getInstance(getActivity()).getCliente().getNomeAmostra());
        textViewExame.setText(SharedPrefManager.getInstance(getActivity()).getCliente().getExame());
        textViewNumeroContrato.setText(SharedPrefManager.getInstance(getActivity()).getCliente().getNumeroContrato());
        textViewConcetracaoComposto.setText(SharedPrefManager.getInstance(getActivity()).getCliente().getConcetracaoComposto());
        textViewTempoExposicao.setText(SharedPrefManager.getInstance(getActivity()).getCliente().getTempoExposicao());
        textViewObservacao.setText(SharedPrefManager.getInstance(getActivity()).getCliente().getObservacao());
    }
}
