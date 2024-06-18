import java.util.*;


public class Booking {


    public static void BookTickets(int Cusid, int tickets, Train currentTrain){
        currentTrain.bookTicket(Cusid, tickets);
    }

    public static void cancelTickets(int Cusid, int tickets, Train currentTrain){
        currentTrain.cancelTicket(Cusid, tickets);
    }

    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Train> trainList = new ArrayList<>();

        System.out.println("Enter Number of Trains");
        int n = sc.nextInt();
        
        for(int i=0;i<n;i++){
            System.out.println("Enter Train Number or ID");
            int id = sc.nextInt();
            trainList.add(new Train(id));
        }

        boolean condition = true;
        int Pid, Tid;
        while(condition){
            System.out.println("1. Book Tickets \n 2. Cancel Tickets \n 3.Show Status of the Train \n 4. Quit");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                {
                    System.out.printf(" Enter passenger id : ");
                    Pid = sc.nextInt();
                    System.out.printf("Enter Train id : ");
                    Tid = sc.nextInt();

                    Train currentTrain = null;
                    for(Train train: trainList){
                        if(train.TrainID == Tid){
                            currentTrain = train;
                            break;
                        }
                    }
                    if(currentTrain == null){
                        System.out.println(" No train found in the given ID.");
                        return;
                    }
                    System.out.println("Number of tickets you want to book");
                    int tickets = sc.nextInt();

                    if(tickets > currentTrain.availableSeats + currentTrain.waitingList){
                        System.out.println("Not enough tickets in the current train");
                        return;
                    }

                    BookTickets(Pid, tickets, currentTrain);
                    break;
                }
                case 2:
                    {
                        System.out.printf(" Enter passenger id : ");
                        Pid = sc.nextInt();
                        System.out.printf("Enter Train id : ");
                        Tid = sc.nextInt();

                        Train currentTrain = null;
                        for(Train train: trainList){
                            if(train.TrainID == Tid){
                                currentTrain = train;
                                break;
                            }
                        }
                        if(currentTrain == null){
                            System.out.println(" No train found in the given ID.");
                            return;
                        }
                        System.out.println("Number of tickets you want to Cancel");
                        int tickets = sc.nextInt();

                        if(tickets > currentTrain.availableSeats + currentTrain.waitingList){
                            System.out.println("Not enough tickets in the current train");
                            return;
                        }

                        cancelTickets(Pid, tickets, currentTrain);
                        break;
                    }

                case 3:
                    {
                        System.out.println("Enter train ID to see the status.");
                        Tid = sc.nextInt();
                        Train currentTrain = null;
                        for(Train train : trainList){
                            if(train.TrainID == Tid){
                                currentTrain = train;
                                break;
                            }
                        }
                        
                        if(currentTrain == null){
                            System.out.println("The current train id is not found");
                            return;
                        }

                        currentTrain.showTrainStatus();
                        break;
                    }
                case 4:
                    condition = false;
                    break;
            }
        }
        
    }
}