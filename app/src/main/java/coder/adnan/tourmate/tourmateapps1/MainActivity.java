package coder.adnan.tourmate.tourmateapps1;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements
        EventCreateSetupFragment.eventCreateInterface,
        LoginFragment.UserInterfaceAuthListener,
        EventCreateListFragment.OnActiveFloatingAction,RegisterFragment.UserInterfaceRegister {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser fuser = auth.getCurrentUser();
        Fragment fragment = null;
        if (fuser != null) {
            fragment = new EventCreateListFragment();
        } else {
            fragment = new LoginFragment();
        }

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.fragmentLoader, fragment);
        ft.commit();
    }

    @Override
    public void onAuthcomplete() {

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        EventCreateListFragment events = new EventCreateListFragment();
        ft.replace(R.id.fragmentLoader, events);
        ft.commit();
    }

    @Override
    public void onRegisterInterface() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        RegisterFragment registerFragment = new RegisterFragment();
        ft.replace(R.id.fragmentLoader, registerFragment);
        ft.commit();
    }

    @Override
    public void evetnFabAdd() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        EventCreateSetupFragment eventset = new EventCreateSetupFragment();
        ft.replace(R.id.fragmentLoader, eventset);
        ft.commit();
    }

    @Override
    public void Logout() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        LoginFragment login = new LoginFragment();
        ft.replace(R.id.fragmentLoader, login);
        ft.commit();
    }

    @Override
    public void eventinfo() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        EventCreateListFragment eventListBack = new EventCreateListFragment();
        ft.replace(R.id.fragmentLoader, eventListBack);
        ft.commit();
    }

    @Override
    public void onUserRegisterBack() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        LoginFragment login = new LoginFragment();
        ft.replace(R.id.fragmentLoader, login);
        ft.commit();
    }

    @Override
    public void eventLisenter() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        EventCreateListFragment eventListBack = new EventCreateListFragment();
        ft.replace(R.id.fragmentLoader, eventListBack);
        ft.commit();
    }
}





