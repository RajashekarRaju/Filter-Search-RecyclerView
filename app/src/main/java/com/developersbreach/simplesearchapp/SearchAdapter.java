package com.developersbreach.simplesearchapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private final List<Sports> mSportsList;

    public SearchAdapter(List<Sports> listData) {
        // Data is initialized in MainActivity class
        mSportsList = listData;
    }

    static class SearchViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTitleTextView;

        SearchViewHolder(View view) {
            super(view);
            mTitleTextView = view.findViewById(R.id.search_title_text_view);
        }
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_search, parent, false
        );
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchViewHolder holder, int position) {
        Sports movie = mSportsList.get(position);
        holder.mTitleTextView.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return mSportsList.size();
    }
}
