package com.how_vi.autoescola;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class InstrutorRVAdapter extends ListAdapter<InstrutorModel, InstrutorRVAdapter.ViewHolder> {

    private OnItemClickListener listener;

    InstrutorRVAdapter(){
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<InstrutorModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<InstrutorModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull InstrutorModel oldItem, @NonNull InstrutorModel newItem) {
            return oldItem.getCoInstrutor() == newItem.getCoInstrutor();
        }

        @Override
        public boolean areContentsTheSame(@NonNull InstrutorModel oldItem, @NonNull InstrutorModel newItem) {
            return oldItem.getNuCPF().equals(newItem.getNuCPF()) &&
                   oldItem.getNoInstrutor().equals(newItem.getNoInstrutor()); /*&&
                   oldItem.getDtNascimento().equals(newItem.getDtNascimento());*/
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.instrutor_rv_item, parent, false);
        return new ViewHolder(item);
    }
    // TODO: 16/06/2022 Ajustar componente de data
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        InstrutorModel model = getInstrutorAt(position);
        holder.nuCPFInstrutorTV.setText(model.getNuCPF());
        holder.noInstrutorTV.setText(model.getNoInstrutor());
        //holder.dtNascInstrutorTV.settext(model.getDtNascimento());
    }

    public InstrutorModel getInstrutorAt(int position){
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nuCPFInstrutorTV;
        TextView noInstrutorTV;
        //TextView dtNascInstrutorTV;

        ViewHolder(@NonNull View itemView){
            super(itemView);
            // TODO: 16/06/2022 Ajustar componente de data
            nuCPFInstrutorTV = itemView.findViewById(R.id.idTVNuCPFInstrutor);
            noInstrutorTV = itemView.findViewById(R.id.idTVnoInstrutor);
            //dtNascInstrutor = itemView.findViewById(R.idTVDtNascInstrutor);

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
        void onItemClick(InstrutorModel model);
    }

    public void setOnClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}


