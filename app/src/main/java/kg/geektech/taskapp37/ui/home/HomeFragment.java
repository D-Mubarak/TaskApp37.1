package kg.geektech.taskapp37.ui.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import kg.geektech.taskapp37.R;
import kg.geektech.taskapp37.databinding.FragmentHomeBinding;
import kg.geektech.taskapp37.interfaces.OnItemClickListener;
import kg.geektech.taskapp37.models.News;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NewsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NewsAdapter();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                News news = adapter.getItem(pos);
                Toast.makeText(requireContext(), news.getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int pos) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setMessage("What you want to do?");
                alertDialog.setPositiveButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.removeList(pos);
                    }
                });
            }


        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.fab.setOnClickListener(view -> {
            openFragment();
        });
        getParentFragmentManager().setFragmentResultListener("rk_news", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                News news = (News) result.getSerializable("news");
                Log.e("Home", "text= " + news.getTitle());
                adapter.addItem(news);
            }
        });
        return root;
    }

    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.newsFragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList();
    }

    private void initList() {
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}