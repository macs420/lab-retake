import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static BookManager bookManager;

    public static void main(String[] args) {
        List<Book> initialBooks = new ArrayList<>();
        initialBooks.add(new Book("Book1", "Author1", "ISBN001", 2001));
        initialBooks.add(new Book("Book2", "Author2", "ISBN002", 2002));
        initialBooks.add(new Book("Book3", "Author3", "ISBN003", 2003));
        initialBooks.add(new Book("Book4", "Author4", "ISBN004", 2004));
        initialBooks.add(new Book("Book5", "Author5", "ISBN005", 2005));

        bookManager = new BookManager(initialBooks);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu:");
            System.out.println("[1] Add book");
            System.out.println("[2] Remove book");
            System.out.println("[3] Update book");
            System.out.println("[4] List books");
            System.out.println("[5] Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    removeBook(scanner);
                    break;
                case 3:
                    updateBook(scanner);
                    break;
                case 4:
                    listBooks();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.println("Enter title:");
        String title = scanner.nextLine();
        System.out.println("Enter author:");
        String author = scanner.nextLine();
        System.out.println("Enter ISBN:");
        String isbn = scanner.nextLine();
        System.out.println("Enter year:");
        int year = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Book book = new Book(title, author, isbn, year);
        bookManager.addBook(book);
    }

    private static void removeBook(Scanner scanner) {
        System.out.println("Enter ISBN of the book to remove:");
        String isbn = scanner.nextLine();

        Book bookToRemove = bookManager.getBooks().stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);

        if (bookToRemove != null) {
            bookManager.removeBook(bookToRemove);
            System.out.println("Book removed.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void updateBook(Scanner scanner) {
        System.out.println("Enter ISBN of the book to update:");
        String isbn = scanner.nextLine();

        Book oldBook = bookManager.getBooks().stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);

        if (oldBook != null) {
            System.out.println("Enter new title:");
            String newTitle = scanner.nextLine();
            System.out.println("Enter new author:");
            String newAuthor = scanner.nextLine();
            System.out.println("Enter new ISBN:");
            String newIsbn = scanner.nextLine();
            System.out.println("Enter new year:");
            int newYear = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            Book newBook = new Book(newTitle, newAuthor, newIsbn, newYear);
            bookManager.updateBook(oldBook, newBook);
            System.out.println("Book updated.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void listBooks() {
        List<Book> books = bookManager.getBooks();
        books.forEach(System.out::println);
    }
}
