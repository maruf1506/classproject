package com.bidyarthibymmmcschool.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
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

import com.bidyarthibymmmcschool.Modal.Subject;
import com.bidyarthibymmmcschool.Ui.ChapterActivity;
import com.bidyarthibymmmcschool.R;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectViewHolder>{

    private Context mtx;
    private List<Subject> List;

    private List<Subject> listfull;


    public SubjectAdapter(Context mtx, List<Subject> subjects) {
        this.mtx = mtx;
        this.List = subjects;
        listfull = new ArrayList<>(subjects);
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mtx);
        View view = inflater.inflate(R.layout.layout_products, null);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        final Subject product = List.get(position);

        holder.textViewTitle.setText(product.getSubject());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ChapterActivity.class);
                i.putExtra("subject", List.get(position).getSubject());
                i.putExtra("sub_title", ((Activity) mtx).getIntent().getStringExtra("title").toLowerCase());
                mtx.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void filterList(ArrayList<Subject> filteredList) {
        List = filteredList;
        notifyDataSetChanged();
    }

}


class SubjectViewHolder extends RecyclerView.ViewHolder {


    CardView cardView;
    ImageView imageView;
    TextView textViewTitle;


    public SubjectViewHolder(@NonNull View itemView) {
        super(itemView);


        cardView = itemView.findViewById(R.id.carview);
        imageView = itemView.findViewById(R.id.imageview);
        textViewTitle = itemView.findViewById(R.id.textViewTitle);


    }
}