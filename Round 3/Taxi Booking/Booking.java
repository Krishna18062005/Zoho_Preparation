/*
The are 6 points(A,B,C,D,E,F) 15 KM apart 60 min travel between each, n taxis all taxis at A starting
100 rs for first 5 KM and then 10 for each of the further KMs, rate from pickup to drop only
pickup time example : 9 hrs, 15 hrs

When a customer books a Taxi, a free taxi at that point is allocated
-If no free taxi is available at that point, a free taxi at the nearest point is allocated.
-If two taxi’s are free at the same point, one with lower earning is allocated
-If no taxi is free at that time, booking is rejected


Input 1:
Customer ID: 1
Pickup Point: A
Drop Point: B
Pickup Time: 9

Output 1:
Taxi can be allotted.
Taxi-1 is allotted

*/
import java.util.*;
public class Booking
{
    public static void bookTaxi(int customerID,char pickupPoint,char dropPoint,int pickupTime,List<Taxi> freeTaxis)
    {
        // to find nearest
        int min = 999;

        //distance between pickup and drop
        int distanceBetweenpickUpandDrop = 0;

        //this trip earning
        int earning = 0;

        //when taxi will be free next
        int nextfreeTime = 0;

        //where taxi is after trip is over
        char nextSpot = 'Z';

        //booked taxi
        Taxi bookedTaxi = null;

        //all details of current trip as string
        String tripDetail = "";
        
        for(Taxi t : freeTaxis)
        {
            int distanceBetweenCustomerAndTaxi = Math.abs((t.currentSpot - '0') - (pickupPoint - '0')) * 15;
            if(distanceBetweenCustomerAndTaxi < min)
            {
                bookedTaxi = t;
                //distance between pickup and drop = (drop - pickup) * 15KM
                distanceBetweenpickUpandDrop = Math.abs((dropPoint - '0') - (pickupPoint - '0')) * 15;
                //trip earning = 100 + (distanceBetweenpickUpandDrop-5) * 10
                earning = (distanceBetweenpickUpandDrop-5) * 10 + 100;
                
                //drop time calculation
                int dropTime  = pickupTime + distanceBetweenpickUpandDrop/15;
                
                //when taxi will be free next
                nextfreeTime = dropTime;

                //taxi will be at drop point after trip
                nextSpot = dropPoint;

                // creating trip detail
                tripDetail = customerID + "               " + customerID + "          " + pickupPoint +  "      " + dropPoint + "       " + pickupTime + "          " +dropTime + "           " + earning;
                min = distanceBetweenCustomerAndTaxi;
            }
            
        }

        //setting corresponding details to allotted taxi
        bookedTaxi.setDetails(true,nextSpot,nextfreeTime,bookedTaxi.totalEarnings + earning,tripDetail);
        //BOOKED SUCCESSFULLY
        System.out.println("Taxi " + bookedTaxi.id + " booked");

    }

    public static List<Taxi> createTaxis(int n)
    {
        List<Taxi> taxis = new ArrayList<Taxi>();
        // create taxis
        for(int i=1 ;i <=n;i++)
        {
            Taxi t = new Taxi();
            taxis.add(t);
        }
        return taxis;
    }

    public static List<Taxi> getFreeTaxis(List<Taxi> taxis,int pickupTime,char pickupPoint)
    {
        List<Taxi> freeTaxis = new ArrayList<Taxi>();
        for(Taxi t : taxis)
        {   
            //taxi should be free
            //taxi should have enough time to reach customer before pickuptime
            if(t.freeTime <= pickupTime && (Math.abs((t.currentSpot - '0') - (pickupPoint - '0')) <= pickupTime - t.freeTime))
            freeTaxis.add(t);

        }
        return freeTaxis;
    }


    public static void main(String[] args)
    {

        //create 4 taxis
        List<Taxi> taxis = createTaxis(4);

        Scanner s = new Scanner(System.in);
        int id = 1;

        while(true)
        {
            System.out.println("0 - > Book Taxi");
            System.out.println("1 - > Print Taxi details");
            int choice = s.nextInt();
            switch(choice)
            {
                case 0:
                {
                //get details from customers

                int customerID = id;
                System.out.println("Enter Pickup point");
                char pickupPoint = s.next().charAt(0);
                System.out.println("Enter Drop point");
                char dropPoint = s.next().charAt(0);
                System.out.println("Enter Pickup time");
                int pickupTime = s.nextInt();

                //check if pickup and drop points are valid
                if(pickupPoint < 'A' || dropPoint > 'F' || pickupPoint > 'F' || dropPoint < 'A')
                {
                    System.out.println("Valid pickup and drop are A, B, C, D, E, F. Exitting");
                    return;
                }
                // get all free taxis that can reach customer on or before pickup time
                List<Taxi> freeTaxis = getFreeTaxis(taxis,pickupTime,pickupPoint);

                //no free taxi means we cannot allot, exit!
                if(freeTaxis.size() == 0)
                {
                    System.out.println("No Taxi can be alloted. Exitting");
                    return;
                }

                //sort taxis based on earnings
                Collections.sort(freeTaxis,(a,b)->a.totalEarnings - b.totalEarnings);
                // 3,4,2 - > 2,3,4

                //get free Taxi nearest to us
                bookTaxi(id,pickupPoint,dropPoint,pickupTime,freeTaxis);
                id++;
                break;
            }
            case 1:
            {
                //two functions to print details
                 for(Taxi t : taxis)
                    t.printTaxiDetails();
                 for(Taxi t : taxis)
                    t.printDetails();
                break;
            }
            default:
                return;
            }

        }
       
    }
}
