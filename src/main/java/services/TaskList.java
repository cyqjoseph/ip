package services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.DukeException;
import tasks.Task;

/**
 * The TaskList class manages a list of tasks, allowing for adding, deleting, marking, and unmarking tasks.
 */
public class TaskList {
    private static final int MAX_ITEMS = 100;
    private List<Task> tasks;

    /**
     * Constructs a new TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }


    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Task to add cannot be null";
        assert this.tasks.size() < MAX_ITEMS : "Task list is at maximum capacity";
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list based on its index.
     *
     * @param index The index of the task to be deleted.
     * @throws DukeException If the index is out of range.
     */
    public Task deleteTask(int index) throws DukeException {
        assert index >= 1 : "Index cannot be less than 1";
        if (this.tasks.size() == 0) {
            throw new DukeException("Task index is out of range.");
        }
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeException("Index out of range");
        }
        Task deletedTask = this.tasks.remove(index - 1);
        return deletedTask;
    }

    public String getTaskSummary() {
        return "\tNow you have " + this.tasks.size() + " task"
                + (this.tasks.size() == 1 ? "" : "s") + " in the list";
    }

    /**
     * Marks a task as done based on its index.
     *
     * @param index The index of the task to be marked as done.
     * @throws DukeException If the index is out of range.
     */
    public void markTask(int index) throws DukeException {
        assert index >= 1 : "Index cannot be less than 1";
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeException("Task index is out of range.");
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsDone();
        currTask.toString();
    }

    /**
     * Unmarks a task (marks as not done) based on its index.
     *
     * @param index The index of the task to be unmarked.
     * @throws DukeException If the index is out of range.
     */
    public void unmarkTask(int index) throws DukeException {
        assert index >= 1 : "Index cannot be less than 1";
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeException("Task index is out of range.");
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsUndone();
        currTask.toString();
    }

    /**
     * Lists all tasks in the task list.
     */
    public String listTasks() {
        if (this.tasks.size() == 0) {
            return "\tThe task list is empty.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("\tHere are the tasks in your list: \n");
            for (int i = 0; i < this.tasks.size(); i++) {
                Task currTask = this.tasks.get(i);
                sb.append("\t" + (i + 1) + "." + currTask.toString());
                sb.append("\n");
            }
            return sb.toString();
        }

    }
    /**
     * Finds task according to input.
     */
    public TaskList findTasks(String word) throws DukeException {
        assert word != null && !word.isEmpty() : "Search word cannot be null or empty";
        Pattern pattern = Pattern.compile(Pattern.quote(word), Pattern.CASE_INSENSITIVE);
        List<Task> foundTasks = new ArrayList<>();
        for (Task task: this.tasks) {
            Matcher matcher = pattern.matcher(task.getName());
            if (matcher.find()) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.size() == 0) {
            throw new DukeException("No tasks with " + word + " found");
        }
        return new TaskList(foundTasks);
    }
}
