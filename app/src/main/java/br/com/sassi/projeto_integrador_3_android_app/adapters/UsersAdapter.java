package br.com.sassi.projeto_integrador_3_android_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.sassi.projeto_integrador_3_android_app.R;
import br.com.sassi.projeto_integrador_3_android_app.models.Cliente;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    private Context mCtx;
    private List<Cliente> clienteList;

    public UsersAdapter(Context mCtx, List<Cliente> clienteList) {
        this.mCtx = mCtx;
        this.clienteList = clienteList;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_users, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {

        Cliente cliente = clienteList.get(position);

        holder.textViewNomeCliente.setText(cliente.getNomeCliente());
        holder.textViewNomeAmostra.setText(cliente.getNomeAmostra());
        holder.textViewExame.setText(cliente.getExame());
        holder.textViewConcentracaoComposto.setText(cliente.getConcetracaoComposto());
        holder.textViewNumeroContrato.setText(cliente.getNumeroContrato());
        holder.textViewTempoExposicao.setText(cliente.getTempoExposicao());
        holder.textViewObservacao.setText(cliente.getObservacao());

    }

    @Override
    public int getItemCount() {
        return clienteList.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder{

        TextView textViewNomeCliente, textViewNomeAmostra, textViewExame, textViewNumeroContrato, textViewConcentracaoComposto, textViewTempoExposicao, textViewObservacao;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNomeCliente = itemView.findViewById(R.id.textViewNomeCliente);
            textViewNomeAmostra = itemView.findViewById(R.id.textViewNomeAmostra);
            textViewExame = itemView.findViewById(R.id.textViewExame);
            textViewNumeroContrato = itemView.findViewById(R.id.textViewNumeroContrato);
            textViewConcentracaoComposto = itemView.findViewById(R.id.textViewConcentracaoComposto);
            textViewTempoExposicao = itemView.findViewById(R.id.textViewTempoExposicao);
            textViewObservacao = itemView.findViewById(R.id.textViewObservacao);
        }
    }
}
