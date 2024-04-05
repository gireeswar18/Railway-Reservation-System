import java.util.*;

public class TicketBooking {

    static int aLB = 2;
    static int aMB = 2;
    static int aUB = 2;
    static int aRAC = 2;
    static int aWL = 2;

    // Seat Number
    static List<Integer> lb_pos = new ArrayList<Integer>(Arrays.asList(1, 2));
    static List<Integer> mb_pos = new ArrayList<Integer>(Arrays.asList(1, 2));
    static List<Integer> ub_pos = new ArrayList<Integer>(Arrays.asList(1, 2));
    static List<Integer> rac_pos = new ArrayList<Integer>(Arrays.asList(1, 2));
    static List<Integer> wl_pos = new ArrayList<Integer>(Arrays.asList(1, 2));

    // Booked List
    static Queue<Integer> rac_list = new LinkedList<Integer>(); // ID
    static Queue<Integer> wl_list = new LinkedList<Integer>(); // ID
    static List<Integer> confirm_list = new ArrayList<Integer>(); // passengerID

    // Passenger Data using their id
    static Map<Integer, Passenger> passenger_data = new HashMap<Integer, Passenger>();

    public void bookTicket (Passenger p, int seatno, String allotted) {
        p.number = seatno;
        p.allotted = allotted;

        // Save data in HashMap
        passenger_data.put(p.passengerID, p);

        // Ticket Confirmed
        confirm_list.add(p.passengerID);

        // Print Booked Details
        System.out.println("Passenger ID: " + p.passengerID);
        System.out.println("Passenger Name: " + p.name);
        System.out.println("Passenger Age: " + p.age);
        System.out.println("Passenger Gender: " + p.gender);
        System.out.println("Passenger Allotted Seat Number: " + seatno + allotted);
        System.out.println("--------------------------------------Booked Successfully!");

    }

    public void book_rac (Passenger p, int seatno, String racBerth) {
        p.number = seatno;
        p.allotted = racBerth;

        passenger_data.put(p.passengerID, p);

        rac_list.add(p.passengerID);

        System.out.println("Passenger ID: " + p.passengerID);
        System.out.println("Passenger Name: " + p.name);
        System.out.println("Passenger Age: " + p.age);
        System.out.println("Passenger Gender: " + p.gender);
        System.out.println("Passenger Allotted Seat Number: " + seatno + racBerth);
        System.out.println("--------------------------------------RAC Berth Given");
    }

    public void book_wl (Passenger p, int seatno, String waitingBerth) {
        p.number = seatno;
        p.allotted = waitingBerth;

        passenger_data.put(p.passengerID, p);

        wl_list.add(p.passengerID);

        System.out.println("Passenger ID: " + p.passengerID);
        System.out.println("Passenger Name: " + p.name);
        System.out.println("Passenger Age: " + p.age);
        System.out.println("Passenger Gender: " + p.gender);
        System.out.println("Passenger Allotted Seat Number: " + seatno + waitingBerth);
        System.out.println("--------------------------------------Waiting List Berth Given");
    }


    public void booked_Tickets_list () {
        if (passenger_data.isEmpty()) {
            System.out.println("No Tickets Booked Yet!");
        }

        else {
            System.out.println("Booked Tickets List: ");
            for (Passenger p : passenger_data.values())
            {
                System.out.println("Passenger ID: " + p.passengerID);
                System.out.println("Passenger Name: " + p.name);
                System.out.println("Passenger Age: " + p.age);
                System.out.println("Passenger Gender: " + p.gender);
                if (!p.cname.equals("null")) {
                    System.out.println("Child Name: " + p.cname);
                    System.out.println("Child Age: " + p.cage);
                }
                System.out.println("Passenger Allotted Seat Number: " + p.number + p.allotted);
                System.out.println("===========================================");
            }
        }
    }

    public void cancel_ticket (int pid) {
            Passenger p = passenger_data.get(pid);
            int allotted_seat = p.number;
            passenger_data.remove(pid);
            confirm_list.remove(Integer.valueOf(pid));
            System.out.println("--------------------------------------Cancelled Successfully!");

            if (p.allotted.equals("L"))
            {
                lb_pos.add(allotted_seat);
                aLB++;
            }
            else if (p.allotted.equals("M"))
            {
                mb_pos.add(allotted_seat);
                aMB++;
            }
            else if (p.allotted.equals("U"))
            {
                ub_pos.add(allotted_seat);
                aUB++;
            }

            if (!rac_list.isEmpty())
            {
                Passenger rac_passenger = passenger_data.get(rac_list.poll()); // First Passenger
                int rac_p_number = rac_passenger.number; // 1
                rac_pos.add(rac_p_number); // 1
                rac_list.remove(rac_passenger.passengerID);
                aRAC++;

                if (!wl_list.isEmpty())
                {
                    Passenger wl_passenger = passenger_data.get(wl_list.poll());
                    int wl_number = wl_passenger.number;
                    wl_pos.add(wl_number);
                    wl_list.remove(wl_passenger.passengerID);
                    aWL++;

                    wl_passenger.number = rac_pos.get(0);
                    wl_passenger.allotted = "RAC";
                    rac_pos.remove(0);
                    rac_list.add(wl_passenger.passengerID);
                    aWL++;
                    aRAC--;
                }

                Main.book(rac_passenger);
            }
    }
}
