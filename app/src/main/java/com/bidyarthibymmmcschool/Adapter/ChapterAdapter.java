package com.bidyarthibymmmcschool.Adapter;

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

import com.bidyarthibymmmcschool.Modal.Chapter;
import com.bidyarthibymmmcschool.R;
import com.bidyarthibymmmcschool.Ui.pdf;

import java.util.ArrayList;
import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterViewHolder>{

    private Context mtx;
    private List<Chapter> List;

    private List<Chapter> listfull;


    public ChapterAdapter(Context mtx, List<Chapter> subjects) {
        this.mtx = mtx;
        this.List = subjects;
        listfull = new ArrayList<>(subjects);
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mtx);
        View view = inflater.inflate(R.layout.layout_products, null);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        final Chapter product = List.get(position);

        holder.textViewTitle.setText(product.getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), pdf.class);
                i.putExtra("chapter_title",product.getTitle());
                i.putExtra("chapter_link",product.getChapter());
                mtx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void filterList(ArrayList<Chapter> filteredList) {
        List = filteredList;
        notifyDataSetChanged();
    }

}


class ChapterViewHolder extends RecyclerView.ViewHolder {


    CardView cardView;
    ImageView imageView;
    TextView textViewTitle;


    public ChapterViewHolder(@NonNull View itemView) {
        super(itemView);


        cardView = itemView.findViewById(R.id.carview);
        imageView = itemView.findViewById(R.id.imageview);
        textViewTitle = itemView.findViewById(R.id.textViewTitle);


    }
}