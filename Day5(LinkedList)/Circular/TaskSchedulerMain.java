
class TaskCLL {

    private TaskNode head;
    private TaskNode tail;
    private int size;
    private TaskNode current;

    public TaskCLL() {
        this.size = 0;
    }

    public void addFirst(int taskId, String taskName, int priority, String dueDate) {
        TaskNode node = new TaskNode(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = node;
            tail = node;
            node.next = head;
        } else {
            node.next = head;
            head = node;
            tail.next = head;
        }
        size++;
    }

    public void addLast(int taskId, String taskName, int priority, String dueDate) {
        if (tail == null) {
            addFirst(taskId, taskName, priority, dueDate);
            return;
        }
        TaskNode node = new TaskNode(taskId, taskName, priority, dueDate);
        tail.next = node;
        tail = node;
        tail.next = head;
        size++;
    }

    public void add(int taskId, String taskName, int priority, String dueDate, int index) {
        if (index == 0) {
            addFirst(taskId, taskName, priority, dueDate);
            return;
        }
        if (index == size) {
            addLast(taskId, taskName, priority, dueDate);
            return;
        }
        TaskNode temp = head;
        for (int i = 1; i < index; i++) {
            temp = temp.next;
        }
        TaskNode node = new TaskNode(taskId, taskName, priority, dueDate);
        node.next = temp.next;
        temp.next = node;
        size++;
    }

    public void removeByTaskId(int taskId) {
        if (head == null) {
            return;
        }
        TaskNode temp = head;
        TaskNode prev = tail;
        for (int i = 0; i < size; i++) {
            if (temp.taskId == taskId) {
                if (temp == head) {
                    head = head.next;
                    tail.next = head;
                } else if (temp == tail) {
                    tail = prev;
                    tail.next = head;
                } else {
                    prev.next = temp.next;
                }
                size--;
                return;
            }
            prev = temp;
            temp = temp.next;
        }
    }

    public void viewCurrentTask() {
        if (current == null) {
            current = head;
        }
        System.out.println("Current Task: " + current.taskName);
        current = current.next;
    }

    public void displayTasks() {
        if (head == null) {
            return;
        }
        TaskNode temp = head;
        for (int i = 0; i < size; i++) {
            System.out.println(temp.taskName + " | Priority: "
                    + temp.priority);
            temp = temp.next;
        }
    }

    public void searchByPriority(int priority) {
        if (head == null) {
            return;
        }
        TaskNode temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.priority == priority) {
                System.out.println("Task: " + temp.taskName + " | Due: "
                        + temp.dueDate);
            }
            temp = temp.next;
        }
    }

    private class TaskNode {

        int taskId;
        String taskName;
        int priority;
        String dueDate;
        TaskNode next;

        TaskNode(int taskId, String taskName, int priority, String dueDate) {
            this.taskId = taskId;
            this.taskName = taskName;
            this.priority = priority;
            this.dueDate = dueDate;
        }
    }
}

public class TaskSchedulerMain {

    public static void main(String[] args) {
        TaskCLL scheduler = new TaskCLL();
        scheduler.addFirst(1, "Submit Assignment", 2, "2025-04-15");
        scheduler.addLast(2, "Team Meeting", 1, "2025-04-16");
        scheduler.add(3, "Doctor Appointment", 3, "2025-04-17", 1);
        scheduler.addLast(4, "Pay Bills", 2, "2025-04-18");
        scheduler.addFirst(5, "Read Book", 3, "2025-04-19");
        scheduler.displayTasks();
        scheduler.viewCurrentTask();
        scheduler.viewCurrentTask();
        scheduler.viewCurrentTask();
        scheduler.searchByPriority(2);
        scheduler.removeByTaskId(3);
        scheduler.displayTasks();
    }
}
