package com.driver;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    HashMap<Integer, Book> bookHashMap;

    public BookRepository(){
        bookHashMap = new HashMap<>();
    }

    public Book save(Book book){
        int id = book.getId();
        bookHashMap.put(id, book);
        return book;
    }

    public Book findBookById(int id){
        Book book = null;
        if(bookHashMap.containsKey(id)) book = bookHashMap.get(id);
        return book;
    }

    public List<Book> findAll(){
        List<Book> bookList = new ArrayList<>();
        for(Book book : bookHashMap.values()) bookList.add(book);
        return bookList;
    }

    public void deleteBookById(int id){
       bookHashMap.remove(id);
    }

    public void deleteAll(){
       for(int key : bookHashMap.keySet()) bookHashMap.remove(key);
    }

    public List<Book> findBooksByAuthor(String author){
        List<Book> bookList = new ArrayList<>();
        for(Book book : bookHashMap.values()) {
            if(book.getAuthor().equals(author)) bookList.add(book);
        }
        return bookList;

    }

    public List<Book> findBooksByGenre(String genre){
        List<Book> bookList = new ArrayList<>();
        for(Book book : bookHashMap.values()) {
            if(book.getGenre().equals(genre)) bookList.add(book);
        }
        return bookList;
    }
}
