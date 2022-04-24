package jp.terminal;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class GuideTerminal {

    private static PrintWriter pw;
    private static BufferedReader br;
    private static String nickname;
    private static int indexOfTour = -1;

    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("localhost", 6666);
        pw = new PrintWriter(socket.getOutputStream(), true);
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        login(scanner);
        menu(scanner);
        pw.close();
        br.close();
        socket.close();
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
                    2. register for tour as a guide
                    3. unregister from tour as a guide
                    4. exit""");
            a = scanner.nextInt();
            switch(a){
                case 1: getTourOffers(); break;
                case 2: addguide(scanner); break;
                case 3: removeguide(); break;
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

    private static void addguide(Scanner scanner){
        System.out.println("index of tour you would like to attend as a guide: ");
        indexOfTour = scanner.nextInt();
        pw.println("addguide");
        pw.println(nickname);
        pw.println(indexOfTour);
        System.out.println("Successfully registered");
    }

    private static void removeguide(){
        if(indexOfTour == -1){
            System.out.println("you are not registered for any tour");
        }else{
            pw.println("removeguide");
            pw.println(indexOfTour);
        }

    }

}
