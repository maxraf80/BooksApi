package udacity.com.booksapi;

public class Book {

private String title;
private String authors;
private String publisher;
private String description;
private String    thumbnail;


public Book (String mTitle,String mAuthors,String mPublisher, String mDescription, String mThumnail){

    title=mTitle;
    authors=mAuthors;
    publisher=mPublisher;
    description=mDescription;
    thumbnail=mThumnail;}


public String getTitle(){return title;}
public String getAuthors(){return authors;}
public String getPublisher(){return publisher;}
public String getDescription(){return description;}
public String    getThumbnail(){return thumbnail;}
}
