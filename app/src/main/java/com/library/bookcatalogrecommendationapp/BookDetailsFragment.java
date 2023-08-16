package com.library.bookcatalogrecommendationapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.library.bookcatalogrecommendationapp.models.Book;
import com.library.bookcatalogrecommendationapp.models.SavedBook;
import com.library.bookcatalogrecommendationapp.utils.AppDatabase;
import com.library.bookcatalogrecommendationapp.utils.AppExecutors;
import com.library.bookcatalogrecommendationapp.utils.SavedBookDao;


public class BookDetailsFragment extends Fragment {

    private static final String ARG_SELECTED_BOOK = "selected_book";

    private Book selectedBook;
    Button buttonInterested;
    TextView book_title;
    Button buttonRead;
    Button buttonBack;

    public BookDetailsFragment() {
        // Required empty public constructor
    }

    public static BookDetailsFragment newInstance(Book book) {
        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_SELECTED_BOOK, book);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedBook = getArguments().getParcelable(ARG_SELECTED_BOOK);

            Log.e("onCreate", selectedBook.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_book_details, container, false);


        book_title = rootView.findViewById(R.id.book_title);


        AppDatabase appDatabase = Room.databaseBuilder(getContext(), AppDatabase.class, "books").build();
        SavedBookDao savedBookDao = appDatabase.savedBookDao();

        book_title.setText(selectedBook.getTitle());


        // Inflate the layout for this fragment


        buttonInterested = rootView.findViewById(R.id.button_intrested);
        buttonRead = rootView.findViewById(R.id.button_read);
        buttonBack = rootView.findViewById(R.id.button_back);

        // Set click listener for the "Interested" button
        buttonInterested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavedBook newSavedBook = new SavedBook(0,selectedBook.getTitle(),selectedBook.getAuthors(),selectedBook.getPublishedDate(),selectedBook.getGenre(),
                        selectedBook.getCoverImageUrl(),false);

//                savedBookDao.insertOrReplaceSavedBook(newSavedBook);
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        savedBookDao.insertOrReplaceSavedBook(newSavedBook);
                    }
                });
            }
        });

        // Set click listener for the "Read" button
        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                SavedBook newSavedBook = new SavedBook(0,selectedBook.getTitle(),selectedBook.getAuthors(),selectedBook.getPublishedDate(),selectedBook.getGenre(),
                        selectedBook.getCoverImageUrl(),true);

//                savedBookDao.insertOrReplaceSavedBook(newSavedBook);
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        savedBookDao.insertOrReplaceSavedBook(newSavedBook);
                    }
                });
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToHomeFragment();
            }
        });

        // Check if an action bar is available before setting its properties
        if (getActivity() != null && ((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            requireActivity().setTitle("Book Details");
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        return rootView;
    }
    private void navigateToHomeFragment() {
        FragmentManager fragmentManager = getParentFragmentManager();

        // Create a new instance of BookDetailsFragment
        HomeFragment bookDetailsFragment = HomeFragment.newInstance();

        // Replace the current fragment with the new BookDetailsFragment
        fragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, bookDetailsFragment, "navigateToHomeFragment")
                .addToBackStack(null)  // Add the transaction to the back stack
                .commit();
    }

}
