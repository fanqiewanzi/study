package modle;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ManageV2 {
    ArrayList<Book> bookArrayList = new ArrayList<Book>();
    private final Scanner stringScanner=new Scanner(System.in);
    private final Scanner digitalScanner=new Scanner(System.in);
    public void bookManage(){

        try {
            loadFile("a.txt");
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
                    outputFile("a.txt");
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
        InputStream f=null;
        InputStreamReader reader=null;
        try{
            f = new FileInputStream(filePath);
            reader=new InputStreamReader(f,"UTF-8");
            StringBuffer sb = new StringBuffer();
            while (reader.ready()) {
                char elem=(char) reader.read();
                // 转成char加到StringBuffer对象中
                if(elem=='\n'){
                    String str=sb.toString();
                    String arr[]= str.split(",");
                    Book book=new Book(arr[0],arr[1],Float.parseFloat(arr[2]));
                    bookArrayList.add(book);
                    sb=new StringBuffer();
                    continue;
                }
                sb.append(elem);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader!=null){
                // 关闭读取流
                reader.close();
            }
            if (f!=null){
                // 关闭输入流
                f.close();
            }
        }
    }

    private void outputFile(String filePath) throws IOException{
            FileOutputStream os=null;
            OutputStreamWriter writer=null;
        try{
            File f = new File(filePath);
            os = new FileOutputStream(f);
            writer = new OutputStreamWriter(os, "UTF-8");
            int length=bookArrayList.size();
            for (int i=0;i<length;i++)
            {
                writer.append(bookArrayList.get(i).toString());
                writer.append("\n");
            }
            writer.close();
            os.close();
        }catch (IOException e) {
            System.out.println("Exception");
        }finally {
            if(os!=null){
                os.close();
            }
            if (writer!=null){
                writer.close();
            }
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
        for (int i=0;i<bookArrayList.size();i++) {
            System.out.println(bookArrayList.get(i));
        }
    }
}
