package udacity.com.booksapi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

private String api= "https://www.googleapis.com/books/v1/volumes?q=";
TextView topic;
Button checkButton;
String find;
String newText;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);
        topic = (TextView) findViewById(R.id.editText);

checkButton= (Button) findViewById(R.id.search_button);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                find = topic.getText().toString();}});

        if(find.length()>0)
        {find = newText.replace(" ", "+");
           api = api + newText;}
        BookAsycTask task = new BookAsycTask();

        task.execute(api.toString()); }



    private class BookAsycTask extends AsyncTask<String,Void,Book>{
protected Book doInBackground(String... urls) {
    if (urls.length<1|| urls[0]==null){return null;}

    Book result = QueryUtils.fetchBookData(urls[0]);return result;}

        

    protected void onPostExecute(Book result) {

        if (result == null) {
            return;



        }}}}