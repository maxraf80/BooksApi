package udacity.com.booksapi;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.book_list, parent, false);
        }

        Book book = getItem(position);


        ImageView imageView =(ImageView)listItemView.findViewById(R.id.thumbnail);
        Glide.with(getContext()).load(book.getThumbnail()).into(imageView);

        TextView titleTextView=(TextView)listItemView.findViewById(R.id.Title);
        titleTextView.setText(book.getTitle());

        TextView autorTextView = (TextView) listItemView.findViewById((R.id.authors));
        autorTextView.setText(book.getAuthors());

        TextView editorTextView =(TextView)listItemView.findViewById(R.id.publisher);
        editorTextView.setText(book.getPublisher());

        TextView descriptionTextView=(TextView)listItemView.findViewById(R.id.description);
        descriptionTextView.setText(book.getDescription());

        return listItemView;
    }
}
