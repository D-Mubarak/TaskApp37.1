package kg.geektech.taskapp37.ui.board;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kg.geektech.taskapp37.Prefs;
import kg.geektech.taskapp37.R;
import kg.geektech.taskapp37.databinding.FragmentBoardBinding;
import kg.geektech.taskapp37.interfaces.OnStartClickListener;

public class BoardFragment extends Fragment {

    private FragmentBoardBinding binding;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BoardAdapter adapter = new BoardAdapter();
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 2)
                    binding.skip.setVisibility(View.GONE);
                else
                    binding.skip.setVisibility(View.VISIBLE);
            }
        });

        adapter.setClickListener(new OnStartClickListener() {
            @Override
            public void onClick() {
                close();
            }
        });
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
        binding.dotsInd.setViewPager2(binding.viewPager);
        skipListener(view);

    }


    private void skipListener(View view) {
        binding.skip.setOnClickListener(view1 -> {
            close();
        });
    }

    private void close() {
        Prefs prefs = new Prefs(requireActivity());
        prefs.saveBoardState();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();

    }
}