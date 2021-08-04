package com.android.wnf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.wnf.R;
import com.android.wnf.model.Materi;
import com.android.wnf.model.Materis;

import java.util.List;

public class MateriAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Materis> materiList;
    private Context context;
    private MateriClickListener listener;
    public MateriAdapter(Context context , List<Materis> materiList){
        this.context = context;
        this.materiList = materiList;
    }

    public void setMateriClickListener(MateriClickListener listener){
        this.listener = listener;
    }

    class MateriHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout parentContainer;
        private AppCompatTextView materiText;
        MateriHolder(View itemView){
            super(itemView);
            parentContainer = itemView.findViewById(R.id.parentContainer);
            materiText = itemView.findViewById(R.id.materiText);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MateriHolder(inflater.inflate(R.layout.item_materi , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MateriHolder holders = (MateriHolder) holder;
        Materis materi = materiList.get(position);
        holders.materiText.setText(materi.getTitle());
        holders.parentContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null)
                    listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(materiList != null)
            return materiList.size();
        else
            return 0;
    }
    public interface MateriClickListener {
        void onClick(int position);
    }
}
