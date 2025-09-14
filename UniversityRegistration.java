package DSproject;
import java.util.*;
public class UniversityRegistration {
    static Scanner input= new Scanner(System.in);
    
    static DoublyLinkedList all_students = new DoublyLinkedList();
    static DoublyLinkedList all_courses = new DoublyLinkedList();
    
    static Stack<Triple> undo = new Stack<>();
    static Stack<Triple> redo = new Stack<>();
    
   static void add_student(){
        System.out.print("Enter the student's id: ");
        int id=input.nextInt();
        if(all_students.does_exist(id) != null){
            System.out.println("The student already exists");
        }
        else{
            all_students.add_to_tail(id , false , "add_student");
            System.out.println("The student addedd successfully");
        }
    }
    
    static void add_course(){
        System.out.print("Enter the course id: ");
        int id=input.nextInt();
        if(all_courses.does_exist(id) != null){
            System.out.println("The course already exists");
        }
        else{
            all_courses.add_to_tail(id , false , "add_course");
            System.out.println("The course added successfully");
        }
    }
    
    static void remove_student(){
        System.out.print("Enter student id to be removed: ");
        int Studentid=input.nextInt();
        all_students.remove(Studentid , false , "remove_student");
    }
    
    static void remove_course(){
        System.out.print("Enter course id to be removed: ");
        int courseid=input.nextInt();
        all_courses.remove(courseid , false , "remove_course");
    }
    
    static void get_last_student_added(){
        Node lastStudent=all_students.get_last();
        if(lastStudent!=null){
            System.out.println("The id of last student added: "+lastStudent.ID);
        }
    }
    
    static void get_last_course_added(){
        Node lastCourse=all_courses.get_last();
        if(lastCourse!=null){
            System.out.println("The id of last course added: "+lastCourse.ID);
        }
    }
    
    static void enroll_student(int stuID, int couID ,boolean undo_or_redo){
        Node student = all_students.does_exist(stuID);
        if(student == null){
            System.out.println("Student does not exist.");
            return;
        }
        Node course = all_courses.does_exist(couID);
        if(course == null){
            System.out.println("Course does not exist.");
            return;
        }

        if(student.enrolled.does_exist(couID) != null){
            System.out.println("Student is already enrolled in this course");
            return;
        }

        // Chech if Maximum
        if(course.enrolled.size() >= 30){
            System.out.println("Course is full");
            return;
        }

        // Find position for course
        int couPosition = 0;
        Node couCurrent = student.enrolled.head;
        while(couCurrent != null && couCurrent.ID < couID){
            couCurrent = couCurrent.next;
            couPosition++;
        }
        // Add in Position
        student.enrolled.add_in_position(couID,couPosition);

        int stuPosition = 0;
        Node stuCurrent = course.enrolled.head;
        while(stuCurrent != null && stuCurrent.ID < stuID){
            stuCurrent = stuCurrent.next;
            stuPosition++;
        }
        //Add in position
        course.enrolled.add_in_position(stuID,stuPosition);
        System.out.println("made enrollment successfully");    
        if(!undo_or_redo){
            Triple t = new Triple("enroll", stuID, couID);
            undo.push(t);
            redo.clear();
        }
    }
    
    static void remove_enrollment(int stuID, int couID ,boolean undo_or_redo){
        Node student =all_students.does_exist(stuID);
        Node course = all_courses.does_exist(couID);
        
        if(student == null || course == null){
            System.out.println("Student or Course not found");
            return;
        }

        if(student.enrolled.does_exist(couID) == null){
            System.out.println("Student is not enrolled in this course");
            return;
        }

        student.enrolled.remove(couID, true,"");
        course.enrolled.remove(stuID, true,"");
        System.out.print("Removed enrollment successfully");    
    
        if(!undo_or_redo){
            Triple t = new Triple("remove_enrollment", stuID, couID);
            undo.push(t);
            redo.clear();
        }
    }
    
