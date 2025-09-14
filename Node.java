package DSproject;
public class Node {
    int ID;
    DoublyLinkedList enrolled = new DoublyLinkedList();
    Node next;
    Node prev;
    
    public Node(int id ){
        ID=id;
    }
}