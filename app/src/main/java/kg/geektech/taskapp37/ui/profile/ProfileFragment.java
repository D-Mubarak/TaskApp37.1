package kg.geektech.taskapp37.ui.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import kg.geektech.taskapp37.Prefs;
import kg.geektech.taskapp37.R;
import kg.geektech.taskapp37.databinding.FragmentNewsBinding;
import kg.geektech.taskapp37.databinding.FragmentProfileBinding;
import kg.geektech.taskapp37.ui.notifications.NotificationsViewModel;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private boolean proverka = false;
    private boolean proverka2 = true;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Prefs prefs = new Prefs(requireContext());
        putPhoto(prefs);
        savingInfo(prefs);


        if (!prefs.getImage().equals("")) {
            Glide.with(binding.photo).load(prefs.getImage()).circleCrop().into(binding.photo);
            proverka = true;
        }
    }

    private void savingInfo(Prefs prefs) {
        binding.usernameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                prefs.saveUserName(s.toString());
            }
        });
        binding.usernameET.setText(prefs.getUsername());
    }


    public void putPhoto(Prefs prefs) {
        ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        Glide.with(binding.photo).load(uri).circleCrop().into(binding.photo);
                        prefs.saveImage(uri);
                        binding.photo.setImageURI(uri);
                        proverka = true;
                    }
                });
        binding.photo.setOnClickListener(view1 -> {
            if (proverka && proverka2) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(requireContext());
                alertDialog.setMessage("Do you want to delete?");
                alertDialog.setNeutralButton("Заменить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mGetContent.launch("image/*");
                    }
                });
                alertDialog.setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        binding.photo.setImageResource(R.drawable.ic_person);
                        proverka2 = false;
                    }
                });
                AlertDialog dialog = alertDialog.create();
                dialog.show();
            } else {
                mGetContent.launch("image/*");
                proverka = true;

            }
        });
    }
}