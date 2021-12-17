package kg.geektech.taskapp37;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kg.geektech.taskapp37.databinding.ItemBoardBinding;
import kg.geektech.taskapp37.interfaces.OnStartClickListener;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private ItemBoardBinding binding;
    private String[] titles = new String[]{"Java", "Python", "Kotlin"};
    private String java = "Java - Строго типизированный объектно-ориентированный язык программирования общего назначения, разработанный компанией Sun Microsystems";
    private String python = "Язык является полностью объектно-ориентированным - всё является объектами. Необычной особенностью языка является выделение блоков кода пробельными отступами";
    private String kotlin = "Статически типизированный, объектно-ориентированный язык программирования, работающий поверх Java Virtual Machine и разрабатываемый компанией JetBrains";
    private String[] info = new String[]{java, python, kotlin};
    //private Uri[] uris = new Uri[]{};
    private int[] images = new int[]{R.drawable.java,R.drawable.python, R.drawable.kotlin };
    private OnStartClickListener clickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public void setClickListener(OnStartClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull ItemBoardBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick();
                }
            });
        }

        public void bind(int position) {
            binding.textTitle.setText(titles[position]);
            binding.imageView.setImageResource(images[position]);
            binding.textDesc.setText(info[position]);
            if (position == titles.length - 1 && position == images.length - 1) {
                binding.btnStart.setVisibility(View.VISIBLE);
            } else {
                binding.btnStart.setVisibility(View.INVISIBLE);
            }
        }
    }

}
