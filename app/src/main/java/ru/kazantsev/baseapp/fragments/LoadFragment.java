package ru.kazantsev.baseapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ru.kazantsev.baseapp.R;

/**
 * Created by 0shad on 13.07.2015.
 */
public class LoadFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.progressbar, container, false);
        return rootView;
    }
}
