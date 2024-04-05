import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        TicketBooking tb = new TicketBooking();

        while (loop) {
            System.out.println("SELECT YOUR CHOICE: \n 1. BOOK \n 2. CANCEL \n 3. PRINT BOOKED TICKETS \n 4. PRINT AVAILABLE TICKETS \n 5. EXIT");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Passenger Name: ");
                    String name = sc.next();
                    System.out.println("Enter Passenger Age: ");
                    int age = sc.nextInt();
                    System.out.println("Enter Passenger Gender: [M / F]");
                    String gender = sc.next();
                    if (gender.equals("F"))
                    {
                        String cname = "null";
                        int cage = 0;
                        System.out.println("Do you have a child with age less than 5 with you? [Y / N]");
                        String child = sc.next();
                        if (child.equals("Y")) {
                            System.out.println("Enter child name: ");
                            cname = sc.next();
                            System.out.println("Enter child age: ");
                            cage = sc.nextInt();
                        }
                        System.out.println("Enter Passenger Berth Preference: [L / M / U]");
                        String bp = sc.next();
                        Passenger p = new Passenger(name, age, gender, bp, cname, cage);
                        book(p);
                    }
                    else {
                        System.out.println("Enter Passenger Berth Preference: [L / M / U]");
                        String bp = sc.next();
                        Passenger p = new Passenger(name, age, gender, bp, "null", 0);
                        book(p);
                    }
                    break;
                case 2:
                    System.out.println("Enter Passenger ID: ");
                    int pid = sc.nextInt();
                    cancel(pid);
                    break;
                case 3:
                    tb.booked_Tickets_list();
                    break;
                case 4:
                    System.out.println("Available Lower Berth: " + TicketBooking.aLB);
                    System.out.println("Available Middle Berth: " + TicketBooking.aMB);
                    System.out.println("Available Upper Berth: " + TicketBooking.aUB);
                    System.out.println("Available RAC: " + TicketBooking.aRAC);
                    System.out.println("Available Waiting List: " + TicketBooking.aWL);
                    System.out.println("===========================================");
                    break;
                case 5:
                    loop = false;
            }
        }
    }

    public static void book(Passenger p) {

        TicketBooking tb = new TicketBooking();

        // If no tickets available
        if (TicketBooking.aWL == 0) {
            System.out.println("No Tickets Available!");
            return;
        }

        // If preferred berth is available

        if (p.age > 60 && TicketBooking.aLB > 0) {
            System.out.println("As you are a Senior Citizen, Lower Berth is Given");
            tb.bookTicket(p, TicketBooking.lb_pos.get(0), "L"); // BOOKING
            TicketBooking.lb_pos.remove(0);
            TicketBooking.aLB--;
        }

        else if (!p.cname.equals("null") && TicketBooking.aLB > 0) {
            System.out.println("As you have a child with you, You are given Lower Berth");
            tb.bookTicket(p, TicketBooking.lb_pos.get(0), "L"); // BOOKING
            TicketBooking.lb_pos.remove(0);
            TicketBooking.aLB--;
        }

        else if (p.bp.equals("L") && TicketBooking.aLB > 0) {
            System.out.println("Lower Berth Given");
            tb.bookTicket(p, TicketBooking.lb_pos.get(0), "L"); // BOOKING
            TicketBooking.lb_pos.remove(0);
            TicketBooking.aLB--;
        }

        else if (p.bp.equals("M") && TicketBooking.aMB > 0) {
            System.out.println("Middle Berth Given");
            tb.bookTicket(p, TicketBooking.mb_pos.get(0), "M");
            TicketBooking.mb_pos.remove(0);
            TicketBooking.aMB--;
        }

        else if (p.bp.equals("U") && TicketBooking.aUB > 0) {
            System.out.println("Upper Berth Given");
            tb.bookTicket(p, TicketBooking.ub_pos.get(0), "U");
            TicketBooking.ub_pos.remove(0);
            TicketBooking.aUB--;
        }

        // Available Tickets are Given
        else if (TicketBooking.aLB > 0) {
            System.out.println("Lower Berth Given");
            tb.bookTicket(p, TicketBooking.lb_pos.get(0), "L"); // BOOKING
            TicketBooking.lb_pos.remove(0);
            TicketBooking.aLB--;
        }

        else if (TicketBooking.aMB > 0) {
            System.out.println("Middle Berth Given");
            tb.bookTicket(p, TicketBooking.mb_pos.get(0), "M");
            TicketBooking.mb_pos.remove(0);
            TicketBooking.aMB--;
        }

        else if (TicketBooking.aUB > 0) {
            System.out.println("Upper Berth Given");
            tb.bookTicket(p, TicketBooking.ub_pos.get(0), "U");
            TicketBooking.ub_pos.remove(0);
            TicketBooking.aUB--;
        }

        else if (TicketBooking.aRAC > 0) {
            System.out.println("RAC Given");
            tb.book_rac(p, TicketBooking.rac_pos.get(0), "RAC");
            TicketBooking.rac_pos.remove(0);
            TicketBooking.aRAC--;
        }

        else if (TicketBooking.aWL > 0) {
            System.out.println("Waiting List Given");
            tb.book_wl(p, TicketBooking.wl_pos.get(0), "WL");
            TicketBooking.wl_pos.remove(0);
            TicketBooking.aWL--;
        }
    }

    // Cancelling Ticket

    public static void cancel (int pid) {
        if (TicketBooking.passenger_data.isEmpty() || !TicketBooking.passenger_data.containsKey(pid))
        {
            System.out.println("No Records Found!");
        }

        else
        {
            TicketBooking tb = new TicketBooking();
            tb.cancel_ticket(pid);
        }

    }
}