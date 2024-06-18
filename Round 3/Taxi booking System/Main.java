import java.util.*;
import javax.swing.DebugGraphics;


public class Main {

    public static void bookTaxi(int id, char pickup, char drop, int pickuptime, List<Taxi> Taxis){
        
        Taxi currentTaxi = null;
        int dist = 1000000;
        int earnings = 100000;
        for(Taxi taxi: Taxis){
            //Check if the taxi is free
            int currDist = Math.abs((taxi.currentPoint - 'A') - (pickup - 'A'));
            if(taxi.freetime <= pickuptime && currDist <= pickuptime - taxi.freetime){
                if(dist > currDist){
                    currentTaxi = taxi;
                    dist = currDist;
                    earnings = taxi.collection;
                }else if(dist == currDist){
                    if(taxi.collection < earnings){
                        currentTaxi = taxi;
                        earnings = taxi.collection;
                    }
                }
            }
        }

        if(currentTaxi == null){
            System.out.println("No taxi found");
            return;
        }

        int distance = Math.abs((pickup - 'A') - (drop - 'A'));

        int newfreetime = pickuptime + distance;
        char newcurrentPoint = drop;
        int newcollection = ((distance*15) - 5) * 10 + 100;

        String temp = "Customer " + id + " booked the Taxi " + currentTaxi.id + " from " + pickup + " to " + drop;
        ;
        currentTaxi.setDetails(newfreetime, newcurrentPoint, newcollection, temp);
        System.out.println("Your Taxi booked Successfully");
        System.out.println("Taxi " + currentTaxi.id + " is alloted");

    }

    public static List<Taxi> CreateTaxi(int n){
        List<Taxi> taxis = new ArrayList<Taxi>();

        for(int i=0;i<n;i++){
            Taxi t = new Taxi();
            taxis.add(t);
        }
        return taxis;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Taxi> Taxis = CreateTaxi(4);

        // for(Taxi taxi: Taxis){
        //     System.out.print(taxi.id + " ");
        // }
        
        boolean isTrue = true;
        while(isTrue){

            System.out.println(" 1. Book Taxi\n 2. Print Customer Details\n 3.Print Taxi Details\n 4. Exit");
            int choice = sc.nextInt();
            
            switch(choice){
                case 1:{
                    int id;
                    char pickup, drop;
                    int pickuptime;
                    
                    System.out.print("Enter id: ");
                    id = sc.nextInt();
                    System.out.print("Enter pickup spot: ");
                    pickup = sc.next().charAt(0);
                    System.out.print("Enter drop spot: ");
                    drop = sc.next().charAt(0);
                    System.out.print("Enter timing: ");
                    pickuptime = sc.nextInt();

                    bookTaxi(id, pickup, drop, pickuptime, Taxis);
                    break;
                }
                case 2:{
                    int id;
                    System.out.print("Enter id : ");
                    id = sc.nextInt();
                    
                    Taxi currTaxi = null;
                    for(Taxi taxi : Taxis){
                        if(taxi.id == id){
                            currTaxi = taxi;
                            break;
                        }
                    }
                    if(currTaxi == null){
                        System.out.println("Ther is no Taxi with this id.");
                        break;
                    }
                    
                    currTaxi.printDetails();
                    break;
                }
                case 3:{
                    int id;
                    System.out.print("Enter id : ");
                    id = sc.nextInt();

                    Taxi currTaxi = null;
                    for(Taxi taxi : Taxis){
                        if(taxi.id == id){
                            currTaxi = taxi;
                            break;
                        }
                    }
                    if(currTaxi == null) System.out.println("Current Taxi id is not found.");
                    currTaxi.printTaxiDetails();
                    break;
                }
                case 4:{
                    isTrue = false;
                    break;
                }
                default:
                    System.out.println("Invalid choice. Please Enter a valid choice!");
                    break;
            }
        }
    }
}