package com.how_vi.autoescola.veiculo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.how_vi.autoescola.R;
import com.how_vi.autoescola.data.model.VeiculoModel;

public class VeiculoRVAdapter extends ListAdapter<VeiculoModel, VeiculoRVAdapter.ViewHolder> {

    // criando variavel para o itemClickListener
    private OnItemClickListener listener;

    // criando construtor para o adapter
    public VeiculoRVAdapter(){
        super(DIFF_CALLBACK);
    }

    // criando callback para o Recycler View
    private static final DiffUtil.ItemCallback<VeiculoModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<VeiculoModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull VeiculoModel oldItem, @NonNull VeiculoModel newItem) {
            return oldItem.getCoVeiculo() == newItem.getCoVeiculo();
        }

        @Override
        public boolean areContentsTheSame(@NonNull VeiculoModel oldItem, @NonNull VeiculoModel newItem) {
           // exibe os atributos na lista
            return oldItem.getDeMarca().equals(newItem.getDeMarca()) &&
                   oldItem.getDeModelo().equals(newItem.getDeModelo()) &&
                   oldItem.getNuPlaca().equals(newItem.getNuPlaca());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        // preenche o layout com os itens da lista
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.veiculo_rv_item, parent, false);
        return new ViewHolder(item);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        // define os atributos dos itens exibidos na lista
        VeiculoModel model = getVeiculoAt(position);
        holder.deMarcaVeiculoTV.setText(model.getDeMarca());
        holder.deModeloVeiculoTV.setText(model.getDeModelo());
        holder.nuPlacaVeiculoTV.setText(model.getNuPlaca());
    }

    // criando metodo get para selecionar item da lista
    public VeiculoModel getVeiculoAt(int position){
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView deMarcaVeiculoTV;
        TextView deModeloVeiculoTV;
        TextView nuPlacaVeiculoTV;

        ViewHolder(@NonNull View itemView){
            super(itemView);
            // inicializando cada View do Recycler View
            deMarcaVeiculoTV = itemView.findViewById(R.id.idTVdeMarcaVeiculo);
            deModeloVeiculoTV = itemView.findViewById(R.id.idTVdeModeloVeiculo);
            nuPlacaVeiculoTV = itemView.findViewById(R.id.idTVnuPlacaVeiculo);

            // adicionando evento on click listener para cada item do Recycler View
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    // dentro do click listener passa a posicao do item do Recycler View
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(VeiculoModel model);
    }

    public void setOnClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}


