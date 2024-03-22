import java.util.Scanner;
class Task {
    private String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean b) {
        this.completed = b;
    }
}
class TaskList {
    private Task[] task_list;
    private int count=0;
    private int capacity;

    TaskList(int capacity) {
        this.task_list = new Task[capacity];
        this.capacity = capacity;
    }

    void addTask(Task task) {
        if (count < capacity) {
            task_list[count++] = task;
        } else {
            System.out.println("Task Queue is full.");
        }
    }

    void editTask( Scanner scanner) {
        System.out.print("Enter task index to edit: ");
        int index = scanner.nextInt();
        index-=1;
        scanner.nextLine();
        if (index >= 0 && index < capacity &&index<count) {
            System.out.print("Enter new description: ");
            String newDescription = scanner.nextLine();
            task_list[index].setDescription(newDescription);
            System.out.println("Task edited successfully.");
        } 
        else {
            System.out.println("Invalid task index.");
        }
    }

    void completeTask( Scanner scanner) {
      System.out.print("Enter task index that you completed: ");
      int index = scanner.nextInt();
      index-=1;
      scanner.nextLine();
      if (index >= 0 && index < capacity &&index<count) {
          task_list[index].setCompleted(true);
          System.out.println("Congratulation for Task("+task_list[index].getDescription()+") Completion");
      } 
      else {
          System.out.println("Invalid task index.");
      }
    }
    void taskHistory()
    {
      System.out.println("\nTask History //Status Indicates Task Completion");
      System.out.println("-----------------------------------------------------");
      for(int i=0;i<capacity;i++)
      {
        if(count==0)
        {
          System.out.println("No task is present to Show");
          return ;
        }
        if(task_list[i]!=null)
        {
          System.out.println((i+1)+". Task >>> "+task_list[i].getDescription()+" (Status: "+task_list[i].isCompleted()+")");
        }
        
      }
    }
    Task[] getHistory() {
        return task_list;
    }
}

class TaskManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList task_list;
        Task task;

        System.out.println("\nWelcome to Task Manager!");
        System.out.println("--------------------------");
        System.out.print("Enter the number of tasks: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();
        task_list = new TaskList(capacity);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Task");
            System.out.println("2. Edit Task");
            System.out.println("3. Complete Task");
            System.out.println("4. View Task History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Describe the task you want to add: ");
                    String task_des = scanner.nextLine();
                    task=new Task(task_des);
                    task_list.addTask(task);
                    break;
                case 2:
                    task_list.editTask(scanner);
                    break;
                case 3:
                    task_list.completeTask(scanner);
                    break;
                case 4:
                    task_list.taskHistory();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}