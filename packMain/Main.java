package packMain;

import packBook.Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Клас Main представляє програму для обробки і відображення інформації про книги.
 * Програма дозволяє читати дані про книги з файлу, фільтрувати книги за автором, видавництвом та роком видання,
 * та виводити результати в консоль.
 *
 * @author Mykhailo Matsyshyn
 */
public class Main {
    /**
     * Головний метод програми, який обробляє введені дані та виводить результати.
     *
     * @param args Аргументи командного рядка. Може містити шлях до файлу з даними про книги.
     */
    public static void main(String[] args) {
        String filePath = null;
        Scanner scanner = new Scanner(System.in);

        if (args.length == 1) {
            filePath = args[0];
        } else {
            System.out.print("\u001B[31m\u00BB Введіть шлях до файлу: \u001B[0m");
            filePath = scanner.nextLine();
        }

        List<Book> books = readBooksFromFile(filePath);

        if (books.isEmpty()) {
            System.out.println("\n\u001B[31m Файл містить пустий список книг.\u001B[0m");
            return;
        }

        System.out.print("\u001B[36m\u00BB Введіть ім'я автора для фільтрації: \u001B[0m");
        String authorName = scanner.nextLine();
        System.out.println("\u001B[32m  Список книг заданого автора:\u001B[0m");
        List<Book> booksByAuthor = BooksByAuthor(books, authorName);
        printBooks(booksByAuthor);

        System.out.print("\n\u001B[36m\u00BB Введіть назву видавництва для фільтрації: \u001B[0m");
        String publisherName = scanner.nextLine();
        System.out.println("\u001B[32m  Список книг, що видані заданим видавництвом:\u001B[0m");
        List<Book> booksByPublisher = BooksByPublisher(books, publisherName);
        printBooks(booksByPublisher);

        System.out.print("\n\u001B[36m\u00BB Введіть рік для фільтрації книг, що випущені після цього року: \u001B[0m");
        int year = scanner.nextInt();
        System.out.println("\u001B[32m  Список книг, що випущені після заданого року:\u001B[0m");
        List<Book> booksAfterYear = BooksAfterYear(books, year);
        printBooks(booksAfterYear);

        scanner.close();
    }

    /**
     * Метод для зчитування даних про книги з файлу та створення списку книг.
     *
     * @param filePath Шлях до файлу з даними про книги.
     * @return Список книг, які були зчитані з файлу.
     */
    private static List<Book> readBooksFromFile(String filePath) {
        List<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line; // рядок з файлу
            while ((line = reader.readLine()) != null) {
                String[] bookData = line.split(",");
                if (bookData.length == 7) {
                    int id = Integer.parseInt(bookData[0].trim());
                    String title = bookData[1].trim();
                    String author = bookData[2].trim();
                    String publisher = bookData[3].trim();
                    int year = Integer.parseInt(bookData[4].trim());
                    int pages = Integer.parseInt(bookData[5].trim());
                    double price = Double.parseDouble(bookData[6].trim());

                    Book book = new Book(id, title, author, publisher, year, pages, price);
                    books.add(book);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("\u001B[31mПомилка при зчитуванні файлу: \u001B[0m" + e.getMessage());
            System.exit(1);
        }

        return books;
    }

    /**
     * Метод для фільтрації книг за іменем автора.
     *
     * @param books      Список книг для фільтрації.
     * @param authorName Ім'я автора для фільтрації.
     * @return Список книг, що відповідають введеному імені автора.
     */
    private static List<Book> BooksByAuthor(List<Book> books, String authorName) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(authorName)) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Метод для фільтрації книг за назвою видавництва.
     *
     * @param books         Список книг для фільтрації.
     * @param publisherName Назва видавництва для фільтрації.
     * @return Список книг, що видані заданим видавництвом.
     */
    private static List<Book> BooksByPublisher(List<Book> books, String publisherName) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getPublisher().equalsIgnoreCase(publisherName)) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Метод для фільтрації книг за роком видання.
     *
     * @param books Список книг для фільтрації.
     * @param year  Рік видання для фільтрації.
     * @return Список книг, що випущені після заданого року.
     */
    private static List<Book> BooksAfterYear(List<Book> books, int year) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getPublicationYear() > year) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Метод для виведення списку книг у консоль.
     *
     * @param books Список книг для виведення.
     */
    private static void printBooks(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
