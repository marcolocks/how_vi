package com.how_vi.autoescola;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class VeiculoRVAdapter extends ListAdapter<VeiculoModel, VeiculoRVAdapter.ViewHolder> {

    private OnItemClickListener listener;

    VeiculoRVAdapter(){
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<VeiculoModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<VeiculoModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull VeiculoModel oldItem, @NonNull VeiculoModel newItem) {
            return oldItem.getCoVeiculo() == newItem.getCoVeiculo();
        }

        @Override
        public boolean areContentsTheSame(@NonNull VeiculoModel oldItem, @NonNull VeiculoModel newItem) {
            return oldItem.getDeMarca().equals(newItem.getDeMarca()) &&
                   oldItem.getDeModelo().equals(newItem.getDeModelo()) &&
                   oldItem.getNuPlaca().equals(newItem.getNuPlaca());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.veiculo_rv_item, parent, false);
        return new ViewHolder(item);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        VeiculoModel model = getVeiculoAt(position);
        holder.deMarcaVeiculoTV.setText(model.getDeMarca());
        holder.deModeloVeiculoTV.setText(model.getDeModelo());
        holder.nuPlacaVeiculoTV.setText(model.getNuPlaca());
    }

    public VeiculoModel getVeiculoAt(int position){
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView deMarcaVeiculoTV;
        TextView deModeloVeiculoTV;
        TextView nuPlacaVeiculoTV;

        ViewHolder(@NonNull View itemView){
            super(itemView);
            deMarcaVeiculoTV = itemView.findViewById(R.id.idTVdeMarcaVeiculo);
            deModeloVeiculoTV = itemView.findViewById(R.id.idTVdeModeloVeiculo);
            nuPlacaVeiculoTV = itemView.findViewById(R.id.idTVnuPlacaVeiculo);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
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


