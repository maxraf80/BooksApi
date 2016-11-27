package udacity.com.booksapi;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Book> {

private static final String LOG_TAG=  MainActivity.class.getName();

    private static final String GOOGLE_API_URL= "https://www.googleapis.com/books/v1/volumes?q=";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public Loader<Book> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Book> loader, Book data) {

    }

    @Override
    public void onLoaderReset(Loader<Book> loader) {

    }
}
