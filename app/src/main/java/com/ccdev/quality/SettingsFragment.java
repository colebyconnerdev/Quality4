package com.ccdev.quality;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.ccdev.quality.Utils.Prefs;

/**
 * Created by Coleby.Conner on 7/17/2016.
 */

public class SettingsFragment extends BackHandledFragment {

    private EditText mServerText, mRootText, mDomainText;
    private EditText mUsernameText, mPasswordText, mAdminText;
    private Switch mRememberMeSwitch, mUseAdminSwitch;
    private Button mConfirmButton, mCancelButton;

    private OnSettingsListener mCallback;
    public interface OnSettingsListener {
        void onSettingsCanceled();
        void onSettingsConfirmed();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnSettingsListener) this;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnSettingsListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeView();
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    private void initializeView() {

        mServerText = (EditText) getView().findViewById(R.id.settings_server);
        mRootText = (EditText) getView().findViewById(R.id.settings_root);
        mDomainText = (EditText) getView().findViewById(R.id.settings_domain);
        mUsernameText = (EditText) getView().findViewById(R.id.settings_username);
        mPasswordText = (EditText) getView().findViewById(R.id.settings_password);
        mAdminText = (EditText) getView().findViewById(R.id.settings_admin);
        mRememberMeSwitch = (Switch) getView().findViewById(R.id.settings_rememberMe);
        mUseAdminSwitch = (Switch) getView().findViewById(R.id.settings_useAdmin);
        mConfirmButton = (Button) getView().findViewById(R.id.settings_confirm);
        mCancelButton = (Button) getView().findViewById(R.id.settings_cancel);

        mServerText.setText(Prefs.getServer());
        mRootText.setText(Prefs.getRoot());
        mDomainText.setText(Prefs.getDomain());

        if (Prefs.getRememberMe()) {
            mRememberMeSwitch.setChecked(true);
            mUsernameText.setText(Prefs.getUsername());
            mPasswordText.setText(Prefs.getPassword());
        }

        if (Prefs.getUseAdmin()) {
            mUseAdminSwitch.setChecked(true);
            mAdminText.setText(Prefs.getAdmin());
        }


        mRememberMeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                setRememberMe(isChecked);
            }
        });

        mUseAdminSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                setUseAdmin(isChecked);
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });

        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirm();
            }
        });
    }

    private void setRememberMe(boolean isChecked) {
        // TODO warning
        mUsernameText.setEnabled(isChecked);
        mPasswordText.setEnabled(isChecked);
    }

    private void setUseAdmin(boolean isChecked) {
        // TODO warning
        mAdminText.setEnabled(isChecked);
    }

    private void cancel() {
        mCallback.onSettingsCanceled();
    }

    private void confirm() {

        String server;
        if ((server = mServerText.getText().toString()).isEmpty()) {
            Bundle args = new Bundle();
            args.putString(DialogFragment.TITLE_TEXT, "Missing Field");
            args.putString(DialogFragment.PROMPT_TEXT, "Server IP cannot be blank.");
            // TODO left off here
            DialogFragment dialogFragment = new DialogFragment();
            mServerText.requestFocus();
            return;
        }

        String root;
        if ((root = mRootText.getText().toString()).isEmpty()) {
            mRootText.requestFocus();
            // TODO warning
            return;
        }

        String domain;
        if ((domain = mDomainText.getText().toString()).isEmpty()) {
            mDomainText.requestFocus();
            // TODO warning
            return;
        }

        boolean rememberMe;
        String username, password;
        if ((rememberMe = mRememberMeSwitch.isChecked())) {
            if ((username = mUsernameText.getText().toString()).isEmpty()) {
                mUsernameText.requestFocus();
                // TODO warning
                return;
            }
            if ((password = mPasswordText.getText().toString()).isEmpty()) {
                mPasswordText.requestFocus();
                // TODO warning
                return;
            }
        } else {
            username = "";
            password = "";
        }

        boolean useAdmin;
        String admin;
        if ((useAdmin = mUseAdminSwitch.isChecked())) {
            if ((admin = mAdminText.getText().toString()).isEmpty()) {
                mAdminText.requestFocus();
                // TODO warning
                return;
            }
        } else {
            admin = "";
        }

        // TODO getInitialFileTree

        if (false) { // TODO ^^
            Prefs.setServer(server);
            Prefs.setRoot(root);
            Prefs.setDomain(domain);
            Prefs.setRememberMe(rememberMe);
            Prefs.setUsername(username);
            Prefs.setPassword(password);
            Prefs.setUseAdmin(useAdmin);
            Prefs.setAdmin(admin);
            Prefs.commitAll();
        }

        mCallback.onSettingsConfirmed();
    }

    private void showDialog() {

    }
}
