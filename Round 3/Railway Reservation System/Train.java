import java.util.*;

public class Train {

    int TrainID;
    int availableSeats;
    int waitingList;
    ArrayList<Integer> customerIDs;
    ArrayList<String> status;
    ArrayList<Integer> BookedTickets;

    public Train(int id) {
        TrainID = id;
        availableSeats = 60;
        waitingList = 10;
        customerIDs = new ArrayList<Integer>();
        status = new ArrayList<String>();
        BookedTickets = new ArrayList<Integer>();
    }

    public void bookTicket(int Cusid, int tickets){
        if(availableSeats + waitingList < tickets){
            System.out.println("Not available that many tickets.");
            return;
        }
        else if(availableSeats < tickets){
            System.out.println("Not enough tickets");
            char waitingTicket = 'Y';
            System.out.print("Do you want to book tickets in waitingList? (Y/N)");
            // waitingTicket = .next().charAt(0);
            if(waitingTicket == 'Y'){
                tickets -= availableSeats;
                waitingList -= tickets;

                System.out.println(availableSeats + " Tickets confimed " + tickets+ " are in waiting List.");
                customerIDs.add(Cusid);
                String details = Cusid + " Booked " + availableSeats + " tickets and " + tickets+ " waiting List tickets.";
                status.add(details);
                BookedTickets.add(tickets);
                availableSeats = 0;
            }else{
                System.out.println("You can't book tickets.");
                return;
            }
        }
        else{
            availableSeats -= tickets;
            customerIDs.add(Cusid);
            String details = "Customer " + Cusid + " Booked " + tickets + " tickets.";
            status.add(details);
            BookedTickets.add(tickets);
        }
        System.out.println("Your tickets are Successfully booked.");
        
    }

    public void cancelTicket(int Cusid, int tickets){
        int index = customerIDs.indexOf(Cusid);
        int totalBookings = BookedTickets.get(index);
        if(totalBookings < tickets){
            System.out.println("You only booked " + totalBookings + " Tickets");
            return;
        }
        if(availableSeats + tickets  <= 60){
            availableSeats += tickets;
            BookedTickets.set(index, totalBookings - tickets);
            String details = Cusid + " Cancelled " + tickets + " tickets.";
            status.add(details);
        }else{
            waitingList = tickets - (60 - availableSeats);
            availableSeats = 60;
            BookedTickets.set(index, totalBookings - tickets);
            String details = "Customer " + Cusid + " Cancelled " + tickets + " tickets.";
            status.add(details);
        }            
        System.out.println("Your tickets are Successfully Cancelled.");

    }

    
    public void showTrainStatus(){
        System.out.println("Booking Status: ");
        System.out.println("--------------------------------");
        for(int i=0;i<customerIDs.size();i++){
            System.out.println(status.get(i));
        }
        System.out.println("--------------------------------");
    }

}