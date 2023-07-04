package com.bidyarthibymmmcschool.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bidyarthibymmmcschool.Modal.Class;
import com.bidyarthibymmmcschool.Ui.SubjectActivity;
import com.bidyarthibymmmcschool.R;

import java.util.ArrayList;
import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassViewHolder>{

    private Context mtx;
    private List<Class> List;
    private List<Class> listfull;


    public ClassAdapter(Context mtx, List<Class> List) {
        this.mtx = mtx;
        this.List = List;
        listfull = new ArrayList<>(List);
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mtx);
        View view = inflater.inflate(R.layout.layout_products, null);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        final Class product = List.get(position);

        holder.textViewTitle.setText(product.getTitle());
        holder.imageView.setImageDrawable(mtx.getResources().getDrawable(product.getImage()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), SubjectActivity.class);
                i.putExtra("title", List.get(position).getTitle());
                mtx.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void filterList(ArrayList<Class> filteredList) {
        List = filteredList;
        notifyDataSetChanged();
    }
}


class ClassViewHolder extends RecyclerView.ViewHolder {


    CardView cardView;
    ImageView imageView;
    TextView textViewTitle;


    public ClassViewHolder(@NonNull View itemView) {
        super(itemView);


        cardView = itemView.findViewById(R.id.carview);
        imageView = itemView.findViewById(R.id.imageview);
        textViewTitle = itemView.findViewById(R.id.textViewTitle);


    }
}