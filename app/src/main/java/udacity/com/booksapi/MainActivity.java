package udacity.com.booksapi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static udacity.com.booksapi.QueryUtils.LOG_TAG;


public class MainActivity extends AppCompatActivity {
    boolean conection;
    private String api = "https://www.googleapis.com/books/v1/volumes?q=";
    TextView topic;
    Button checkButton;
    String find;
    private BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.empty_list_view));
        adapter = new BookAdapter(this, new ArrayList<Book>());
        listView.setAdapter(adapter);


        topic = (TextView) findViewById(R.id.editText);
        checkButton = (Button) findViewById(R.id.search_button);
        checkButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    conection = true;
                } else {
                    conection = false;
                }
                if (conection == true) {
                    find = topic.getText().toString();
                    String toSearch = "";
                    if (find.length() > 0) {
                        find = find.replace(" ", "+");
                        toSearch = api + find;
                    }
                    BookAsycTask task = new BookAsycTask();
                    task.execute(toSearch);
                } else {
                    Toast.makeText(getApplicationContext(), "No internet connection found¡¡¡¡", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private class BookAsycTask extends AsyncTask<String, Void, ArrayList<Book>> {
        private ArrayList<Book> result;

        protected ArrayList<Book> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                Log.e(LOG_TAG, "YYYY");
                return null;

            }

            result = QueryUtils.fetchBookData(urls[0]);
            Log.e(LOG_TAG, "YYYY");
            return result;
        }


        protected void onPostExecute(ArrayList<Book> result) {

            if (result == null) {
                Log.e(LOG_TAG, "XXXXX");

                return;
            }
            adapter.addAll(result);
            Log.e(LOG_TAG, "XXXXX");
        }
    }
}