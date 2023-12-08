package packBook;

/**
 * Клас, що представляє інформацію про книгу.
 *
 * @author Mykhailo Matsyshyn
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private int pageCount;
    private double price;

    /**
     * Конструктор для створення об'єкта книги.
     *
     * @param id              Унікальний ідентифікатор книги.
     * @param title           Назва книги.
     * @param author          Автор книги.
     * @param publisher       Видавництво книги.
     * @param publicationYear Рік видання книги.
     * @param pageCount       Кількість сторінок книги.
     * @param price           Ціна книги.
     */
    public Book(int id, String title, String author, String publisher,
                int publicationYear, int pageCount, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.pageCount = pageCount;
        this.price = price;
    }

    /**
     * Отримати ім'я автора книги.
     *
     * @return Ім'я автора.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Отримати назву видавництва книги.
     *
     * @return Назва видавництва.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Отримати рік видання книги.
     *
     * @return Рік видання.
     */
    public int getPublicationYear() {
        return publicationYear;
    }

    /**
     * Перевизначений метод для представлення об'єкта книги у вигляді рядка.
     *
     * @return Рядок, що містить інформацію про книгу.
     */
    @Override
    public String toString() {
        return " ==== Книга #" + id + " ====\n - Назва: " + title + "\n - Автор: " + author +
                "\n - Видавництво: " + publisher + "\n - Рік видання: " + publicationYear +
                "\n - К-ть сторінок: " + pageCount + "\n - Ціна: " + price;
    }
}
