package com.ccdev.quality;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Coleby.Conner on 7/18/2016.
 */

public class DialogFragment extends BackHandledFragment implements View.OnClickListener {

    public static final String TAG = "DialogFragment";

    public static final int REQUEST_INPUT = 0;
    public static final int REQUEST_NO_INPUT = 1;

    public static final int RESULT_OK = 0;
    public static final int RESULT_CANCEL = 1;
    public static final int RESULT_ERROR = -1;

    public static final String TITLE_TEXT = "title_text";
    public static final String PROMPT_TEXT = "prompt_text";
    public static final String CONFIRM_TEXT = "confirm_text";
    public static final String CANCEL_TEXT = "cancel_text";
    public static final String DATA_INPUT = "data_input";

    private int mRequestCode;
    private EditText mInputText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeView();
    }

    @Override
    public boolean onBackPressed() {
        dismiss();
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_confirmButton:
                confirm();
                break;
            case R.id.dialog_cancelButton:
                cancel();
                break;
        }
    }

    private void initializeView() {

        Bundle args = getArguments();

        if (args.isEmpty()) {
            dismiss();
            return;
        }

        mRequestCode = getTargetRequestCode();

        if (args.containsKey(TITLE_TEXT)) {
            TextView titleText = (TextView) getView().findViewById(R.id.dialog_titleText);
            View titleContainer = (View) getView().findViewById(R.id.dialog_titleContainer);

            titleText.setText(args.getString(TITLE_TEXT));
            titleContainer.setVisibility(View.VISIBLE);
        }

        if (args.containsKey(PROMPT_TEXT)) {
            TextView promptText = (TextView) getView().findViewById(R.id.dialog_promptText);

            promptText.setText(args.getString(PROMPT_TEXT));
            promptText.setVisibility(View.VISIBLE);
        }

        if (mRequestCode == REQUEST_INPUT) {
            mInputText = (EditText) getView().findViewById(R.id.dialog_inputText);

            mInputText.setVisibility(View.VISIBLE);
        }

        if (args.containsKey(CONFIRM_TEXT)) {
            Button confirmButton = (Button) getView().findViewById(R.id.dialog_confirmButton);
            View buttonsContainer = getView().findViewById(R.id.dialog_buttonsContainer);

            confirmButton.setText(args.getString(CONFIRM_TEXT));
            confirmButton.setVisibility(View.VISIBLE);
            confirmButton.setOnClickListener(this);
            buttonsContainer.setVisibility(View.VISIBLE);
        }

        if (args.containsKey(CANCEL_TEXT)) {
            Button cancelButton = (Button) getView().findViewById(R.id.dialog_cancelButton);
            View buttonsContainer = getView().findViewById(R.id.dialog_buttonsContainer);

            cancelButton.setText(args.getString(CANCEL_TEXT));
            cancelButton.setVisibility(View.VISIBLE);
            cancelButton.setOnClickListener(this);
            buttonsContainer.setVisibility(View.VISIBLE);
        }
    }

    public void cancel() {
        Fragment targetFragment = getTargetFragment();
        if (targetFragment != null) {
            targetFragment.onActivityResult(mRequestCode, RESULT_CANCEL, null);
        }
        dismiss();
    }

    public void confirm() {
        Fragment targetFragment = getTargetFragment();
        if (targetFragment != null && mRequestCode == REQUEST_INPUT) {
            if (mInputText != null) {
                String input = mInputText.getText().toString();
                Intent i = new Intent();
                i.putExtra(DATA_INPUT, input);
                targetFragment.onActivityResult(mRequestCode, RESULT_OK, i);
            } else {
                targetFragment.onActivityResult(mRequestCode, RESULT_ERROR, null);
            }
        }
        dismiss();
    }

    public void show() {
        getFragmentManager()
                .beginTransaction()
                .add(R.id.main_content, this)
                .addToBackStack(TAG)
                .commit();
    }

    public void dismiss() {
        getFragmentManager().popBackStack(TAG,
                FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
