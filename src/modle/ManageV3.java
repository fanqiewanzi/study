package modle;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author BAZINGA
 */
public class ManageV3 {
    ArrayList<Book> bookArrayList = new ArrayList<Book>();
    private final Scanner stringScanner=new Scanner(System.in);
    private final Scanner digitalScanner=new Scanner(System.in);
    public void bookManage(){
        try {
            loadFile("book.txt");
            while (true) {
                System.out.println("Add Book-----1");
                System.out.println("Delete Book-----2");
                System.out.println("Modify Book-----3");
                System.out.println("Search Book-----4");
                System.out.println("Quit-----0");
                int statue = digitalScanner.nextInt();
                switch (statue) {
                    case 1:
                        addBook();
                        printBooks();
                        break;
                    case 2:
                        deleteBook();
                        printBooks();
                        break;
                    case 3:
                        modifyBook();
                        printBooks();
                        break;
                    case 4:
                        searchBook();
                        break;
                    default:
                        break;
                }
                if (statue == 0) {
                    outputFile("book.txt");
                    break;
                }
            }
        }catch (IOException e){
            System.out.println("Exception");
        }finally {
            digitalScanner.close();
            stringScanner.close();
        }

    }

    private void loadFile(String filePath) throws IOException {
        //打开filePath下的文件并创建一个缓存读取对象
        File file=new File(filePath);
        BufferedReader bufferedReader=null;
        try{
            //初始化缓存读取对象
            bufferedReader=new BufferedReader(new FileReader(file));
            String str;
            //每次读取一行
            while((str=bufferedReader.readLine())!=null){
                String[] temp=str.split(",");
                String bookName =temp[0];
                String author=temp[1];
                float price =Float.parseFloat(temp[2]);
                Book book=new Book(bookName,author,price);
                bookArrayList.add(book);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            bufferedReader.close();
        }
    }

    private void outputFile(String filePath) throws IOException{
       String str="";
       //将所有book放进字符串中
        for (Book book:bookArrayList) {
            str=str+book+"\n";
        }
       //定义缓存输出对象
       BufferedOutputStream bufferedOutputStream=null;
       FileOutputStream fileOutputStream=null;
       try {
           //定义文件输出流并初始化
           fileOutputStream=new FileOutputStream(filePath);
           bufferedOutputStream=new BufferedOutputStream(fileOutputStream);
           //将字符串写入并输出到文件输出流中
           bufferedOutputStream.write(str.getBytes());
           bufferedOutputStream.flush();

       }catch (FileNotFoundException e){
           e.printStackTrace();
       }catch (IOException e){
           e.printStackTrace();
       }finally{
           bufferedOutputStream.close();
           fileOutputStream.close();
       }
    }
    private int isExt(String name){
        for (int i=0;i<bookArrayList.size();i++)
        {
            if (bookArrayList.get(i).getName().equals(name)) {
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
        bookArrayList.add(book);

    }
    private void deleteBook(){
        System.out.println("Enter the name of the book:");
        String name=stringScanner.nextLine();
        int index=isExt(name);
        if (index==-1){
            System.out.println("The book is not exist");
            return;
        }
        bookArrayList.remove(index);

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
        bookArrayList.set(index,book);

    }
    private void searchBook(){
        System.out.println("Enter the name of the book:");
        String name=stringScanner.nextLine();
        int index=isExt(name);
        if (index==-1){
            System.out.println("The book is not exist");
            return;
        }
        System.out.println(bookArrayList.get(index));

    }

    private void printBooks(){
        if (!bookArrayList.isEmpty()){
            System.out.println("Here are "+bookArrayList.size()+"books");
        }else{
            System.out.println("There is no book");
            return;
        }
        for (Book book:bookArrayList
             ) {
            System.out.println(book);
        }
    }
}
