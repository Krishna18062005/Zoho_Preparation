import java.util.*;

public class Taxi {

    int id;
    static int currID = 1;
    int freetime;
    char currentPoint;
    int collection;
    static List<String> details;

    public Taxi(){
        id = currID;
        currID += 1;
        freetime = 5;
        currentPoint = 'A';
        int collection = 0;
        details = new ArrayList<String>();
    }

    public void setDetails(int newfreetime, char newcurrentPoint, int newcollection, String detail){
        freetime = newfreetime;
        currentPoint = newcurrentPoint;
        collection += newcollection;
        details.add(detail);
    }

    public static void printDetails(){
        System.out.println("Taxi details : ");
        for(int i=0; i<details.size(); i++){
            System.out.println(details.get(i));
        }
    }

    public void printTaxiDetails(){
        System.out.println("Taxi details : ");
        System.out.println("Taxi id : " + id);
        System.out.println("Taxi freetime : " + freetime);
        System.out.println("Taxi earnings : " + collection);
        System.out.println("Taxi current Point : " + currentPoint);
        
    }

}