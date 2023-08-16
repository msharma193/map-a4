package com.library.bookcatalogrecommendationapp.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.library.bookcatalogrecommendationapp.R;
import com.library.bookcatalogrecommendationapp.models.SavedBook;
import java.util.List;

public class SavedBooksAdapter extends RecyclerView.Adapter<SavedBooksAdapter.ViewHolder> {

    private List<SavedBook> savedBooks;

    public SavedBooksAdapter(List<SavedBook> savedBooks) {
        this.savedBooks = savedBooks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SavedBook savedBook = savedBooks.get(position);
        holder.bind(savedBook);
    }

    @Override
    public int getItemCount() {
        return savedBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView authorTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
        }

        public void bind(SavedBook savedBook) {
            titleTextView.setText(savedBook.getTitle());
            authorTextView.setText(savedBook.getAuthors());
        }
    }
}
