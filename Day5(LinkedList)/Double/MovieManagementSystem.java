class MovieNode {
    String title;
    String director;
    int year;
    double rating;

    MovieNode prev, next;

    public MovieNode(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.prev = null;
        this.next = null;
    }

    @Override
    public String toString() {
        return "[Title: " + title + ", Director: " + director + ", Year: " + year + ", Rating: " + rating + "]";
    }
}

class MovieList {
    private MovieNode head = null;
    private MovieNode tail = null;
    public void addAtBeginning(String title, String director, int year, double rating) {
        MovieNode newNode = new MovieNode(title, director, year, rating);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    public void addAtEnd(String title, String director, int year, double rating) {
        MovieNode newNode = new MovieNode(title, director, year, rating);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }
    public void addAtPosition(String title, String director, int year, double rating, int position) {
        if (position <= 1 || head == null) {
            addAtBeginning(title, director, year, rating);
            return;
        }

        MovieNode newNode = new MovieNode(title, director, year, rating);
        MovieNode current = head;
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

    public void removeByTitle(String title) {
        MovieNode current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
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
                System.out.println("Removed: " + title);
                return;
            }
            current = current.next;
        }
        System.out.println("Movie not found: " + title);
    }

    public void searchByDirector(String director) {
        MovieNode current = head;
        boolean found = false;
        while (current != null) {
            if (current.director.equalsIgnoreCase(director)) {
                System.out.println(current);
                found = true;
            }
            current = current.next;
        }
        if (!found) System.out.println("No movies found by director: " + director);
    }
    public void searchByRating(double minRating) {
        MovieNode current = head;
        boolean found = false;
        while (current != null) {
            if (current.rating >= minRating) {
                System.out.println(current);
                found = true;
            }
            current = current.next;
        }
        if (!found) System.out.println("No movies found with rating >= " + minRating);
    }
    public void updateRating(String title, double newRating) {
        MovieNode current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                current.rating = newRating;
                System.out.println("Updated rating for " + title + " to " + newRating);
                return;
            }
            current = current.next;
        }
        System.out.println("Movie not found: " + title);
    }
    public void displayForward() {
        System.out.println("Movies in order:");
        MovieNode current = head;
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }
    public void displayReverse() {
        System.out.println("Movies in reverse order:");
        MovieNode current = tail;
        while (current != null) {
            System.out.println(current);
            current = current.prev;
        }
    }
}

public class MovieManagementSystem {
    public static void main(String[] args) {
        MovieList movieList = new MovieList();

        movieList.addAtEnd("Inception", "Christopher Nolan", 2010, 8.8);
        movieList.addAtBeginning("Interstellar", "Christopher Nolan", 2014, 8.6);
        movieList.addAtPosition("The Dark Knight", "Christopher Nolan", 2008, 9.0, 2);
        movieList.addAtEnd("Joker", "Todd Phillips", 2019, 8.5);

        movieList.displayForward();
        movieList.displayReverse();

        System.out.println("\nSearching by Director 'Christopher Nolan':");
        movieList.searchByDirector("Christopher Nolan");

        System.out.println("\nSearching by Rating >= 8.7:");
        movieList.searchByRating(8.7);

        System.out.println("\nUpdating rating for 'Joker':");
        movieList.updateRating("Joker", 9.1);
        movieList.displayForward();

        System.out.println("\nRemoving movie 'Inception':");
        movieList.removeByTitle("Inception");
        movieList.displayForward();
    }
}