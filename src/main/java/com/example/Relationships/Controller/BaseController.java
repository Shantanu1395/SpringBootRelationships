package com.example.Relationships.Controller;

import com.example.Relationships.Model.Address;
import com.example.Relationships.Model.Book;
import com.example.Relationships.Model.Library;
import com.example.Relationships.Model.Student;
import com.example.Relationships.Service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BaseController {

    @Autowired
    private LibraryService libraryService;

    //Library
    @PostMapping("/library")
    public Library addLibrary(@RequestBody Library library){
        return libraryService.addLibrary(library);
    }

    @GetMapping("/library")
    public List<Library> getLibraries(){
        return libraryService.getLibraries();
    }

    @GetMapping("/library/{libraryId}")
    public Library getLibraries(@PathVariable String libraryId){
        return libraryService.getLibraryById(Integer.parseInt(libraryId));
    }

    //Address
    @PostMapping("/address")
    public Address addAddress(@RequestBody Address address){
        return libraryService.addAddress(address);
    }

    @GetMapping("/address")
    public List<Address> getAllAddress(){
        return libraryService.getAllAddress();
    }

    @GetMapping("/address/{addressId}")
    public Address getAddressById(@PathVariable String addressId){
        return libraryService.getAddressById(Integer.parseInt(addressId));
    }

    @PutMapping("/library/{libraryId}/address/{addressId}")
    public Library mapLibraryToAddress(@PathVariable String libraryId, @PathVariable String addressId){
       return libraryService.mapLibraryToAddress(Integer.parseInt(libraryId), Integer.parseInt(addressId));
    }

    //Books
    @PostMapping("/book")
    public Book addBook(@RequestBody Book book){
        return libraryService.addBook(book);
    }

    @GetMapping("/book")
    public List<Book> getAllBooks(){
        return libraryService.getAllBooks();
    }

    @GetMapping("/book/{bookId}")
    public Book getBookById(@PathVariable String bookId){
        return libraryService.getBookById(Integer.parseInt(bookId));
    }

    @PutMapping("/library/{libraryId}/book/{bookId}")
    public Book addBookToLibrary(@PathVariable String libraryId, @PathVariable String bookId){
        return libraryService.addBookToLibrary(Integer.parseInt(libraryId), Integer.parseInt(bookId));
    }

    //Student
    @PostMapping(value = "/student", consumes = "application/json")
    public Student addStudent(@RequestBody Student student){
        return libraryService.addStudent(student);
    }

    @GetMapping("/student")
    public List<Student> getAllStudents(){
        return libraryService.getAllStudents();
    }

    @PutMapping("/student/{studentId}/book/{bookId}")
    public Student mapStudentToBook(@PathVariable int studentId, @PathVariable int bookId){
        return libraryService.mapStudentToBook(studentId, bookId);
    }


}
