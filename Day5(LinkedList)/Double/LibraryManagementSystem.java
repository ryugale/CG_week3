class BookNode {
    String title, author, genre;
    int bookId;
    boolean isAvailable;
    BookNode next, prev;

    public BookNode(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
        this.next = null;
        this.prev = null;
    }

    @Override
    public String toString() {
        return "[ID: " + bookId + ", Title: " + title + ", Author: " + author +
                ", Genre: " + genre + ", Available: " + (isAvailable ? "Yes" : "No") + "]";
    }
}

class Library {
    private BookNode head = null;
    private BookNode tail = null;

    public void addAtBeginning(String title, String author, String genre, int bookId, boolean isAvailable) {
        BookNode newNode = new BookNode(title, author, genre, bookId, isAvailable);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }
   
    public void addAtEnd(String title, String author, String genre, int bookId, boolean isAvailable) {
        BookNode newNode = new BookNode(title, author, genre, bookId, isAvailable);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }
    public void addAtPosition(String title, String author, String genre, int bookId, boolean isAvailable, int position) {
        if (position <= 1 || head == null) {
            addAtBeginning(title, author, genre, bookId, isAvailable);
            return;
        }

        BookNode newNode = new BookNode(title, author, genre, bookId, isAvailable);
        BookNode current = head;
        int count = 1;

        while (current.next != null && count < position - 1) {
            current = current.next;
            count++;
        }
        newNode.next = current.next;
        newNode.prev = current;

        if (current.next != null) {
            current.next.prev = newNode;
        } else {
            tail = newNode;
        }

        current.next = newNode;
    }
    public void removeById(int bookId) {
        BookNode current = head;
        while (current != null) {
            if (current.bookId == bookId) {
                if (current == head) {
                    head = current.next;
                    if (head != null) head.prev = null;
                } else if (current == tail) {
                    tail = current.prev;
                    if (tail != null) tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                System.out.println("Removed Book ID: " + bookId);
                return;
            }
            current = current.next;
        }
        System.out.println("Book ID not found: " + bookId);
    }
    public void searchByTitle(String title) {
        BookNode current = head;
        boolean found = false;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                System.out.println(current);
                found = true;
            }
            current = current.next;
        }
        if (!found) System.out.println("No books found with title: " + title);
    }
    public void searchByAuthor(String author) {
        BookNode current = head;
        boolean found = false;
        while (current != null) {
            if (current.author.equalsIgnoreCase(author)) {
                System.out.println(current);
                found = true;
            }
            current = current.next;
        }
        if (!found) System.out.println("No books found by author: " + author);
    }

    public void updateAvailability(int bookId, boolean newStatus) {
        BookNode current = head;
        while (current != null) {
            if (current.bookId == bookId) {
                current.isAvailable = newStatus;
                System.out.println("Updated availability for Book ID " + bookId + " to " + (newStatus ? "Available" : "Not Available"));
                return;
            }
            current = current.next;
        }
        System.out.println("Book ID not found: " + bookId);
    }
    public void displayForward() {
        System.out.println("Library Book List (Forward):");
        BookNode current = head;
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }

    public void displayReverse() {
        System.out.println("Library Book List (Reverse):");
        BookNode current = tail;
        while (current != null) {
            System.out.println(current);
            current = current.prev;
        }
    }
    public int countBooks() {
        int count = 0;
        BookNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        library.addAtEnd("1984", "George Orwell", "Dystopian", 101, true);
        library.addAtBeginning("Pride and Prejudice", "Jane Austen", "Romance", 102, true);
        library.addAtPosition("To Kill a Mockingbird", "Harper Lee", "Classic", 103, false, 2);
        library.addAtEnd("The Hobbit", "J.R.R. Tolkien", "Fantasy", 104, true);

        library.displayForward();
        library.displayReverse();

        System.out.println("\nSearch by Title '1984':");
        library.searchByTitle("1984");

        System.out.println("\nSearch by Author 'Jane Austen':");
        library.searchByAuthor("Jane Austen");

        System.out.println("\nUpdate availability of Book ID 103:");
        library.updateAvailability(103, true);

        System.out.println("\nRemove Book ID 101:");
        library.removeById(101);

        library.displayForward();

        System.out.println("\nTotal number of books: " + library.countBooks());
    }
}