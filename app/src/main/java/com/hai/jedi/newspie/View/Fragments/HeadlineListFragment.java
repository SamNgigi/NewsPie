package com.hai.jedi.newspie.View.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hai.jedi.newspie.R;
import com.hai.jedi.newspie.ViewModel.HeadlineViewModel;
import com.hai.jedi.newspie.ViewModel.SharedViewModel;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeadlineListFragment extends Fragment {

    private final String TAG = HeadlineListFragment.class.getSimpleName().toUpperCase();

    private HeadlineViewModel headlineViewModel;
    private SharedViewModel sharedViewModel;


    public HeadlineListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        sharedViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity()))
                                            .get(SharedViewModel.class);
        headlineViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity()))
                                              .get(HeadlineViewModel.class);

        // Getting the source_id from our source adapter to our HeadlineListFragment.
        sharedViewModel.getSelected_source().observe(
                getViewLifecycleOwner(), selected_sourceId -> {
                    Log.d(TAG, selected_sourceId);
                }
        );

        headlineViewModel.sourceHeadlines().observe(
                getViewLifecycleOwner(), headlineWrapper -> {
                    Log.d(TAG, headlineWrapper.getArticles().toString());
                }
        );

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_headline_list, container, false);
    }

}
