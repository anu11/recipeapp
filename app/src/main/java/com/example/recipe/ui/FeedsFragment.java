package com.example.recipe.ui;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recipe.data.CategoryDataStore;
import com.example.recipe.MainActivityListener;
import com.example.recipe.OnItemClickListener;
import com.example.recipe.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedsFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter MyAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FragmentManager fragmentManager;
    List<CategoryDataStore.CategoryDataItem> cupCakes;
    OnItemClickListener onItemClickListener;
    RecipeDetailFragment recipeFragment;

    public FeedsFragment() {}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MainActivityListener) {
            cupCakes = activityListener.getData();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feed, container,
                false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        final CategoryAdapter mAdapter = new CategoryAdapter(getActivity(),cupCakes, new AdapterClickResolver());
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

}