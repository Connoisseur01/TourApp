package jp.terminal;

import jp.Utils.Data;
import jp.model.Tour;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class TouristTerminal {

    private static PrintWriter pw;
    private static BufferedReader br;
    private static String nickname;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("localhost", 6666);
        pw = new PrintWriter(socket.getOutputStream(), true);
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        login(scanner);
        menu(scanner);
        pw.close();
        br.close();
        socket.close();
        scanner.close();
    }

    private static void login(Scanner scanner){

        System.out.println("Login: ");
        nickname = scanner.nextLine();
        System.out.println("Successful login");
    }

    private static void menu(Scanner scanner) throws IOException {
        int a = 0;
        while(a != 4) {
            System.out.println("""
                    -----------------------------------
                    1. get tour offers
                    2. register for tour
                    3. unregister from tour
                    4. exit""");
            a = scanner.nextInt();
            switch(a){
                case 1: getTourOffers(); break;
                case 2: registerForTour(scanner); break;
                case 3: unregisterFromTour(scanner); break;
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

    private static void unregisterFromTour(Scanner scanner) throws IOException {
        System.out.println("index of tour you want to unregister from: ");
        int index = scanner.nextInt();
        pw.println("unregisterFromTour");
        pw.println(nickname);
        pw.println(index);
        if(br.readLine().equals(false)) System.out.println("you are not registered for this tour");
        else System.out.println("successfully unregistered from tour");
    }

    private static void registerForTour(Scanner scanner) throws IOException {
        System.out.println("index of tour you want to register for: ");
        int index = scanner.nextInt();
        pw.println("registerForTour");
        pw.println(nickname);
        pw.println(index);
        if(br.readLine().equals("false")){
            System.out.println("You are already registered for this tour or the tour is full");
        } else System.out.println("successfully registered");
    }
}
