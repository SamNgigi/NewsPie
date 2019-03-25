package com.hai.jedi.newspie.View.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

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
import com.google.firebase.database.FirebaseDatabase;
import com.hai.jedi.newspie.Constants;
import com.hai.jedi.newspie.Models.Source;
import com.hai.jedi.newspie.R;
import com.hai.jedi.newspie.View.Adapters.SourceListAdapter;
import com.hai.jedi.newspie.ViewModel.FirebaseViewModel;
import com.hai.jedi.newspie.ViewModel.SharedViewModel;
import com.hai.jedi.newspie.ViewModel.SourceViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SourceListFragment extends Fragment {
    // TAG for Debugging
    private final String TAG = SourceListFragment.class.getSimpleName().toUpperCase();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Initializing our Source object
    private Source mSource;

    // Initializing SourceViewModel
    private SourceViewModel sourceViewModel;
    // Initializing SharedViewModel
    private SharedViewModel sharedViewModel;
    // Initializing FirebaseViewModel
    private FirebaseViewModel firebaseViewModel;


    // Our RecyclerView
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    // Firebase manenos
    private DatabaseReference bookMarkedSources;

    // private OnFragmentInteractionListener mListener;

    public SourceListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SourceListFragment.
     *
     * We need this for fragments to communicate with activities or
     * Each other.
     */
    // TODO: Rename and change types and number of parameters
    public static SourceListFragment newInstance(String param1, String param2) {
        SourceListFragment fragment = new SourceListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        /* *
         * Initializing our Firebase
         * */

        bookMarkedSources = FirebaseDatabase.getInstance()
                                     .getReference()
                                     .child(Constants.FIREBASE_SOURCE_BOOKMARKS);

        /* *
         * We initialize our observer here in the onActivityCreated method because we want to make
         * sure that the onCreate method of the underlying activity is already called and therefore
         * any updates from the underlying activity can also be observed.
         *
         * You can find our more about this here https://www.youtube.com/watch?v=ACK67xU1Y3s
         * */

        sharedViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity()))
                                            .get(SharedViewModel.class);
        sourceViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity()))
                                            .get(SourceViewModel.class);

        sourceViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity()))
                                            .get(SourceViewModel.class);
        firebaseViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity()))
                                            .get(FirebaseViewModel.class);


        /* *
         *  Got nullPointer exception when i called the below method because i forgot to initialize
         *  the ViewModel as done above.
         *
         * The call was experiencing a timeout when i was using the telkom wifi.
         * I changed to student wifi and the call was executed just fine.
         *
         * Below we also use "getViewLifecycleOwner" instead of using "this" so that the UI is
         * updated based on the view lifecycle instead of the fragment instance lifecycle. We have to do this
         * */
        // getting the information from the navigation view from the main activity.
        sharedViewModel.getSelected_category().observe(
                getViewLifecycleOwner(), category -> {
                    Log.d(TAG, category);
                    // Loading the data dynamically.
                    sourceViewModel.loadSources4Category(category);
                }
        );

        /*
        * Displaying the source data. This is updated dynamically based on the category chosen as
        * we are calling the loadSources4Category first.
        * */


        firebaseViewModel.getSources().observe(
                getViewLifecycleOwner(), fbSources->{
                    Log.d(TAG, String.valueOf(fbSources.size()));
                    List<String> test = new ArrayList<>();
                    for(Source s: fbSources){
                        test.add(s.getSource_id());
                    }
                    Log.d(TAG, String.valueOf(test));

                    sourceViewModel.sourcesForCategory().observe(
                            getViewLifecycleOwner(), sources -> {
                                mRecyclerView.setAdapter(new SourceListAdapter(getActivity(),sources.getSource_list(), test));
                                /* Log.d(TAG, sources.getSource_list().toString());*/
                            }
                    );
                }
        );



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
        ButterKnife.bind(this, view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false));
        return view;
    }

}
