package jp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tour implements Serializable {

    public Tour(String name, Date date, String description, int quantityAvailable){
        this.name = name;
        this.date = date;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
    }


    private String guide;
    private String name;
    private String description;
    private Date date;
    private int touristQuantity = 0;
    private int quantityAvailable;

    public List<String> getTourists() {
        return tourists;
    }

    public void setTourists(List<String> tourists) {
        this.tourists = tourists;
    }

    private List<String> tourists = new ArrayList<>();

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTouristQuantity() {
        return touristQuantity;
    }

    public void setTouristQuantity(int touristQuantity) {
        this.touristQuantity = touristQuantity;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
}
