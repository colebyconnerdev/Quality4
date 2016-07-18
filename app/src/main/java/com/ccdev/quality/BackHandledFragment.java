package com.ccdev.quality;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Coleby.Conner on 7/17/2016.
 */

public abstract class BackHandledFragment extends Fragment {

    public abstract boolean onBackPressed();

    protected BackHandlerInterface backHandlerInterface;
    public interface BackHandlerInterface {
        void setSelectedFragment(BackHandledFragment backHandledFragment);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof BackHandlerInterface)) {
            throw new ClassCastException("Hosting activity must implement BackHandledInterface");
        } else {
            backHandlerInterface = (BackHandlerInterface) getActivity();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        backHandlerInterface.setSelectedFragment(this);
    }
}
