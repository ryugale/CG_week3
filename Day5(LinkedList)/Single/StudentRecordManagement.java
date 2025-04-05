class StudentRecord{
    private Node head;
    private Node tail;
    private int size;

    public StudentRecord(){
        this.size = 0;
    }
    public void insert_first(int rollnumber, String name, int age, char grade){
        Node node = new Node(rollnumber, name, age, grade);
        node.next = head;
        head = node; 

        if(tail==null){
            tail = head;
        }
        size++;
    }
    public void insert_last(int rollnumber, String name, int age, char grade){
        if(tail==null){
            insert_first(rollnumber, name, age, grade);
            return;
        }
        Node node = new Node(rollnumber, name, age, grade);
        tail.next = node;
        tail = node;
        size++;
    }
    public void insert_middle(int  rollnumber, String name, int age, char grade, int index){
        if(index == 0){
            insert_first(rollnumber, name, age, grade);
            return;
        }
        else if(index == size){
            insert_last(rollnumber, name, age, grade);
            return;
        }
        else if(index > size){
            System.out.println("Invalid");
            return;
        }
        Node temp = head;
        for(int i = 1; i<index;i++){
            temp = temp.next;
        }
        Node node = new Node(rollnumber, name, age, grade);
        node.next = temp.next;
        temp.next = node;
        size++;
    }
    public void delete_first(){
        head = head.next;
        size--;
    }
    public void delete_last(){
        Node temp = head;
        for(int i = 1;i<size-1;i++){
            temp = temp.next;
        }
        temp.next = null;
        tail = temp;
        size--;
    }
    public void delete_middle(int index){
        Node point = head;
        Node temp;
        for(int i =1;i<index;i++){
            point = point.next;
        }
        temp = point.next;
        point.next =temp.next;
        temp.next = null;
        size--;
    }
    public void delete_rollno(int rollnumber){
        Node temp = head;
        if(head.rollnumber==rollnumber){
            delete_first();
        }
        else if(tail.rollnumber==rollnumber){
            delete_last();
        }
        else{
            for(int i=1; i<size-1;i++){
            if(temp.next.rollnumber == rollnumber){
                delete_middle(i);
                return;
            }
        }
    }
    }
    public void searchstudent(int rollnumber){
        Node temp = head;
        for(int i = 0;i<size;i++){
            if(temp.rollnumber==rollnumber){
                System.out.println("Student name: "+temp.name);
                System.out.println("Student grade: "+temp.grade);
            }temp = temp.next;
        }
    }
    public void updategrade(int rollnumber, char grade){
        Node temp = head;
        for(int i = 0;i<size;i++){
            if(temp.rollnumber==rollnumber){
                temp.grade=grade;
                System.out.println("Student name: "+temp.name);
                System.out.println("Student grade: "+temp.grade);
            }
        }
    }
    public void Display(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.rollnumber+" ");
            System.out.print(temp.name+" ");
            System.out.print(temp.age+" ");
            System.out.print(temp.grade+" -> ");
            temp = temp.next;
        }
        System.out.println(" -> END");
    }
}
    class Node{
        int rollnumber;
        String name;
        int age;
        char grade;
        Node next;

        public Node(int rollnumber, String name, int age, char grade){
            this.rollnumber = rollnumber;
            this.name = name;
            this.age = age;
            this.grade = grade;
            this.next = null;
        }
    }
public class StudentRecordManagement {
    public static void main(String[] args) {
            StudentRecord list = new StudentRecord();
        list.insert_first(001, "Dina", 20, 'A');
        list.insert_first(002, "Buwa", 20, 'A');
        list.insert_first(003, "karun", 20, 'B');
        list.insert_middle(004, "Jawa", 21, 'B', 2);
        list.insert_last(005, "mukesh", 21, 'o');
        list.delete_rollno(003);
        list.Display();
    }
}