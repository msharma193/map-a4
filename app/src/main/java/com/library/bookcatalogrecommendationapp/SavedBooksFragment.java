package com.library.bookcatalogrecommendationapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.library.bookcatalogrecommendationapp.adapters.SavedBooksAdapter;
import com.library.bookcatalogrecommendationapp.models.SavedBook;
import com.library.bookcatalogrecommendationapp.utils.AppDatabase;
import com.library.bookcatalogrecommendationapp.utils.SavedBookDao;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SavedBooksFragment extends Fragment {

    public SavedBooksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_saved_books, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewSavedBooks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);

        // Obtain an instance of the AppDatabase and SavedBookDao
        AppDatabase appDatabase = Room.databaseBuilder(requireContext(), AppDatabase.class, "books").build();
        SavedBookDao savedBookDao = appDatabase.savedBookDao();

        // Execute the database operation on a background thread
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                List<SavedBook> readSavedBooks = savedBookDao.getReadSavedBooks();

                // Set up the RecyclerView adapter with the list of read saved books
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SavedBooksAdapter adapter = new SavedBooksAdapter(readSavedBooks);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        });

        if (getActivity() != null && ((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            requireActivity().setTitle("Saved Books");
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        return rootView;
    }
}
