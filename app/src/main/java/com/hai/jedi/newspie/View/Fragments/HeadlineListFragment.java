package com.hai.jedi.newspie.View.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.hai.jedi.newspie.R;
import com.hai.jedi.newspie.View.Adapters.HeadlineListAdapter;
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

    // Headline recyclerView
    @BindView(R.id.headlineRecycler)
    RecyclerView headlineRecycler;

    // Firebase  Manenos
    private DatabaseReference mFavHeadline;


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
        sharedViewModel.getSelected_sourceId().observe(
                getViewLifecycleOwner(), selected_source -> {
                    Log.d(TAG, selected_source);
                    headlineViewModel.loadHeadlines4Source(selected_source);
                }
        );

        headlineViewModel.sourceHeadlines().observe(
                getViewLifecycleOwner(), headlines -> {
                    Log.d(TAG, headlines.getArticles().toString());
                    headlineRecycler.setAdapter(
                            new HeadlineListAdapter(getActivity(), headlines.getArticles()));
                }
        );

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_headline_list, container, false);
        ButterKnife.bind(this, view);
        headlineRecycler.setHasFixedSize(true);
        headlineRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

}
