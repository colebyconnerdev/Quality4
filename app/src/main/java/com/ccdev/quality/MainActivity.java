package com.ccdev.quality;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ccdev.quality.Utils.Prefs;

public class MainActivity extends AppCompatActivity implements BackHandledFragment.BackHandlerInterface{

    FragmentManager mFragmentManager;
    Fragment mLoginFragment, mSettingsFragment;
    BackHandledFragment mSelectedFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.setProperty("jcifs.smb.client.responseTimeout", "5000");

        mLoginFragment = new LoginFragment();
        mSettingsFragment = new SettingsFragment();

        mFragmentManager = getSupportFragmentManager();

        determineLandingPage();
    }

    @Override
    public void onBackPressed() {
        if (mSelectedFragment == null || mSelectedFragment.onBackPressed()) {
            //super.onBackPressed();
        }
    }

    @Override
    public void setSelectedFragment(BackHandledFragment backHandledFragment) {
        mSelectedFragment = backHandledFragment;
    }

    private void determineLandingPage() {

        if (Prefs.getPrefs(this) != Prefs.SETTINGS_OK) {
            mFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content, mSettingsFragment)
                    .commit();
            return;
        }

        if (Prefs.getRememberMe()) {
            // TODO getInitialFileTree() -> Files
        } else {
            mFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content, mLoginFragment)
                    .commit();
            return;
        }

        mFragmentManager
                .beginTransaction()
                .replace(R.id.main_content, mSettingsFragment)
                .commit();
    }
}
