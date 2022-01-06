package com.developersbreach.simplesearchapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AppCompatEditText mEditText;
    private TextView mNoSearchResultsFoundText;
    private ImageView mClearQueryImageView;
    private ImageView mVoiceSearchImageView;
    private List<Sports> mSportsList;
    private static final int SPEECH_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.search_list);
        mEditText = findViewById(R.id.search_edit_text);
        mNoSearchResultsFoundText = findViewById(R.id.no_search_results_found_text);
        mClearQueryImageView = findViewById(R.id.clear_search_query);
        mVoiceSearchImageView = findViewById(R.id.voice_search_query);

        // Assign data to variable
        mSportsList = new SportsData().listData();
        attachAdapter(mSportsList);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                String query = text.toString().toLowerCase();
                filterWithQuery(query);
                toggleImageView(query);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing
            }
        });

        mVoiceSearchImageView.setOnClickListener(v -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            startActivityForResult(intent, SPEECH_REQUEST_CODE);
        });

        mClearQueryImageView.setOnClickListener(v ->
                mEditText.setText("")
        );
    }

    private void attachAdapter(List<Sports> list) {
        SearchAdapter adapter = new SearchAdapter(list);
        mRecyclerView.setAdapter(adapter);
    }

    private void filterWithQuery(String query) {
        if (!query.isEmpty()) {
            List<Sports> filteredList = onFilterChanged(query);
            attachAdapter(filteredList);
            toggleRecyclerView(filteredList);
        } else {
            attachAdapter(mSportsList);
        }
    }

    private List<Sports> onFilterChanged(String filterQuery) {
        List<Sports> filteredList = new ArrayList<>();
        for (Sports currentSport : mSportsList) {
            if (currentSport.getTitle().toLowerCase(Locale.getDefault()).contains(filterQuery)) {
                filteredList.add(currentSport);
            }
        }
        return filteredList;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            ArrayList<String> results = Objects.requireNonNull(data).getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            // Do something with spokenText
            mEditText.setText(spokenText);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void toggleRecyclerView(List<Sports> sportsList) {
        if (sportsList.isEmpty()) {
            mRecyclerView.setVisibility(View.INVISIBLE);
            mNoSearchResultsFoundText.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mNoSearchResultsFoundText.setVisibility(View.INVISIBLE);
        }
    }

    private void toggleImageView(String query) {
        if (!query.isEmpty()) {
            mClearQueryImageView.setVisibility(View.VISIBLE);
            mVoiceSearchImageView.setVisibility(View.INVISIBLE);
        } else {
            mClearQueryImageView.setVisibility(View.INVISIBLE);
            mVoiceSearchImageView.setVisibility(View.VISIBLE);
        }
    }
}
