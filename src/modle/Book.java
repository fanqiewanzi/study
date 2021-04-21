package modle;

public class Book {
    private String bookName;
    private String author;
    private float price;

    public Book(){
    }

    public Book(String name,String author,float price){
        this.bookName=name;
        this.author=author;
        this.price=price;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return bookName;
    }

    public float getPrice(){
        return price;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  bookName +
                "," + author +
                "," + price ;
    }
}
