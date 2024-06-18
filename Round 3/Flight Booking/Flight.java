import java.util.*;
public class Flight
{
    // id for flight which is alloted everytime a new Flight Object is created
    static int id = 0;
    int flightID;
    //Availabel number of tickets currently in flight
    int tickets;
    //current price
    int price;
    //string list of all passenger details for printing
    ArrayList<String> passengerDetails;
    //list of all passenger IDs
    ArrayList<Integer> passengerIDs;
    //list of number of tickets booked by every passenger ID
    ArrayList<Integer> bookedTicketsPerPassenger;
    //list of cost paid by every passenger ID, used to calculate refund while cancelling
    ArrayList<Integer> passengerCost;

    //constructor to set values
    public Flight()
    {
        tickets = 50;
        price = 5000;
        id = id + 1;
        flightID = id;
        passengerDetails = new ArrayList<String>();
        passengerIDs = new ArrayList<Integer>();
        bookedTicketsPerPassenger = new ArrayList<Integer>();
        passengerCost = new ArrayList<Integer>();
    }

    //add passenger details to flight
    public void addPassengerDetails(String passengerDetail,int numberOfTickets,int passengerID)
    {
        passengerDetails.add(passengerDetail);
        passengerIDs.add(passengerID);
        passengerCost.add(price * numberOfTickets);

        //updating price using logic in the problem statement
        price+=200 * numberOfTickets;

        //updating available number of tickets
        tickets-= numberOfTickets;
        bookedTicketsPerPassenger.add(numberOfTickets);
        System.out.println("Booked Successfully!");

    }

    //cancel tickets booked by a passenger ID
    public void cancelTicket(int passengerID)
    {
        //find the index to remove from all lists
       int indexToRemove = passengerIDs.indexOf(passengerID);
       if(indexToRemove < 0)
       {
           System.out.println("Passenger ID not found!");
           return;
       }
       int ticketsToCancel = bookedTicketsPerPassenger.get(indexToRemove);

       //increase number of available tickets
       tickets+=ticketsToCancel;
       //change price to new value after cancellation
       price-= 200 * ticketsToCancel;

       //calculate refund
       System.out.println("Refund Amount after cancel : " + passengerCost.get(indexToRemove));

       //remove details of passenger from all lists
       bookedTicketsPerPassenger.remove(indexToRemove);
       passengerIDs.remove(Integer.valueOf(passengerID));
       passengerDetails.remove(indexToRemove);
       passengerCost.remove(indexToRemove);

       System.out.println("Cancelled Booked Tickets Successfully!");

    }

    //functions to print details about flights and passengers
    public void flightSummary()
    {
        System.out.println("Flight ID " + flightID + " --" + "Remaining Tickets " + tickets + " --" + 
        "Current Ticket Price " + price);
    }
    public void printDetails()
    {
       System.out.println("Flight ID " + flightID + "->");
        for(String detail : passengerDetails)
            System.out.println(detail);
    }


}
