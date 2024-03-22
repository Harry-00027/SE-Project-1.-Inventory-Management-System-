import java.util.Scanner;

class Student {
  private String name;
  private int roll;

  public Student(String name, int roll) {
      this.name = name;
      this.roll = roll;
  }

  String getName() {
      return name;
  }

  int getRoll() {
      return roll;
  }
}

class CentralDatabase {
  private Student[] students;
  private String[] subjects={"CN","SE","GraphTheory","OOS"};
  private int[][] marks;
  int noOfStudent;
  int count=0; 

  CentralDatabase(int noOfStudent) {
      students=new Student[noOfStudent];
      this.noOfStudent=noOfStudent;
      marks=new int[noOfStudent][4];
  }
  void addStudent(Student S)
  {
    if(count>noOfStudent)
    {
      System.out.println("Array Exceed!! New Students will not Add");
    }
    students[count++]=S;
  }

  void addMarks(Scanner scanner) {
      for(int i=0;i<noOfStudent;i++)
      {
        System.out.println("Adding Marks for Student "+students[i].getName());
        for(int j=0;j<4;j++)
        {
          System.out.print("Enter marks for subject " + subjects[j] + ": ");
          int m = scanner.nextInt();
          marks[i][j]=m;
        }
      }
      System.out.println("All Marks Added Successfully");
  }

  void viewMarks() 
  {
    for(int i=0;i<noOfStudent;i++)
      {
        System.out.println("marks for Student "+students[i].getName());
        for(int j=0;j<4;j++)
        {
          System.out.println(" marks for subject " + subjects[j] + ": "+marks[i][j]);
        }
      }
  }
  void updateMark(Scanner scanner, int roll,String sub) {
    for(int i=0;i<noOfStudent;i++)
    {
      if(students[i].getRoll()==roll)
      {
        for(int j=0;j<4;j++)
        {
          if(subjects[j].equalsIgnoreCase(sub)){
            System.out.print("Enter new marks for subject " + subjects[j] + ": ");
            int m = scanner.nextInt();
            marks[i][j]=m;
          }
        }
        break;
      }
    }
    System.out.println("You are not authorise to change the marks of the student ");
  }
  void publishResult() {
    int[] totalMarks = new int[noOfStudent];
    for (int i = 0; i < noOfStudent; i++) {
        for (int j=0;j<4;j++) {
            totalMarks[i] += marks[i][j];
        }
    }

    // Here I use Bubble Sort
    for (int i = 0; i < noOfStudent - 1; i++) {
        for (int j = 0; j < noOfStudent - i - 1; j++) {
            if (totalMarks[j] < totalMarks[j + 1]) {
                // Swap total marks
                int tempMarks = totalMarks[j];
                totalMarks[j] = totalMarks[j + 1];
                totalMarks[j + 1] = tempMarks;

                // Swap student all value
                Student tempName = students[j];
                students[j] = students[j + 1];
                students[j + 1] = tempName;
            }
        }
    }

    System.out.println("\nFinal Result (Sorted by Total Marks):");
    System.out.println("-----------------------------------------");
    for (int i = 0; i < noOfStudent; i++) {
        System.out.println("Name: " + students[i].getName() + " (Roll Number: " + students[i].getRoll() + ")");
        System.out.println("Total Marks: " + totalMarks[i] + "\n");
    }
}

}

class StudentMarksManagementSystem
{
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the number of students: ");
    int numStudents = scanner.nextInt();
    CentralDatabase CN=new CentralDatabase(numStudents);

    for (int i = 0; i < numStudents; i++) {
        scanner.nextLine();
        System.out.print("Enter name for student " + (i + 1) + ": ");
        String studentNames = scanner.nextLine();
        System.out.print("Enter roll number for student " + (i + 1) + ": ");
        int rollNumbers = scanner.nextInt();
        Student students=new Student(studentNames, rollNumbers);
        CN.addStudent(students);
    }


    while (true) {
        System.out.println("\nMenu:");
        System.out.println("1. Add Marks(Teacher)");
        System.out.println("2. View Marks(Teacher)");
        System.out.println("3. Update Marks(Teacher)");
        System.out.println("4. Result(Student)");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                CN.addMarks(scanner);
            case 2:
                CN.viewMarks();
                break;
            case 3:
                System.out.print("Enter the Subject Name you teach: ");
                String subject = scanner.nextLine();
                System.out.print("Enter the roll number of students you want to update marks: ");
                int roll = scanner.nextInt();
                CN.updateMark(scanner, roll,subject);
                break;
            case 4:
                CN.publishResult();
                break;
            case 5:
                scanner.close();
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
}
