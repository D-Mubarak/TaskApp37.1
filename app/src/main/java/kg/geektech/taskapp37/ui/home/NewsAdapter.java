    package kg.geektech.taskapp37.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import kg.geektech.taskapp37.R;
import kg.geektech.taskapp37.databinding.FragmentNewsBinding;
import kg.geektech.taskapp37.databinding.ItemNewsBinding;
import kg.geektech.taskapp37.interfaces.OnItemClickListener;
import kg.geektech.taskapp37.models.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<News> list;
    private OnItemClickListener onItemClickListener;

    public NewsAdapter() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.bind(list.get(position));
        holder.bind(list.get(position), onItemClickListener);
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.DKGRAY);
        }
        holder.bind(list.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(News news) {
        list.add(0, news);
        notifyItemInserted(0);

    }

    public void updateItem(News news) {
        int index = list.indexOf(news);
        list.set(index, news);
        notifyItemChanged(index);
    }

    public News getItem(int pos) {
        return list.get(pos);
    }

    public void addItems(List<News> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public void remove(News news, int pos) {
        this.list.remove(news);
        notifyItemRemoved(pos);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void editing(News news, int position) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemNewsBinding binding;

        public ViewHolder(@NonNull ItemNewsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            itemView.getRoot().setOnClickListener(view -> {
                onItemClickListener.onClick(getAdapterPosition());
            });
            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onLongClick(getAdapterPosition());
                    return true;
                }
            });
        }

        public void bind(News news, OnItemClickListener onItemClickListener) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM.dd"+" - " +"HH:mm:ss");
            String a = sdf.format(news.getCreatedAt());
            binding.textTitle.setText(news.getTitle());
            binding.time.setText(a);
        }
    }

}
