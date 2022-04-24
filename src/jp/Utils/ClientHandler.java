package jp.Utils;

import jp.model.Tour;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ClientHandler implements Runnable {


    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;

    public ClientHandler(Socket socket){
        try{
            this.socket = socket;
            this.pw = new PrintWriter(socket.getOutputStream(), true);
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        String message = "";
        while (socket.isConnected()) {

            try {
                message = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            switch (message) {

                case "removeguide":
                    try {
                        removeguide();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case "addguide":
                    try {
                        addguide();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case "getTourOffers":
                    getTourOffers();
                    break;

                case "registerForTour":
                    try {
                        registerForTour();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case "unregisterFromTour":
                    try {
                        unregisterFromTour();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case "addTourOffer":
                    try {
                        addTourOffer();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case "removeTourOffer":
                    try {
                        removeTourOffer();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }
    }

    private void getTourOffers(){
        Data data = new Data();
        List<Tour> tours = data.getTours();
        int quantity = tours.size();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        pw.println(quantity);
        for(int i = 0; i < quantity; i++){
            pw.println(i + ". | " +
                    tours.get(i).getName() + " | " +
                    sdf.format(tours.get(i).getDate()) + " | " +
                    tours.get(i).getDescription() + " | " +
                    tours.get(i).getTouristQuantity() + "/" + tours.get(i).getQuantityAvailable() +
                    " | " + tours.get(i).getTourists());
        }
    }

    private void addTourOffer() throws Exception {
        Data data = new Data();
        List<Tour> tours = data.getTours();
        String name = br.readLine();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(br.readLine());
        String description = br.readLine();
        int quantityAvailable = parseInt(br.readLine());
        Tour tour = new Tour(name, date, description, quantityAvailable);
        tours.add(tour);
        data.addTours(tours);

    }

    private void removeTourOffer() throws IOException {
        Data data = new Data();
        int index = parseInt(br.readLine());
        List<Tour> tours = data.getTours();
        tours.remove(index);
        data.addTours(tours);
    }

    private void registerForTour() throws IOException {
        Data data = new Data();
        List<Tour> tours = data.getTours();
        String nick = br.readLine();
        int index = parseInt(br.readLine());
        int quantityAvailable = tours.get(index).getQuantityAvailable();
        int touristQuantity = tours.get(index).getTouristQuantity();
        if(tours.get(index).getTourists().contains(nick) || touristQuantity == quantityAvailable){
            pw.println("false");
        }else{
            tours.get(index).getTourists().add(nick);
            tours.get(index).setTouristQuantity(touristQuantity + 1);
            data.addTours(tours);
            pw.println("true");
        }

    }

    private void unregisterFromTour() throws IOException{
        Data data = new Data();
        List<Tour> tours = data.getTours();
        String nick = br.readLine();
        int index = parseInt(br.readLine());
        int touristQuantity = tours.get(index).getTouristQuantity();
        if(tours.get(index).getTourists().contains(nick)){
            tours.get(index).getTourists().remove(nick);
            tours.get(index).setTouristQuantity(touristQuantity - 1);
            data.addTours(tours);
            pw.println("true");
        } else pw.println("false");
    }

    private void addguide() throws IOException {
        Data data = new Data();
        String guide = br.readLine();
        int index = parseInt(br.readLine());
        List<Tour> tours = data.getTours();
        tours.get(index).setGuide(guide);
        data.addTours(tours);
    }

    private void removeguide() throws IOException {
        Data data = new Data();
        int index = parseInt(br.readLine());
        List<Tour> tours = data.getTours();
        tours.get(index).setGuide("");
        data.addTours(tours);
    }

}
