package jp.terminal;

import jp.model.Tour;

import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class OfficeTerminal {

    private static PrintWriter pw;
    private static BufferedReader br;

    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("localhost", 6666);
        pw = new PrintWriter(socket.getOutputStream(), true);
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        menu(scanner);
        pw.close();
        br.close();
        socket.close();
        scanner.close();
    }


    private static void menu(Scanner scanner) throws IOException{

        int a = 0;
        while(a != 4) {
            System.out.println("""
                    -----------------------------------
                    1. get tour offers
                    2. add tour offer
                    3. remove tour offer
                    4. exit""");
            a = scanner.nextInt();
            switch(a){
                case 1: getTourOffers(); break;
                case 2: addTourOffer(scanner); break;
                case 3: removeTourOffer(scanner); break;
                case 4: break;
            }
        }
    }

    private static void getTourOffers() throws IOException {
        pw.println("getTourOffers");
        int quantity = parseInt(br.readLine());
        for(int i = 0; i < quantity; i++){
            System.out.println(br.readLine());
        }
    }

    private static void addTourOffer(Scanner scanner){
        String date;
        String name;
        String description;
        int quantityAvailable;
        System.out.println("nazwa: ");
        name = scanner.next();
        System.out.println("Date (dd/MM/yyyy): ");
        date = scanner.next();
        System.out.println("Description: ");
        description = scanner.next();
        System.out.println("number of people: ");
        quantityAvailable = parseInt(scanner.next());
        pw.println("addTourOffer");
        pw.println(name);
        pw.println(date);
        pw.println(description);
        pw.println(quantityAvailable);
    }

    private static void removeTourOffer(Scanner scanner){
        System.out.println("index of offer: ");
        int index = scanner.nextInt();
        pw.println("removeTourOffer");
        pw.println(index);
    }

}
