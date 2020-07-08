package controllers;

import appClass.*;
import com.ibm.icu.text.PluralRules;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.text.*;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;


public class MenuController {
    private Storage storage;
    private ObservableList<Thing> data;
    private Locale usLocale=new Locale("en","US");
    private Locale enLocale=new Locale("en","GB");
    private Locale plLocale=new Locale ("pl","PL");
    private Locale deLocale=new Locale("de","DE");
    private TableColumn thirdTableColumn = new TableColumn();
    private TableColumn secondTableColumn = new TableColumn();
    private TableColumn firstTableColumn = new TableColumn();
    private ResourceBundle words;
    private int numberThing;
    private String thing;
    private static PluralRules pluralRules;

    @FXML
    private ComboBox thingComboBox;
    @FXML
    private TableView thingTableView;
    @FXML
    private ImageView pictureImage;
    @FXML
    private ComboBox languageComboBox;
    @FXML
    private Label objectLabel;
    @FXML
    private Button readFromFileButton;
    @FXML
    private Button saveFileButton;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Label pictureLabel;
    @FXML
    private Label languageLabel;
    @FXML
    private Label patternLabel;

    @FXML
    public void initialize() {
        storage=new Storage();
        languageComboBox.getItems().addAll("PL","EN","DE","US");
        thingComboBox.getItems().addAll("Długopisy","Książki","Telefony","Szklanki");
        firstTableColumn.setCellValueFactory(new PropertyValueFactory<Thing,String>("name"));
        secondTableColumn.setCellValueFactory(new PropertyValueFactory<Thing,String>("price"));
        thirdTableColumn.setMinWidth(250);
        thirdTableColumn.setCellValueFactory(new PropertyValueFactory<Thing,String>("dateOfAddition"));
        thingTableView.getColumns().addAll(firstTableColumn,secondTableColumn,thirdTableColumn);
        data=thingTableView.getItems();
    }



