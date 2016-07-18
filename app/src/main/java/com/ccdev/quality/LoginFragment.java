package com.ccdev.quality;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by Coleby.Conner on 7/17/2016.
 */

public class LoginFragment extends BackHandledFragment {

    private EditText mUsernameText, mPasswordText;
    private CheckBox mRememberMeCheck;
    private Button mConfirmButton, mSettingsButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mUsernameText = (EditText) view.findViewById(R.id.login_username);
        mPasswordText = (EditText) view.findViewById(R.id.login_password);
        mRememberMeCheck = (CheckBox) view.findViewById(R.id.login_rememberMe);
        mSettingsButton = (Button) view.findViewById(R.id.login_settings);
        mConfirmButton = (Button) view.findViewById(R.id.login_confirm);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
