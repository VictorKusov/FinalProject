package ru.list.victor_90.study.moneykeeper.ui.fragments.adapters;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.list.victor_90.study.moneykeeper.R;
import ru.list.victor_90.study.moneykeeper.database.models.Category;
import ru.list.victor_90.study.moneykeeper.ui.fragments.listeners.OnItemClickListener;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder>{

    List<Category> categories;
    private final OnItemClickListener listener;

    public RVAdapter(List<Category> categories, OnItemClickListener listener) {
        this.categories = categories;
        this.listener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(categories.get(position).getCategory());
        holder.imageView.setImageResource(categories.get(position).getIconById());
        holder.bind(categories.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(final View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.category_item_cv);
            imageView = (ImageView) itemView.findViewById(R.id.category_item_img);
            textView = (TextView) itemView.findViewById(R.id.category_item_txt);

        }

        public void bind(final Category category, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(category);
                }
            });
        }
    }
}
