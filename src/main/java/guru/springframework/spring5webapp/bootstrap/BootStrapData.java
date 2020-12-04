package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(
        AuthorRepository authorRepository,
        BookRepository bookRepository, PublisherRepository publisherRepository)
    {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher aw = new Publisher("Addison Wesley", "24 Clarendon St", "Boston", "Massachusetts", "02118");


        Author eric = new Author("Eric", "Evans");
        Book book = new Book("Domain Driven Design", "123123");
        aw.getBooks().add(book);

        eric.getBooks().add(book);
        book.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(book);
       // publisherRepository.save(aw);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "423454534");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        aw.getBooks().add(noEJB);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(aw);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());


        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Publisher number of books: " + aw.getBooks().size());
    }
}
