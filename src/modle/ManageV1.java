package modle;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageV1 {
    private static final int MaxSize=100;
    public Book[] bookMap=new Book[MaxSize];
    private Scanner stringScanner=new Scanner(System.in);
    private Scanner digitalScanner=new Scanner(System.in);
    private int bookCount=0;
    public void bookManage(){

        while(true){
            System.out.println("Add Book-----1");
            System.out.println("Delete Book-----2");
            System.out.println("Modify Book-----3");
            System.out.println("Search Book-----4");
            System.out.println("Quit-----0");
            int statue=digitalScanner.nextInt();
            switch (statue){
                case 1:addBook();printBooks();break;
                case 2:deleteBook();printBooks();break;
                case 3:modifyBook();printBooks();break;
                case 4:searchBook();break;
                default:break;
            }
            if(statue==0){
                break;
            }
        }
        digitalScanner.close();
        stringScanner.close();
    }

    private int isExt(String name){
        for (int i=0;i<bookCount;i++)
        {
            if (bookMap[i].getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private void addBook(){
        System.out.println("Enter the name of the book:");
        String name=stringScanner.nextLine();
        if (isExt(name)!=-1){
            System.out.println("The book is already exist");
            return;
        }
        System.out.println("Enter the author of the book:");
        String author=stringScanner.nextLine();
        System.out.println("Enter the price of the book:");
        float price=digitalScanner.nextFloat();
        Book book=new Book(name,author,price);
        bookMap[bookCount++]=book;
        return;
    }
    private void deleteBook(){
        System.out.println("Enter the name of the book:");
        String name=stringScanner.nextLine();
        int index=isExt(name);
        if (index==-1){
            System.out.println("The book is not exist");
            return;
        }
        for (int i=index;i<bookMap.length-1;i++)
        {
            bookMap[i]=bookMap[i+1];
        }
        bookCount--;
        return;
    }
    private void modifyBook(){
        System.out.println("Enter the name of the book:");
        String name=stringScanner.nextLine();
        int index=isExt(name);
        if (index==-1){
            System.out.println("The book is not exist");
            return;
        }
        System.out.println("Enter the new name of the book:");
        String newName=stringScanner.nextLine();
        System.out.println("Enter the author of the book:");
        String author=stringScanner.nextLine();
        System.out.println("Enter the price of the book:");
        float price=digitalScanner.nextFloat();
        Book book=new Book(newName,author,price);
        bookMap[index]=book;
        return;
    }
    private void searchBook(){
        System.out.println("Enter the name of the book:");
        String name=stringScanner.nextLine();
        int index=isExt(name);
        if (index==-1){
            System.out.println("The book is not exist");
            return;
        }
            System.out.println(bookMap[index].toString());
        return;
    }
    private void printBooks(){
        if (bookMap.length!=0){
            System.out.println("Here are "+bookCount+"books");
        }else{
            System.out.println("There is no book");
            return;
        }
        for (int i=0;i<bookCount;i++) {
            System.out.println(bookMap[i]);
        }

    }
}
