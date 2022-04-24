package jp.Utils;

import jp.model.Tour;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Data {

    public Data(){
        this.tours = readTours();
    }

    public List<Tour> readTours(){
        List<Tour> tours = null;
        try {
            FileInputStream fis = new FileInputStream(TOURS_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tours = (List<Tour>) ois.readObject();
            fis.close();
            ois.close();
        } catch(IOException | ClassNotFoundException e){
            e.getStackTrace();
        }
        return tours == null ? new ArrayList<>() : tours;
    }

    public void addTours(List<Tour> tours){
        try {
            FileOutputStream fos = new FileOutputStream(TOURS_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tours);
            oos.close();
            fos.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }



    private String TOURS_PATH = "./bin/tours.bin";

    public List<Tour> getTours() {
        return tours;
    }

    private List<Tour> tours;

}
