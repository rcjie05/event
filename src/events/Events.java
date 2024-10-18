package events;

import java.util.Scanner;

public class Events {
    
   
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String resp;
        do{

            System.out.println("1. ADD");
            System.out.println("2. VIEW");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. EXIT");

            System.out.print("Enter Action: ");
            int action = sc.nextInt();
            Events test = new Events();
            switch(action){
                case 1:
                    test.addEvents();
                break;
                case 2:
                    test.viewEvents();
                break;
                case 3:
                    test.viewEvents();
                    test.updateEvents();
                break;
                case 4:
                    test.viewEvents();
                    test.deleteEvents();
                    test.viewEvents();
                break;
            }
            
            System.out.print("Continue? ");
            resp = sc.next();

        }while(resp.equalsIgnoreCase("yes"));
            System.out.println("Thank You!");

    }
    
    public void addEvents(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Event Id: ");
        String eid = sc.nextLine();
        System.out.print("Event Name: ");
        String ename = sc.nextLine();
        System.out.print("Event Date: ");
        String edate = sc.nextLine();
        System.out.print("Event Location: ");
        String elocal = sc.nextLine();
        System.out.print("Description:");
        String edes = sc.nextLine();
        System.out.print("Organizer Name:");
        String eorga = sc.nextLine();

        String sql = "INSERT INTO tbl_events (event_id, event_name, event_date, locations, descriptions, organizers) VALUES (?, ?, ?, ?, ?, ?)";


        conf.addEvents(sql, eid, ename, edate, elocal, edes, eorga);
    }
    
    private void viewEvents() {
        
        String qry = "SELECT * FROM tbl_events";
        String[] hdrs = {"ID", "Event Name", "Event Date", "Location", "Description", "Organizer"};
        String[] clms = {"event_id", "event_name", "event_date", "locations", "descriptions", "organizers"};

        config conf = new config();
        conf.viewEvents(qry, hdrs, clms);
    }
    
    private void updateEvents() {

    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the ID to Update: ");
    String eid = sc.nextLine();

    System.out.print("Enter New Event Name: ");
    String ename = sc.nextLine();
    System.out.print("Enter New Event Date: ");
    String edate = sc.nextLine();
    System.out.print("Enter New Location: ");
    String elocal = sc.nextLine();
    System.out.print("Enter New Description: ");
    String edes = sc.nextLine();
    System.out.print("Enter New Organizer Name: ");
    String eorga = sc.nextLine();

    String qry = "UPDATE tbl_events SET event_name = ?, event_date = ?, locations = ?, descriptions = ?, organizers = ? WHERE event_id = ?";

    config conf = new config();
    conf.updateEvents(qry, ename, edate, elocal, edes, eorga, eid); 
}
    
    private void deleteEvents() {

    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the ID to Delete: ");
    String id = sc.nextLine(); 

    String qry = "DELETE FROM tbl_events WHERE event_id = ?";

    config conf = new config();
    conf.deleteEvents(qry, id);
}
}
