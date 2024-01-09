package com.example.bookshop;

import com.example.bookshop.dao.AuthorDao;
import com.example.bookshop.dao.BookDao;
import com.example.bookshop.dao.GenreDao;
import com.example.bookshop.dao.PublisherDao;
import com.example.bookshop.entity.Author;
import com.example.bookshop.entity.Book;
import com.example.bookshop.entity.Genre;
import com.example.bookshop.entity.Publisher;
import com.example.bookshop.util.IsbnGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@SpringBootApplication
public class BookShopApplication {

    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final PublisherDao publisherDao;
    @Bean
    @Transactional
    @Profile("data")
    public ApplicationRunner runner(){
        return r -> {
            Author author1 = new Author("Charles Dickens","charles@gmail.com");
            Author author2 = new Author("Thomas Hardy","hardy@gmail.com");

            Publisher publisher1 = new Publisher("New Era","newera@gmail.com");
            Publisher publisher2 = new Publisher("Sun","sun@gmail.com");

            Genre genre1 = new Genre();
            genre1.setGenreName("Tragedy");

            Genre genre2 = new Genre();
            genre2.setGenreName("Adventure");

            Book book1 = new Book(
                    1, IsbnGenerator.generate(),
                    "Oliver Twist",
                    "Excellent",
                    100.0,
                    20,"https://m.media-amazon.com/images/M/MV5BMTg4MjAxMTg5N15BMl5BanBnXkFtZTcwODIzNjEzMg@@._V1_.jpg"
            );
            Book book2 = new Book(
                    2, IsbnGenerator.generate(),
                    "Great Expectations",
                    "Good Choice",
                    25.3,
                    20,"https://m.media-amazon.com/images/W/MEDIAX_792452-T2/images/I/91FDaX8y25L._AC_UF1000,1000_QL80_.jpg"
            );
            Book book3 = new Book(
                    3, IsbnGenerator.generate(),
                    "Bleak House",
                    "Bravo",
                    50.3,
                    20,"https://m.media-amazon.com/images/I/61KXodXjmHL.jpg"
            );
            Book book4 = new Book(
                    4, IsbnGenerator.generate(),
                    "Under The Greenwood Tree",
                    "Nice",
                    70.0,
                    20,"https://m.media-amazon.com/images/I/51pG4gRLgkL.jpg"
            );
            Book book5 = new Book(
                    5, IsbnGenerator.generate(),
                    "Return of the Native",
                    "Bravo",
                    25.0,
                    20,"https://m.media-amazon.com/images/I/51TqpGZLPDL.jpg"
            );
            Book book6 = new Book(
                    6, IsbnGenerator.generate(),
                    "Far From the Maddening Crowd",
                    "Great",
                    67.0,
                    20,"https://m.media-amazon.com/images/M/MV5BNzI4NzUwNDgwN15BMl5BanBnXkFtZTgwNTI3MjMwNTE@._V1_.jpg"
            );
            //Mapping
            author1.addBook(book1);
            author1.addBook(book2);
            author1.addBook(book3);

            author2.addBook(book4);
            author2.addBook(book5);
            author2.addBook(book6);

            Publisher pub1 = publisherDao.save(publisher1);
            pub1.addBook(book1);
            pub1.addBook(book2);
            pub1.addBook(book3);

            Publisher pub2 = publisherDao.save(publisher2);

            pub2.addBook(book4);
            pub2.addBook(book5);
            pub2.addBook(book6);

            Genre gen1 = genreDao.save(genre1);

            book1.addGenres(gen1);
            book2.addGenres(gen1);
            book3.addGenres(gen1);

            Genre gen2 = genreDao.save(genre2);

            book4.addGenres(gen2);
            book5.addGenres(gen2);
            book6.addGenres(gen2);

            //save
            authorDao.save(author1);
            authorDao.save(author2);

            publisherDao.save(publisher1);
            publisherDao.save(publisher2);

            genreDao.save(genre1);
            genreDao.save(genre2);
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(BookShopApplication.class, args);
    }

}
