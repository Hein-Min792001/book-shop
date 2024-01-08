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

@RequiredArgsConstructor
@SpringBootApplication
public class BookShopApplication {

    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final PublisherDao publisherDao;
    //@Bean
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
                    20,"https://source.unsplash.com/400*300/?flower"
            );

        };
    }
    public static void main(String[] args) {
        SpringApplication.run(BookShopApplication.class, args);
    }

}
