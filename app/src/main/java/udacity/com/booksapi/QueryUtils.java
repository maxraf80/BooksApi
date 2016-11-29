package udacity.com.booksapi;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class QueryUtils {

    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    public static Book fetchBookData(String requestUrl) {
        URL url = createURL(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        Book book = extractFeatureFromJson(jsonResponse);
        return book;
    }

    private static URL createURL(String stringURL) {
        URL url = null;
        try {
            url = new URL(stringURL);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error creating URL", e);
        }
        return url;
    }


    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConection = null;
        InputStream inputStream = null;
        try {
            urlConection = (HttpURLConnection) url.openConnection();
            urlConection.setReadTimeout(10000);
            urlConection.setConnectTimeout(15000);
            urlConection.setRequestMethod("GET");
            urlConection.connect();

            if (urlConection.getResponseCode() == 200) {
                inputStream = urlConection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "problem retrieving the book JSON results", e);
        } finally {
            if (urlConection != null) {
                urlConection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStram) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStram != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStram, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) ;
            {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    private static Book extractFeatureFromJson(String bookJson) {
        if (TextUtils.isEmpty(bookJson)) {
            return null;
        }
        try {
            JSONObject object = new JSONObject(bookJson);
            JSONArray array = object.getJSONArray("items");

            if (array.length() > 0) {
                JSONObject item = array.getJSONObject(0);

                JSONObject volumeInfo = item.getJSONObject("volumeinfo");
                String title = volumeInfo.getString("title");

                JSONArray authors = volumeInfo.getJSONArray("authors");
                String author = authors.getString(0);

                String publisher = item.getString("publisher");

                String description = item.getString("description");

                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                String imageLink = imageLinks.getString("smallThumbnail");

                return new Book(title, author, publisher, description, imageLink);
            }


        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }
        return null;
    }
}