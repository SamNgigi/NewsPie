package com.hai.jedi.newspie.View.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hai.jedi.newspie.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryListAdapter
        extends RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>{

    private Context mContext;
    private ArrayList<String> mCategories = new ArrayList<>();

    // Our Adapter constructor.
    public CategoryListAdapter(Context context, ArrayList<String> categories){
        mContext = context;
        mCategories = categories;
    }

    // Our ViewHolder class
    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;

        // Our ViewHolder constructor
        public CategoryViewHolder(View itemView){
            super(itemView);
            mContext = itemView.getContext();
        }
    }

    // Required RecyclerView override methods
    @NonNull
    @Override
    public CategoryListAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.category_list_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListAdapter.CategoryViewHolder viewHolder,
                                int position){
        // TODO
    }

    @Override
    public int getItemCount(){
        return mCategories.size();
    }

}
