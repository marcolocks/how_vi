package com.how_vi.autoescola;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class AlunoRVAdapter extends ListAdapter<AlunoModel, AlunoRVAdapter.ViewHolder> {

    private OnItemClickListener listener;

    AlunoRVAdapter(){
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<AlunoModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<AlunoModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull AlunoModel oldItem, @NonNull AlunoModel newItem) {
            return oldItem.getCoAluno() == newItem.getCoAluno();
        }

        @Override
        public boolean areContentsTheSame(@NonNull AlunoModel oldItem, @NonNull AlunoModel newItem) {
            return oldItem.getNuCPF().equals(newItem.getNuCPF()) &&
                    oldItem.getNoAluno().equals(newItem.getNoAluno()); /*&&
                   oldItem.getDtNascimento().equals(newItem.getDtNascimento());*/
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.aluno_rv_item, parent, false);
        return new ViewHolder(item);
    }
    // TODO: 16/06/2022 Ajustar componente de data
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        AlunoModel model = getAlunoAt(position);
        holder.nuCPFAlunoTV.setText(model.getNuCPF());
        holder.noAlunoTV.setText(model.getNoAluno());
        //holder.dtNascAlunoTV.settext(model.getDtNascimento());
    }

    public AlunoModel getAlunoAt(int position){
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nuCPFAlunoTV;
        TextView noAlunoTV;
        //TextView dtNascAlunoTV;

        ViewHolder(@NonNull View itemView){
            super(itemView);
            // TODO: 16/06/2022 Ajustar componente de data
            nuCPFAlunoTV = itemView.findViewById(R.id.idTVnuCPFAluno);
            noAlunoTV = itemView.findViewById(R.id.idTVnoAluno);
            //dtNascAluno = itemView.findViewById(R.idTVDtNascAluno);

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
        void onItemClick(AlunoModel model);
    }

    public void setOnClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}


