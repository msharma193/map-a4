package com.library.bookcatalogrecommendationapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.library.bookcatalogrecommendationapp.adapters.BookAdapter;
import com.library.bookcatalogrecommendationapp.models.Book;
import com.library.bookcatalogrecommendationapp.services.ApiService;
import com.library.bookcatalogrecommendationapp.utils.BookResponse;
import com.library.bookcatalogrecommendationapp.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements BookAdapter.OnItemClickListener {

    private RecyclerView recyclerViewBooks;
    private BookAdapter bookAdapter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewBooks = rootView.findViewById(R.id.recyclerViewBooks);
        recyclerViewBooks.setLayoutManager(new LinearLayoutManager(getContext()));


        fetchBooks();

        if (bookAdapter != null) {
            bookAdapter.setOnItemClickListener((BookAdapter.OnItemClickListener) this);
        }

        if (getActivity() != null && ((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            requireActivity().setTitle("Book List");
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }


        return rootView;
    }

    private void fetchBooks() {
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<BookResponse> call = apiService.getBookResponse();

        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if (response.isSuccessful()) {
                    BookResponse bookResponse = response.body();
                    if (bookResponse != null) {
                        List<Book> books = bookResponse.getBooks();

                        bookAdapter = new BookAdapter(books);
                        recyclerViewBooks.setAdapter(bookAdapter);

                        // Set click listener here after initializing the adapter
                        bookAdapter.setOnItemClickListener(HomeFragment.this);
                    }
                } else {
                    // Handle error
                }
            }


            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                showToast("Network Error: " + t.getMessage());
                Log.d("Network Error:", t.getMessage());
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Book book) {
        FragmentManager fragmentManager = getParentFragmentManager();

        // Remove the previous fragment if it exists
        Fragment previousFragment = fragmentManager.findFragmentById(R.id.home_nav);
        if (previousFragment != null) {
            fragmentManager.beginTransaction().remove(previousFragment).commit();
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BookDetailsFragment bookDetailsFragment = BookDetailsFragment.newInstance(book);
        fragmentTransaction.replace(R.id.nav_host_fragment, bookDetailsFragment, "book_details_fragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
