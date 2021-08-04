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
import com.android.wnf.model.SubMateri;
import com.android.wnf.model.SubMateris;

import java.util.List;

public class SubMateriAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<SubMateris> subMateriList;
    private Context context;
    private MateriClickListener listener;
    public SubMateriAdapter(Context context , List<SubMateris> subMateriList){
        this.context = context;
        this.subMateriList = subMateriList;
    }

    public void setMateriClickListener(MateriClickListener listener){
        this.listener = listener;
    }

    class SubMateriHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout parentContainer;
        private AppCompatTextView materiText;
        SubMateriHolder(View itemView){
            super(itemView);
            parentContainer = itemView.findViewById(R.id.parentContainer);
            materiText = itemView.findViewById(R.id.materiText);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new SubMateriHolder(inflater.inflate(R.layout.item_sub_materi , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SubMateriHolder holders = (SubMateriHolder) holder;
        SubMateris materi = subMateriList.get(position);
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
        if(subMateriList != null)
            return subMateriList.size();
        else
            return 0;
    }
    public interface MateriClickListener {
        void onClick(int position);
    }
}