    public void setLocale(){
        if (languageComboBox.getSelectionModel().getSelectedIndex()==-1) languageComboBox.getSelectionModel().select(0);
        if(languageComboBox.getSelectionModel().getSelectedIndex()==0)Locale.setDefault(plLocale);
        else if(languageComboBox.getSelectionModel().getSelectedIndex()==1)Locale.setDefault(enLocale);
        else if(languageComboBox.getSelectionModel().getSelectedIndex()==2)Locale.setDefault(deLocale);
        else if(languageComboBox.getSelectionModel().getSelectedIndex()==3)Locale.setDefault(usLocale);
        thirdTableColumn.setCellFactory(column -> {
            TableCell<Date,Date> cell = new TableCell<Date,Date>() {
                DateFormat formatter = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(formatter.format(item));
                    }
                }
            };
            return cell;
        });

        secondTableColumn.setCellFactory(column -> {
            TableCell<Float,Float> cell = new TableCell<Float,Float>() {
                NumberFormat formatter =NumberFormat.getCurrencyInstance(Locale.getDefault());
                @Override
                protected void updateItem(Float item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(formatter.format(item));
                    }
                }
            };

            return cell;
        });
        pluralRules = PluralRules.forLocale(Locale.getDefault());
        Stage stage = (Stage) addButton.getScene().getWindow();
        words= ResourceBundle.getBundle("Words",Locale.getDefault());
        objectLabel.setText(words.getString("object"));
        readFromFileButton.setText(words.getString("read"));
        saveFileButton.setText(words.getString("save"));
        addButton.setText(words.getString("add"));
        removeButton.setText(words.getString("remove"));
        pictureLabel.setText(words.getString("picture"));
        languageLabel.setText(words.getString("language"));
        thingComboBox.getItems().clear();
        thingComboBox.getItems().addAll(words.getString("pens"),words.getString("books"),words.getString("phones"),words.getString("glasses"));
        firstTableColumn.setText(words.getString("name"));
        secondTableColumn.setText(words.getString("price"));
        thirdTableColumn.setText(words.getString("date"));
        stage.setTitle(words.getString("title"));
        setTableView();
    }

    public void setTableView()
    {
        if(thingComboBox.getSelectionModel().getSelectedIndex()==-1)thingComboBox.getSelectionModel().select(0);
        if(thingComboBox.getSelectionModel().getSelectedIndex()==0){
            data.clear();
            if(storage.getListOfPens().size()!=0)
            {
                for (Thing thing:storage.getListOfPens()) {
                    data.addAll(thing);
                }
            }
            thingTableView.setItems(data);
        }

        if(thingComboBox.getSelectionModel().getSelectedIndex()==1){
            data.clear();
            if(storage.getListOfBooks().size()!=0)
            {
                for (Thing thing:storage.getListOfBooks()) {
                    data.addAll(thing);
                }
            }
            thingTableView.setItems(data);
        }

        if(thingComboBox.getSelectionModel().getSelectedIndex()==2){
            data.clear();
            if(storage.getListOfPhones().size()!=0)
            {
                for (Thing thing:storage.getListOfPhones()) {
                    data.addAll(thing);
                }
            }
            thingTableView.setItems(data);
        }

        if(thingComboBox.getSelectionModel().getSelectedIndex()==3){
            data.clear();
            if(storage.getListOfGlasses().size()!=0)
            {
                for (Thing thing:storage.getListOfGlasses()) {
                    data.addAll(thing);
                }
            }
            thingTableView.setItems(data);
        }

        if(thingComboBox.getSelectionModel().getSelectedIndex()==0) {
            numberThing=storage.getListOfPens().size();
            thing="pen";
        }
        else if(thingComboBox.getSelectionModel().getSelectedIndex()==1) {
            numberThing=storage.getListOfBooks().size();
            thing="book";
        }
        else if(thingComboBox.getSelectionModel().getSelectedIndex()==2) {
            numberThing=storage.getListOfPhones().size();
            thing="phone";
        }
        else if(thingComboBox.getSelectionModel().getSelectedIndex()==3) {
            numberThing=storage.getListOfGlasses().size();
            thing="glass";
        }
        setNumberThingLabel();
        setImagePicture();
    }

    private void setNumberThingLabel(){
        patternLabel.setText(MessageFormat.format(words.getString(thing+".plural_form." +pluralRules.select(numberThing)),String.valueOf(numberThing)));
    }

    public void setImagePicture(){
        if(thingTableView.getSelectionModel().getSelectedItem()!=null)
        {
            Thing thing=(Thing)thingTableView.getSelectionModel().getSelectedItem();
            pictureImage.setImage( SwingFXUtils.toFXImage(thing.getImage(),null));
        } else if(thingComboBox.getSelectionModel().getSelectedIndex()!=-1)
        {
            thingTableView.getSelectionModel().select(0);
            if(thingTableView.getSelectionModel().getSelectedItem()!=null){
                Thing thing=(Thing)thingTableView.getSelectionModel().getSelectedItem();
                pictureImage.setImage( SwingFXUtils.toFXImage(thing.getImage(),null));
            }
        }
    }

    public void removeFromTable()
    {
        Object thing= thingTableView.getSelectionModel().getSelectedItem();
        thingTableView.getItems().remove(thing);
        if(thingComboBox.getSelectionModel().getSelectedIndex()==0){storage.getListOfPens().remove(thingTableView.getSelectionModel().getSelectedIndex()+1);}
        else if(thingComboBox.getSelectionModel().getSelectedIndex()==1){storage.getListOfBooks().remove(thingTableView.getSelectionModel().getSelectedIndex()+1);}
        else if(thingComboBox.getSelectionModel().getSelectedIndex()==2){storage.getListOfPhones().remove(thingTableView.getSelectionModel().getSelectedIndex()+1);}
        else if(thingComboBox.getSelectionModel().getSelectedIndex()==3){storage.getListOfGlasses().remove(thingTableView.getSelectionModel().getSelectedIndex()+1);}
        setTableView();
    }

    public void addNewThingWindow()
    {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/addNewThing.fxml"));
            Parent root = fxmlLoader.load();

            AddNewThingController addNewThingController=fxmlLoader.getController();
            if(thingComboBox.getSelectionModel().getSelectedIndex()==0){addNewThingController.setInit(storage.getListOfPens(),0);}
            if(thingComboBox.getSelectionModel().getSelectedIndex()==1){addNewThingController.setInit(storage.getListOfBooks(),1);}
            if(thingComboBox.getSelectionModel().getSelectedIndex()==2){addNewThingController.setInit(storage.getListOfPhones(),2);}
            if(thingComboBox.getSelectionModel().getSelectedIndex()==3){addNewThingController.setInit(storage.getListOfGlasses(),3);}

            Scene scene =new Scene(root);
            Stage stage = new Stage();
            stage.setTitle(words.getString("newCard"));
            stage.setScene(scene);
            addNewThingController.init();
            stage.show();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    setTableView();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveFileButton(){

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;
            docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("magazyn");
            doc.appendChild(rootElement);

            for(Thing thing : storage.getListOfPens()){
                Element staff = doc.createElement("pen");
                rootElement.appendChild(staff);

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(thing.getName()));
                staff.appendChild(name);

                Element price = doc.createElement("price");
                price.appendChild(doc.createTextNode(String.valueOf(thing.getPrice())));
                staff.appendChild(price);

                Element image = doc.createElement("image");
                image.appendChild(doc.createTextNode(thing.getNameOfPicture()));
                staff.appendChild(image);

                Element date = doc.createElement("date");
                date.appendChild(doc.createTextNode(new SimpleDateFormat("yyyy-MM-dd").format(thing.getDateOfAddition())));
                staff.appendChild(date);
            }

            for(Thing thing : storage.getListOfPhones()){
                Element staff = doc.createElement("phone");
                rootElement.appendChild(staff);

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(thing.getName()));
                staff.appendChild(name);

                Element price = doc.createElement("price");
                price.appendChild(doc.createTextNode(String.valueOf(thing.getPrice())));
                staff.appendChild(price);

                Element image = doc.createElement("image");
                image.appendChild(doc.createTextNode(thing.getNameOfPicture()));
                staff.appendChild(image);

                Element date = doc.createElement("date");
                date.appendChild(doc.createTextNode(new SimpleDateFormat("yyyy-MM-dd").format(thing.getDateOfAddition())));
                staff.appendChild(date);
            }

            for(Thing thing : storage.getListOfGlasses()){
                Element staff = doc.createElement("glass");
                rootElement.appendChild(staff);

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(thing.getName()));
                staff.appendChild(name);

                Element price = doc.createElement("price");
                price.appendChild(doc.createTextNode(String.valueOf(thing.getPrice())));
                staff.appendChild(price);

                Element image = doc.createElement("image");
                image.appendChild(doc.createTextNode(thing.getNameOfPicture()));
                staff.appendChild(image);

                Element date = doc.createElement("date");
                date.appendChild(doc.createTextNode(new SimpleDateFormat("yyyy-MM-dd").format(thing.getDateOfAddition())));
                staff.appendChild(date);
            }

            for(Thing thing : storage.getListOfBooks()){
                Element staff = doc.createElement("book");
                rootElement.appendChild(staff);

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(thing.getName()));
                staff.appendChild(name);

                Element price = doc.createElement("price");
                price.appendChild(doc.createTextNode(String.valueOf(thing.getPrice())));
                staff.appendChild(price);

                Element image = doc.createElement("image");
                image.appendChild(doc.createTextNode(thing.getNameOfPicture()));
                staff.appendChild(image);

                Element date = doc.createElement("date");
                date.appendChild(doc.createTextNode(new SimpleDateFormat("yyyy-MM-dd").format(thing.getDateOfAddition())));
                staff.appendChild(date);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\Users\\Wojtek\\Dropbox\\[PJ] Laboratorium\\Lab2\\file.xml"));
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }


    }
    public void curencyChanger() {

    }

    @FXML
    public void readFromXmlFile() {
        storage=new Storage();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File file = new File(chooser.getSelectedFile().getPath());
            Document doc = builder.parse(file);

            Element root = doc.getDocumentElement();

            NodeList children = root.getChildNodes();
            if (children != null && children.getLength() > 0) {
                for (int i = 0; i < children.getLength(); i++) {

                    Node child =children.item(i);
                    if (child instanceof Element) {
                        Element el = (Element) child;

                        if (el.getTagName().equals("pen")) {
                            NodeList childrenV2 = el.getChildNodes();
                            Pen pen = new Pen();

                            if (childrenV2 != null && childrenV2.getLength() > 0) {
                                for (int k = 0; k < childrenV2.getLength(); k++) {
                                    child =childrenV2.item(k);
                                    if (child instanceof Element) {
                                        Element elV2 = (Element) child;
                                        Text textNode = (Text) elV2.getFirstChild();
                                        String text = textNode.getData().trim();
                                        switch (elV2.getTagName()){
                                            case "name": pen.setName(text); break;
                                            case "price": pen.setPrice(Float.parseFloat(text)); break;
                                            case "date": pen.setDateOfAddition(new SimpleDateFormat("yyyy-MM-dd").parse(text));break;
                                            case "image": pen.setNameOfPicture(text);
                                            pen.setImage(ImageIO.read(new File("src/main/resources/"+text)));break;
                                        }
                                    }
                                }
                            }
                            storage.getListOfPens().add(pen);
                        }

                        if (el.getTagName().equals("phone")) {
                            NodeList childrenV2 = el.getChildNodes();
                            Phone phone = new Phone();

                            if (childrenV2 != null && childrenV2.getLength() > 0) {
                                for (int k = 0; k < childrenV2.getLength(); k++) {
                                    child =childrenV2.item(k);
                                    if (child instanceof Element) {
                                        Element elV2 = (Element) child;
                                        Text textNode = (Text) elV2.getFirstChild();
                                        String text = textNode.getData().trim();
                                        switch (elV2.getTagName()){
                                            case "name": phone.setName(text); break;
                                            case "price": phone.setPrice(Float.parseFloat(text)); break;
                                            case "date": phone.setDateOfAddition(new SimpleDateFormat("yyyy-MM-dd").parse(text));break;
                                            case "image": phone.setNameOfPicture(text);
                                                phone.setImage(ImageIO.read(new File("src/main/resources/"+text)));break;
                                        }
                                    }
                                }
                            }
                            storage.getListOfPhones().add(phone);
                        }

                        if (el.getTagName().equals("glass")) {
                            NodeList childrenV2 = el.getChildNodes();
                            Glass glass = new Glass();

                            if (childrenV2 != null && childrenV2.getLength() > 0) {
                                for (int k = 0; k < childrenV2.getLength(); k++) {
                                    child =childrenV2.item(k);
                                    if (child instanceof Element) {
                                        Element elV2 = (Element) child;
                                        Text textNode = (Text) elV2.getFirstChild();
                                        String text = textNode.getData().trim();
                                        switch (elV2.getTagName()){
                                            case "name": glass.setName(text); break;
                                            case "price": glass.setPrice(Float.parseFloat(text)); break;
                                            case "date": glass.setDateOfAddition(new SimpleDateFormat("yyyy-MM-dd").parse(text));break;
                                            case "image": glass.setNameOfPicture(text);
                                                glass.setImage(ImageIO.read(new File("src/main/resources/"+text)));break;
                                        }
                                    }
                                }
                            }
                            storage.getListOfGlasses().add(glass);
                        }

                        if (el.getTagName().equals("book")) {
                            NodeList childrenV2 = el.getChildNodes();
                            Book book = new Book();

                            if (childrenV2 != null && childrenV2.getLength() > 0) {
                                for (int k = 0; k < childrenV2.getLength(); k++) {

                                    child =childrenV2.item(k);
                                    if (child instanceof Element) {
                                        Element elV2 = (Element) child;
                                        Text textNode = (Text) elV2.getFirstChild();
                                        String text = textNode.getData().trim();
                                        switch (elV2.getTagName()){
                                            case "name": book.setName(text); break;
                                            case "price": book.setPrice(Float.parseFloat(text)); break;
                                            case "date": book.setDateOfAddition(new SimpleDateFormat("yyyy-MM-dd").parse(text));break;
                                            case "image": book.setNameOfPicture(text);
                                                book.setImage(ImageIO.read(new File("src/main/resources/"+text)));break;
                                        }
                                    }
                                }
                            }
                            storage.getListOfBooks().add(book);
                        }
                    }
                }
            }

        } catch (ParserConfigurationException | IOException | ParseException | SAXException e1) {
            e1.printStackTrace();
        }
        setLocale();
        setTableView();
    }
    }
