package com.hai.jedi.newspie.View.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hai.jedi.newspie.R;
import com.hai.jedi.newspie.ViewModel.FirebaseViewModel;


import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedSourceFragment extends Fragment {

    private static final String TAG = SavedSourceFragment.class.getSimpleName().toUpperCase();

    private FirebaseViewModel firebaseViewModel;


    public SavedSourceFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        /**
         *
         * We had to use the Fragment class of androidx.fragment and not android.*/

        firebaseViewModel = ViewModelProviders.of(this.getActivity()).get(FirebaseViewModel.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.firebase_fragment, container, false);
    }

}
