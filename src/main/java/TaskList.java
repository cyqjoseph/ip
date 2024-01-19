import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final int MAX_ITEMS = 100;
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        if (this.tasks.size() < MAX_ITEMS) {
            this.tasks.add(new Task(task));
            System.out.println("Added: " + task);
        } else {
            System.out.println("The task list is full.");
        }
    }

    public void markTask(int index) {
        if (index <= 0 || index > this.tasks.size()) {
            System.out.println("Task index is out of range.");
            return;
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        currTask.formatString();
    }

    public void unmarkTask(int index) {
        if (index <= 0 || index > this.tasks.size()) {
            System.out.println("Task index is out of range.");
            return;
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        currTask.formatString();
    }

    public void listTasks() {
        if (this.tasks.size() == 0) {
            System.out.println("The task list is empty.");
        } else {
            System.out.println("\tHere are the tasks in your list: ");
            for (int i = 0; i < this.tasks.size(); i++) {
                Task currTask = this.tasks.get(i);
                System.out.println((i + 1) + "." + currTask.formatString());
            }
        }
    }
}
