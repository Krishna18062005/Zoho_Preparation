import java.util.*;
public class BookTicket
{
    // function to book tickets in flight
    public static void book(Flight currentFlight, int tickets,int passengerID)
    {
       String passengerDetail = "";
       // create detail about the passenger with the ID
       passengerDetail = "Passenger ID " + passengerID + " -- " + " Number of Tickets Booked " 
                            + tickets + " -- " + "Total cost " + currentFlight.price * tickets;
       //add passenger detail to flight object
       currentFlight.addPassengerDetails(passengerDetail,tickets,passengerID);

       currentFlight.flightSummary();
       currentFlight.printDetails();

    }
    //function to cancel the booked tickets by the particular passenger
    public static void cancel(Flight currentFlight, int passengerID)
    {
        // calling cancel function on the flight object
        currentFlight.cancelTicket(passengerID);
        currentFlight.flightSummary();
        currentFlight.printDetails();
    }

    //function to print flightdetails
    public static void print(Flight f)
    {
        f.printDetails();
    }
    public static void main(String[] args)
    {
        //create 2 flights for now with id 1 and 2
        ArrayList<Flight> flights = new ArrayList<Flight>();
        for(int i=0;i<2;i++)
            flights.add(new Flight());

        // unique ID for passenger allotted during every booking
        int passengerID = 1;

        // get choice from user
        while(true)
        {
        System.out.println("1. Book 2. Cancel 3. Print");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        

        //based on choice call different functions
        switch(choice)
        {
            //book
            case 1:
            {
                System.out.println("Enter Flight ID");
                int fid = sc.nextInt();

                //check if flight id is valid
                if(fid > flights.size())
                {
                    System.out.println("Invalid flight ID");
                    break;
                }
                //find the corresponding flight
                Flight currentFlight = null;
                for(Flight f : flights)
                {
                    if(f.flightID == fid)
                    {
                        currentFlight = f;
                        f.flightSummary();
                        break;
                    }
                }

                System.out.println("Enter number of tickets");
                int t = sc.nextInt();

                //check if availabilty is there
                if(t > currentFlight.tickets)
                {
                    System.out.println("Not Enough Tickets");
                    break;
                }
                // call book function to book tickets
                book(currentFlight,t,passengerID);

                //increment passenger ID so that next booking will have different passenger ID value
                passengerID = passengerID + 1;
                break;
            }
            //cancel
            case 2:
            {
                System.out.println("Enter flight ID and passenger ID to cancel booking");
                int fid = sc.nextInt();

                //check if flight id is valid
                if(fid > flights.size())
                {
                    System.out.println("Invalid flight ID");
                    break;
                }
                //find the corresponding flight
                Flight currentFlight = null;
                for(Flight f : flights)
                {
                    if(f.flightID == fid)
                    {
                        currentFlight = f;
                        break;
                    }
                }
                //get passengerID from passenger to find the booking
                int id = sc.nextInt();

                //call the cancel Booking
                cancel(currentFlight,id);
                break;
            }
            //print details of flight along with passenger details
            case 3:
            {
                //loop though all available flights
                for(Flight f : flights)
                {
                        //check if flight has atleast 1 passenger detail
                        if(f.passengerDetails.size() == 0)
                        {
                            System.out.println("No passenger Details for  - Flight " + f.flightID);
                         
                        }
                        else
                         print(f);
                }
                   
                break;
            }
            default:
            {
                break;
            }
        }
        }

    }
}
