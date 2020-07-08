package appClass;

import java.awt.image.BufferedImage;
import java.util.Date;


public class Pen implements Thing {
    private String name;
    private float price;
    private BufferedImage image;
    private Date dateOfAddition;
    private String nameOfPicture;

    public String getNameOfPicture() {
        return nameOfPicture;
    }

    public void setNameOfPicture(String nameOfPicture) {
        this.nameOfPicture = nameOfPicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Date getDateOfAddition() {
        return dateOfAddition;
    }

    public void setDateOfAddition(Date dateOfAddition) {
        this.dateOfAddition = dateOfAddition;
    }

}
