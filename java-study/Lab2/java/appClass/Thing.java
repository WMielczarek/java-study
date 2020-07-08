package appClass;


import java.awt.image.BufferedImage;
import java.util.Date;

public interface Thing {
    String getName();
    void setName(String name);
    float getPrice();
    void setPrice(float price);
    BufferedImage getImage();
    void setImage(BufferedImage image);
    Date getDateOfAddition();
    void setDateOfAddition(Date dateOfAddition);
    String getNameOfPicture();
    void setNameOfPicture(String nameOfPicture);
}
