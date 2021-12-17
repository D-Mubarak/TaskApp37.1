package kg.geektech.taskapp37.ui.profile;

import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private int imageOfProfile;

    public ProfileViewModel(int imageOfProfile) {
        this.imageOfProfile = imageOfProfile;
    }

    public int getImageOfProfile() {
        return imageOfProfile;
    }

    public void setImageOfProfile(int imageOfProfile) {
        this.imageOfProfile = imageOfProfile;
    }
}
