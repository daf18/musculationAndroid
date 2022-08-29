package com.example.musculaction.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musculaction.R;
import com.example.musculaction.model.Exercice;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private OnExerciceClickListener onExerciceClickListener;
    private List<Exercice>  exerciceList;
    private Context context;

    public RecyclerViewAdapter(List<Exercice> exerciceList, Context context, OnExerciceClickListener onExerciceClickListener) {
        this.exerciceList = exerciceList;
        this.context = context;
        this.onExerciceClickListener =onExerciceClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercice_row, parent,false);

        return new ViewHolder(view, onExerciceClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exercice exercice = exerciceList.get(position);

        holder.img.setImageResource(exercice.getImg());
        holder.title.setText(exercice.getTitle());
        holder.description.setText(exercice.getDescription());
        //FIXME set onclick listener for btn update and delete

    }

    @Override
    public int getItemCount() {
        return exerciceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        OnExerciceClickListener onExerciceClickListener;
        public ImageView img;
        public TextView title,description;
        public ImageButton update,delete;

        public ViewHolder(@NonNull View itemView, OnExerciceClickListener onExerciceClickListener) {
            super(itemView);
            img = itemView.findViewById(R.id.exercice_img);
            title = itemView.findViewById(R.id.exercice_title_tv);
            description = itemView.findViewById(R.id.exercice_desc);
            update = itemView.findViewById(R.id.update_button);
            delete = itemView.findViewById(R.id.delete_button);

            this.onExerciceClickListener = onExerciceClickListener;

            //each item view created is atteched to onClickListener
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onExerciceClickListener.onExerciceClick(getAdapterPosition());
        }
    }

    public interface OnExerciceClickListener {
        void onExerciceClick(int position);
    }
}
