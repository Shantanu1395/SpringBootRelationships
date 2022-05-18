package com.example.Relationships.Service;

import com.example.Relationships.Model.Address;
import com.example.Relationships.Model.Book;
import com.example.Relationships.Model.Library;
import com.example.Relationships.Model.Student;
import com.example.Relationships.Repository.AddressRepository;
import com.example.Relationships.Repository.BookRepository;
import com.example.Relationships.Repository.LibraryRepository;
import com.example.Relationships.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private LibraryRepository address;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentRepository studentRepository;

    //Library
    public Library addLibrary(Library library){
        if(library.getAddress() != null){
            addressRepository.save(library.getAddress());
        }
        return libraryRepository.save(library);
    }

    public List<Library> getLibraries(){
        return libraryRepository.findAll();
    }

    public Library getLibraryById(int libraryId){
        return libraryRepository.findById(libraryId).orElse(null);
    }

    //Address
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    public Address getAddressById(int addressId) {
        return addressRepository.getById(addressId);
    }

    //Mapping address to library
    public Library mapLibraryToAddress(int libraryId, int addressId) {
        Library library = libraryRepository.findById(libraryId).orElse(null);
        Address address = addressRepository.findById(addressId).orElse(null);

        if(library == null || address == null){
            return null;
        }
        if(library!=null && address!=null){
            library.setAddress(address);
            return libraryRepository.save(library);
        }
        return null;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    public Book addBookToLibrary(int libraryId, int bookId) {
        Library library = libraryRepository.findById(libraryId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);
        if(library==null || book==null){
            return null;
        }
        if(library!=null && book!=null){
            book.setLibrary(library);
            return bookRepository.save(book);

        }
        return null;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student mapStudentToBook(int studentId, int bookId) {
        Student s = studentRepository.findById(studentId).get();
        Book b = bookRepository.findById(bookId).get();
        Set<Book> existingBooks = s.getBooks();
        existingBooks.add(b);

        Set<Student> existingStudents = b.getStudents();
        existingStudents.add(s);

        s.setBooks(existingBooks);
        b.setStudents(existingStudents);
        return studentRepository.save(s);
    }
}
