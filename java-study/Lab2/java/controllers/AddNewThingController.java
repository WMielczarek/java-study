package controllers;

import appClass.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddNewThingController {
    private int whichThing;
    private List<Thing> list;
    private BufferedImage image1,image2,image3;
    private String nameOfPicture1,nameOfPicture2,nameOfPicture3;
    private ResourceBundle words=ResourceBundle.getBundle("Words", Locale.getDefault());

    @FXML
    private ImageView imageView1;
    @FXML
    private ImageView imageView2;
    @FXML
    private ImageView imageView3;
    @FXML
    private Button addButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private ToggleGroup pictureRadioButtonGroup;
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLabel;


    public void init(){
        if(whichThing==0)
        {
            try {
                image1=ImageIO.read(new File("src/main/resources/1.jpg"));
                image2=ImageIO.read(new File("src/main/resources/2.jpg"));
                image3=ImageIO.read(new File("src/main/resources/3.jpg"));
                nameOfPicture1="1.jpg";
                nameOfPicture2="2.jpg";
                nameOfPicture3="3.jpg";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(whichThing==1)
        {
            try {
                image1=ImageIO.read(new File("src/main/resources/4.jpg"));
                image2=ImageIO.read(new File("src/main/resources/5.jpg"));
                image3=ImageIO.read(new File("src/main/resources/6.jpg"));
                nameOfPicture1="4.jpg";
                nameOfPicture2="5.jpg";
                nameOfPicture3="6.jpg";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(whichThing==2)
        {
            try {
                image1=ImageIO.read(new File("src/main/resources/7.jpg"));
                image2=ImageIO.read(new File("src/main/resources/8.jpg"));
                image3=ImageIO.read(new File("src/main/resources/9.jpg"));
                nameOfPicture1="7.jpg";
                nameOfPicture2="8.jpg";
                nameOfPicture3="9.jpg";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(whichThing==3)
        {
            try {
                image1=ImageIO.read(new File("src/main/resources/10.jpg"));
                image2=ImageIO.read(new File("src/main/resources/11.jpg"));
                image3=ImageIO.read(new File("src/main/resources/12.jpg"));
                nameOfPicture1="10.jpg";
                nameOfPicture2="11.jpg";
                nameOfPicture3="12.jpg";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        imageView1.setImage(SwingFXUtils.toFXImage(image1,null));
        imageView2.setImage(SwingFXUtils.toFXImage(image2,null));
        imageView3.setImage(SwingFXUtils.toFXImage(image3,null));
        nameLabel.setText(words.getString("name")+":");
        priceLabel.setText(words.getString("price")+":");
        addButton.setText(words.getString("add"));
    }

    public void setInit(List<Thing> list, int number){
        this.list=list;
        this.whichThing=number;
    }

    public void addButtonSet(){
        RadioButton radio=(RadioButton) pictureRadioButtonGroup.getSelectedToggle();

        if(whichThing==0)
        {
            Pen pen=new Pen();
            pen.setName(nameTextField.getText());
            pen.setPrice(Float.parseFloat(priceTextField.getText()));
            pen.setDateOfAddition(new Date());

            if(radio.getText().charAt(0)=='1'){
                pen.setImage(image1);
                pen.setNameOfPicture(nameOfPicture1);
            }
            else if(radio.getText().charAt(0)=='2'){
                pen.setImage(image2);
                pen.setNameOfPicture(nameOfPicture2);
            }
            else if(radio.getText().charAt(0)=='3'){
                pen.setImage(image3);
                pen.setNameOfPicture(nameOfPicture3);
            }
            list.add(pen);
        }
        if(whichThing==1)
        {
            Book book=new Book();
            book.setName(nameTextField.getText());
            book.setPrice(Float.parseFloat(priceTextField.getText()));
            book.setDateOfAddition(new Date());

            if(radio.getText().charAt(0)=='1'){
                book.setImage(image1);
                book.setNameOfPicture(nameOfPicture1);
            }
            else if(radio.getText().charAt(0)=='2'){
                book.setImage(image2);
                book.setNameOfPicture(nameOfPicture2);
            }
            else if(radio.getText().charAt(0)=='3') {
                book.setImage(image3);
                book.setNameOfPicture(nameOfPicture3);
            }
            list.add(book);
        }

        if(whichThing==2)
        {
            Phone phone=new Phone();
            phone.setName(nameTextField.getText());
            phone.setPrice(Float.parseFloat(priceTextField.getText()));
            phone.setDateOfAddition(new Date());

            if(radio.getText().charAt(0)=='1') {
                phone.setImage(image1);
                phone.setNameOfPicture(nameOfPicture1);
            }
            else if(radio.getText().charAt(0)=='2'){
                phone.setImage(image2);
                phone.setNameOfPicture(nameOfPicture2);
            }
            else if(radio.getText().charAt(0)=='3'){
                phone.setImage(image3);
                phone.setNameOfPicture(nameOfPicture3);
            }
            list.add(phone);
        }

        if(whichThing==3)
        {
            Glass glass=new Glass();
            glass.setName(nameTextField.getText());
            glass.setPrice(Float.parseFloat(priceTextField.getText()));
            glass.setDateOfAddition(new Date());

            if(radio.getText().charAt(0)=='1'){
                glass.setImage(image1);
                glass.setNameOfPicture(nameOfPicture2);
            }
            else if(radio.getText().charAt(0)=='2') {
                glass.setImage(image2);
                glass.setNameOfPicture(nameOfPicture2);
            }
            else if(radio.getText().charAt(0)=='3') {
                glass.setImage(image3);
                glass.setNameOfPicture(nameOfPicture3);
            }
            list.add(glass);
        }
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        stage.close();

    }

}
