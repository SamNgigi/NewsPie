package com.hai.jedi.newspie.View.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hai.jedi.newspie.R;
import com.hai.jedi.newspie.View.Adapters.FirebaseSourceAdapter;
import com.hai.jedi.newspie.ViewModel.FirebaseViewModel;
import com.hai.jedi.newspie.ViewModel.SharedViewModel;


import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FBSourcesFragment extends Fragment {

    private static final String TAG = FBSourcesFragment.class.getSimpleName().toUpperCase();

    private SharedViewModel sharedViewModel;
    private FirebaseViewModel firebaseViewModel;

    @BindView(R.id.firebaseRecyclerView)
    RecyclerView fbRecyclerView;


    public FBSourcesFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        /* *
         *
         * We had to use the Fragment class of androidx.fragment and not android.*/
        sharedViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity()))
                                            .get(SharedViewModel.class);
        firebaseViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity()))
                                                .get(FirebaseViewModel.class);


        firebaseViewModel.getSources().observe(
                getViewLifecycleOwner(), fbSources -> {
                    fbRecyclerView.setAdapter(new FirebaseSourceAdapter(getActivity(), fbSources));
                }
        );
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.firebase_fragment, container, false);
        ButterKnife.bind(this, view);
        fbRecyclerView.setHasFixedSize(true);
        fbRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                                        LinearLayoutManager.HORIZONTAL,
                                        false));
        return view;
    }

}
