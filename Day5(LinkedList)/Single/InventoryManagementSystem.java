import java.util.LinkedList;
class LL{
    ItemNode head, tail;
    private int size;
    public LL(){
        this.size = 0;
    }
    public void insertFirst(String itemName, int itemId, int quantity, int price){
        ItemNode node = new ItemNode(itemName, itemId, quantity, price);
        node.next = head;
        head = node;

        if(tail == null){
            tail = head;
        }
        size+=1;
    }
    public void insertLast(String itemName, int itemId, int quantity, int price){
        if(tail== null){
            insertFirst(itemName, itemId, quantity, price);
            return;
        }
        ItemNode node = new ItemNode(itemName, itemId, quantity, price);
        tail.next = node;
        tail = node;
        size ++;
    }
    public void insert(String itemName, int itemId, int quantity, int price,int index){
        if (index ==0){
            insertFirst(itemName, itemId, quantity, price);
        }
        if (index == size){
            insertLast(itemName, itemId, quantity, price);
        }   
        ItemNode temp = head;
        for(int i = 1; i< index; i++){
            temp = temp.next;
        }
        ItemNode node = new ItemNode(itemName, itemId, quantity, price, temp.next);
        temp.next = node;
        size++;
    }
    public void deleteFirst(){
        ItemNode temp = head;
        head = head.next;
        temp.next = null;
        size -=1;
    }
    public void deleteLast(){
        ItemNode temp = head;
        for(int i = 1; i< size-1; i++){
            temp = temp.next;
        }
        temp.next = null;
        size -=1;
        tail = temp;
    }
    public void delete(int index){
        ItemNode temp = head;

        for(int i = 1; i< index; i++){
            temp = temp.next;
        }
        ItemNode nodeToDelete = temp.next;
        temp.next = nodeToDelete.next;
        nodeToDelete.next = null;
        size -=1;
    }
    public void deleteWithItemId(int itemId){
        ItemNode temp = head;
        if(head.itemId == itemId){
            System.out.println("Trying to remove first number: ");
            deleteFirst();
            return;
        }
        if(tail.itemId == itemId){
            System.out.println("Trying to remove Last number: ");
            deleteLast();
            return;
        }
        for(int i = 1; i< size; i++){
            if(temp.next.itemId == itemId){
                System.out.println("");
                ItemNode nodeToDelete = temp.next;
                temp.next = nodeToDelete.next;
                nodeToDelete.next = null;
                size -=1;
                return;
            }
            temp = temp.next;
        }
    }
    public void searchItem(int itemId){
        ItemNode temp = head;
        for(int i=0; i<size; i++){
            if(temp.itemId == itemId){
                System.out.println("Name: " + temp.itemName);
                System.out.println("itemId: " + temp.itemId);
                System.out.println("quantity: " + temp.quantity);
                System.out.println("Age: " + temp.price);
            }
            temp = temp.next;
        }
    }
    public void updateItem(int itemId, int quantity){
        ItemNode temp = head;
        for(int i=0; i<size; i++){
            if(temp.itemId == itemId){
                temp.quantity = quantity;
                System.out.println("Name: " + temp.itemName);
                System.out.println("itemId: " + temp.itemId);
                System.out.println("quantity: " + temp.quantity);
                System.out.println("Age: " + temp.price);
            }
            temp = temp.next;
        }
    }
    public void display(){
        ItemNode temp = head;
        while(temp != null){
            System.out.print(temp.itemName+ " -> ");
            temp = temp.next;
        }
        System.out.println("END \n Size: " + size);
    }
    public void getTotal(){
        int sum =0;
        ItemNode temp = head;
        while(temp != null){
            sum = sum + (temp.price * temp.quantity);
            temp = temp.next;
        }
        System.out.println(sum);
    }
    class ItemNode{
        String itemName;
        int itemId, quantity, price;
        ItemNode next;

        ItemNode(String itemName, int itemId, int quantity, int price){
            this.itemName = itemName;
            this.itemId = itemId;
            this.quantity = quantity;
            this.price = price;
        }
        ItemNode(String itemName, int itemId, int quantity, int price, ItemNode next){
            this.itemName = itemName;
            this.itemId = itemId;
            this.quantity = quantity;
            this.price = price;
            this.next = next;
        }
    }
}
public class InventoryManagementSystem {
    public static void main(String[] args) {
        LL inventory = new LL();
        inventory.insertFirst("Boat",1,2,1300);
        inventory.insertLast("Bike",2,1,122300);
        inventory.insertFirst("Car",3,2,1300);
        inventory.insert("ship", 4, 1, 10000000, 2);
        inventory.display();
        // inventory.searchItem(4);
        // inventory.display();
        inventory.getTotal();
    }
}