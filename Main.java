package DSproject;
import java.util.Scanner;

public class Main {
    static Scanner input=new Scanner(System.in);
    
    static public void clear_terminal(){
        System.out.println("\u001b[2J"+ "\u001b[H");
    }
    
    public static void print_mainmenu(){
        System.out.println("Welcome to the University Course Registration System.");
        System.out.println("1.  Add a student");
        System.out.println("2.  Add a course");
        System.out.println("3.  Remove a student");
        System.out.println("4.  Remove a course");
        System.out.println("5.  Get the last student added");
        System.out.println("6.  Get the last course added");
        System.out.println("7.  Make an enrollment");
        System.out.println("8.  Remove an enrollment");
        System.out.println("9.  List a student's enrolled courses");
        System.out.println("10. List students enrolled in a course");
        System.out.println("11. List a student's enrolled courses sorted");
        System.out.println("12. List students enrolled in a course sorted");
        System.out.println("13. Check if a course is full or not");
        System.out.println("14. Check if a student is normal or not");
        System.out.println("15. Undo");
        System.out.println("16. Redo");
        System.out.println("17.  Exit");
        System.out.println("Enter your choise: ");
    }
    
    public static void submenu(){
        int sub_choise=0;
        while(sub_choise != 5){
            System.out.println("1.  Return to Main Menu");
            System.out.println("2.  Undo");
            System.out.println("3.  Redo");
            System.out.println("4.  Exit");
            System.out.println("Enter your choise: ");
            sub_choise=input.nextInt();
            clear_terminal();
            
            switch(sub_choise){
                case 1:
                    return;
                case 2:
                    UniversityRegistration.undo();
                    break;
                case 3:
                    UniversityRegistration.redo();
                    break;
                case 4:
                    System.exit(0);
                break;
                default: System.out.println("Invalid Choise (1-4)");
            }
        }
    }
    
    
    public static void main(String[] args) {
        int main_choise=0;
        while(main_choise != 17){
            print_mainmenu();
            main_choise = input.nextInt();
            clear_terminal();
            
            switch(main_choise){
                case 1:
                    UniversityRegistration.add_student();
                    submenu();
                    break;
                case 2:
                    UniversityRegistration.add_course();
                    submenu();
                    break;
                case 3:
                    UniversityRegistration.remove_student();
                    submenu();
                    break;
                case 4:
                    UniversityRegistration.remove_course();
                    submenu();
                    break;
                case 5:
                    UniversityRegistration.get_last_student_added();
                    submenu();
                    break;
                case 6:
                    UniversityRegistration.get_last_course_added();
                    submenu();
                    break;
                case 7:
                    System.out.println("Enter Student ID: ");
                    int stuID = input.nextInt();
                    
                    System.out.println("Enter Course ID: ");
                    int couID = input.nextInt();
                
                    UniversityRegistration.enroll_student(stuID , couID , false);
                    submenu();
                    break;
                case 8:
                    System.out.println("Enter Student ID: ");
                    stuID = input.nextInt();
                
                    System.out.println("Enter Course ID: ");
                    couID = input.nextInt();
                
                    UniversityRegistration.remove_enrollment(stuID , couID , false);
                    submenu();
                    break;
                case 9:
                case 11:
                    UniversityRegistration.list_courses_by_student();
                    submenu();
                    break;
                case 10:
                case 12:
                    UniversityRegistration.list_students_by_course();
                    submenu();
                    break;
                case 13:
                    UniversityRegistration.is_full_course();
                    submenu();
                    break;
                case 14:
                    UniversityRegistration.is_normal_student();
                    submenu();
                    break;
                case 15:
                    UniversityRegistration.undo();
                    submenu();
                    break;
                case 16:
                    UniversityRegistration.redo();
                    submenu();
                    break;
                case 17:
                    return;
                default: System.out.println("Invalid Choise (1-17)");
            }
        }
    }
}