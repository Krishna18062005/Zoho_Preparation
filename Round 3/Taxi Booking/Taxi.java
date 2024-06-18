import java.util.*;
public class Taxi
{

     static int taxicount = 0; // taxi number
     int id;
     boolean booked; //taxi booked or not
     char currentSpot; //where taxi is now
     int freeTime; // when taxi becomes free
     int totalEarnings; // total earnings of taxi
     List<String> trips; // all details of all trips by this taxi
     
    
    public Taxi()
    {
        booked = false;
        currentSpot = 'A';//start point A
        freeTime = 6;//example 6 AM
        totalEarnings = 0;
        trips = new ArrayList<String>();
        taxicount = taxicount + 1; // everytime new taxi is created a new id will be assigned
        id = taxicount;
    }

    public void setDetails(boolean booked,char currentSpot,int freeTime,int totalEarnings,String tripDetail)
    {
            this.booked = booked;
            this.currentSpot = currentSpot;
            this.freeTime = freeTime;
            this.totalEarnings = totalEarnings;
            this.trips.add(tripDetail);
    }

    public void printDetails()
    {
        //print all trips details
        System.out.println("Taxi - "+ this.id + " Total Earnings - " + this.totalEarnings);
        System.out.println("TaxiID    BookingID    CustomerID    From    To    PickupTime    DropTime    Amount");
        for(String trip : trips)
        {
            System.out.println(id + "          " + trip);
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    public void printTaxiDetails()
    {
        //print total earningand taxi details like current location and free time
        System.out.println("Taxi - "+ this.id + " Total Earnings - " + this.totalEarnings + " Current spot - " + this.currentSpot +" Free Time - " + this.freeTime);
    }
    
    
}
