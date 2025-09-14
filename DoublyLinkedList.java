package DSproject;
public class DoublyLinkedList{
    Node head;
    Node tail;
    int Count=0;
    
    public DoublyLinkedList(){
        head=tail=null;
    }
    
    int size(){
        return Count;
    }
    
    Node does_exist(int id){
         Node current = head;
        while (current != null) {
            if (current.ID == id) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
    
    void add_to_tail(int id , boolean undo_or_redo , String added){
       Node newNode = new Node(id);
        if(tail!=null){
            tail.next=newNode;
            newNode.prev=tail;
            tail=newNode;
        }else{
            head=tail=newNode;
        }
        Count++;
        if(!undo_or_redo){
            Triple t= new Triple(added,id);
            UniversityRegistration.undo.push(t);
            UniversityRegistration.redo.clear();
        }
    }
    
   void remove(int id , boolean undo_or_redo , String removed) {
        Node current = head;

        while (current != null) {
            if (current.ID == id) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                
                Count--;
                System.out.println("Removed item with ID: " + id);
                
                if(!undo_or_redo){
                    Triple t = new Triple(removed,id);
                    UniversityRegistration.undo.push(t);
                    UniversityRegistration.redo.clear();
                }
                return;
            }
            
            current = current.next;
        }

        System.out.println("Item with ID " + id + " not found.");
    }
    
    Node get_last(){
        if(tail!=null){
            return tail;
        }
        else{
            System.out.println("The List is empty");
            return null;
        }
    }
    
    void add_in_position(int id, int position){
        Node NEW = new Node(id);

        if(position == 0){
            if(head==null){ 
                head = tail = NEW;
                Count++;
            }
            else{
                NEW.next = head;
                head.prev = NEW;
                head = NEW;
                Count++;
            }
        }else if(position == size()){
            add_to_tail(id,true , "");
        }else{
            Node Current = head;
            for(int i = 0; i<position - 1; i++){
                Current = Current.next;
            }
            NEW.next = Current.next;
            NEW.prev = Current;
            Current.next.prev = NEW;
            Current.next = NEW;
            Count++;
        }
    }
    
    void list(){
        if (head == null) {
            System.out.println("The list is empty");
            return;
        }

        Node current = head;
        System.out.print("List: ");
        while (current != null) {
            System.out.print(current.ID + " ");
            current = current.next;
        }
        System.out.println();
    }
}