    static void list_courses_by_student(){
        System.out.print("Enter student ID: ");
        int studentId = input.nextInt();
        
        if (all_students.does_exist(studentId) == null) {
            System.out.println("Student not found");
            return;
        }
    System.out.println("Courses enrolled by student " + studentId + ":");
        Node current = all_students.head;
        while (current != null) {
            if (current.ID == studentId) {
                current.enrolled.list();
                return;
            }
            current = current.next;
        }
    }
    
    static void list_students_by_course(){
        System.out.print("Enter course ID: ");
        int courseId = input.nextInt();
        
        if (all_courses.does_exist(courseId)  == null) {
            System.out.println("Course not found");
            return;
        }
         System.out.println("Students enrolled in course " + courseId + ":");
        Node current = all_courses.head;
        while (current != null) {
            if (current.ID == courseId) {
                current.enrolled.list();
                return;
            }
            current = current.next;
        }
    }
    
    static void is_full_course(){
        System.out.print("Enter course ID to check: ");
    int courseId = input.nextInt();
        if (all_courses.does_exist(courseId)== null) {
        System.out.println("Course not found");
        return;
    }
      Node current = all_courses.head;
    while (current != null) {
        if (current.ID == courseId) {
            
            int Size = current.enrolled.size();
            System.out.println("Course " + courseId + " has " + Size + " students enrolled");
            
            
            if (Size >= 30) {
                System.out.println("This course is FULL");
            } else {
                System.out.println("This course is NOT full");
            }
            return;
         }
        current = current.next;
    }
    }
    
    static void is_normal_student(){
        System.out.print("Enter student ID to check: ");
    int studentId = input.nextInt();
    
    if (all_students.does_exist(studentId) == null) {
        System.out.println("Student not found");
        return;
    }
    Node current = all_students.head;
    while (current != null) {
        if (current.ID == studentId) {
            
            int courseCount = current.enrolled.size();
            System.out.println("Student " + studentId + " is enrolled in " + courseCount + " courses");
            
            
            if (courseCount <= 5) {
                System.out.println("This student is NORMAL");
            } else {
                System.out.println("This student is OVERLOADED");
            }
            return;
             }
        current = current.next;
    }
    }
    
    static void undo(){
        if (undo.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }
        
        switch(undo.peek().operation){
            case "add_student":
                all_students.remove(undo.peek().first_id , true , "");
                redo.push(undo.peek());
                undo.pop();    
                break;
            case "add_course":
                all_courses.remove(undo.peek().first_id , true ,"");
                redo.push(undo.peek());
                undo.pop();    
                break;
            case "remove_student":
                all_students.add_to_tail(undo.peek().first_id , true , "add_student");
                redo.push(undo.peek());
                undo.pop();    
                break;
            case "remove_course":
                all_courses.add_to_tail(undo.peek().first_id , true, "add_course");
                redo.push(undo.peek());
                undo.pop();    
                break;
            case "enroll":
                remove_enrollment(undo.peek().first_id , undo.peek().second_id , true);
                redo.push(undo.peek());
                undo.pop();    
                break;
            case "remove_enrollment":
                enroll_student(undo.peek().first_id , undo.peek().second_id , true);
                redo.push(undo.peek());
                undo.pop();                    
        }
            
    }
    
    static void redo(){
        if (redo.isEmpty()) {
            System.out.println("Nothing to redo.");
            return;
        }
        switch(redo.peek().operation){
            case "add_student":
                all_students.add_to_tail(redo.peek().first_id , true , "add_student");
                undo.push(redo.peek());
                redo.pop();
                break;
            case "add_course":
                all_courses.add_to_tail(redo.peek().first_id , true , "add_course");
                undo.push(redo.peek());
                redo.pop();
                break;
            case "remove_student":
                all_students.remove(redo.peek().first_id , true , "");
                undo.push(redo.peek());
                redo.pop();
                break;
            case "remove_course":
                all_courses.remove(redo.peek().first_id , true , "");
                undo.push(redo.peek());
                redo.pop();
                break;
            case "enroll":
                enroll_student(redo.peek().first_id , redo.peek().second_id , true);
                undo.push(redo.peek());
                redo.pop();   
                break;
            case "remove_enrollment":
                remove_enrollment(redo.peek().first_id , redo.peek().second_id , true);
                undo.push(redo.peek());
                redo.pop();
        }
    }
}