package com.example.recipe.data;

import android.util.Log;

import com.example.recipe.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajnish on 6/8/15.
 */
public class CategoryDataStore {
    public static final String TAG = "CategoryDataStore";

    public interface CategoryDataStoreListener {
       void onDataFetchComplete(List<Category> list);
    }

    public static void fetchAllCategoryData(final CategoryDataStoreListener listener) {
        ParseQuery<ParseObject> category = ParseQuery.getQuery(Category.class.getSimpleName());
        category.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> results, ParseException e) {
                List<Category> list = new ArrayList<>();
                for (ParseObject object : results) {
                    Category category = Category.getCategory(object);
                    Log.d(TAG, "got result ");
                    list.add(category);
                }
                if (listener != null) {
                    listener.onDataFetchComplete(list);
                }
            }
        });
    }
}
