package DSproject;
public class Triple {
    String operation;
    int first_id;
    int second_id;
    
    public Triple(String operation ,int id ){
        this.operation=operation;
        first_id=id;
    }
    public Triple(String operation , int first_id , int second_id ){
        this.operation=operation;
        this.first_id=first_id;
        this.second_id=second_id;
    }
}