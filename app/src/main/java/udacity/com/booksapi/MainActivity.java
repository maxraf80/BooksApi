package udacity.com.booksapi;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  {

private static final String GOOGLE_API_URL= "https://www.googleapis.com/books/v1/volumes?q=";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
