import model.Book;
import model.ColorOfTheCover;
import model.Publisher;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = new File(Book.EXTENT_FILENAME);
        if(file.exists()) {
            Book.loadExtent();
        }

      //  Book example = new Book(1, "À la recherche du temps perdu", LocalDate.of(1913, 11,14), "Marcel Proust", ColorOfTheCover.Blue, 14, 2, new Publisher("Wydawnictwo MG", "Poland", "MG"));
      //  System.out.print("New book: ");
      //  System.out.println(example);


        System.out.print("Find by Marcel Proust: ");
        System.out.println(Book.findByAuthor("Marcel Proust"));
        System.out.print("Find by the color of the cover blue: ");
        System.out.println(Book.findByColorOfTheCover(ColorOfTheCover.Blue));
        System.out.print("Find by the color of the cover yellow: ");
        System.out.println(Book.findByColorOfTheCover(ColorOfTheCover.Yellow));

        System.out.println("Book extent: ");
        Book.getExtent().stream().forEach(System.out::println);

        Book.saveExtent();
    }
    }

