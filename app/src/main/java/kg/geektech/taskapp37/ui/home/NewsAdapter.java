package kg.geektech.taskapp37.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kg.geektech.taskapp37.databinding.FragmentNewsBinding;
import kg.geektech.taskapp37.databinding.ItemNewsBinding;
import kg.geektech.taskapp37.interfaces.OnItemClickListener;
import kg.geektech.taskapp37.models.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<News> list;
    private OnItemClickListener onItemClickListener;
    private ItemNewsBinding binding;

    public NewsAdapter() {
        list = new ArrayList<>();
        list.add(new News("Muba", System.currentTimeMillis()));
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
            binding.textTitle.setBackgroundColor(Color.GRAY);
        } else {
            binding.textTitle.setBackgroundColor(Color.WHITE);
        }
        holder.bind(list.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(News news) {
        list.add(0, news);
        notifyItemChanged(0);

    }
    public void removeList(int position){
        list.remove(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemCtlickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public News getItem(int pos) {
        return list.get(pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public void remove( int pos){
            list.remove(pos);
            notifyItemRemoved(pos);
        }

        public ViewHolder(@NonNull ItemNewsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            itemView.getRoot().setOnClickListener(view -> {
                onItemClickListener.onClick(getAdapterPosition());
            });
            binding.getRoot().setOnLongClickListener(view -> {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(binding.getRoot().getContext());
                String yes = "Yes";
                String cancel = "Cancel";
                alertDialog.setMessage("Do you want to delete?");
                alertDialog.setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        list.remove(getAdapterPosition());
                        notifyItemRemoved(i);

                    }

                });
                alertDialog.setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
                return true;
            });
        }

        public void bind(News news, OnItemClickListener onItemClickListener ) {
            binding.textTitle.setText(news.getTitle());

        }
    }

}
