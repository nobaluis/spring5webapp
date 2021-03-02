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

    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(PublisherRepository publisherRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started Boostrap");

        Publisher publisher = new Publisher();
        publisher.setName("Generic Publisher");
        publisher.setCity("Coppell");
        publisher.setState("Texas");

        publisherRepository.save(publisher);

        Author jose = new Author("Jose", "Saramago");
        Book enemy = new Book("Enemy", "123456789");
        jose.getBooks().add(enemy);
        enemy.getAuthors().add(jose);
        enemy.setPublisher(publisher);

        authorRepository.save(jose);
        bookRepository.save(enemy);

        Author stephen = new Author("Stephen", "King");
        Book mercedes = new Book("Mr. Mercedes", "12345678");
        stephen.getBooks().add(mercedes);
        mercedes.getAuthors().add(stephen);
        mercedes.setPublisher(publisher);

        authorRepository.save(stephen);
        bookRepository.save(mercedes);


        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());

    }
}
