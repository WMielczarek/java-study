package appClass;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<Thing> listOfPens;
    private List<Thing> listOfBooks;
    private List<Thing> listOfPhones;
    private List<Thing> listOfGlasses;

    public List<Thing> getListOfPens() {
        return listOfPens;
    }

    public List<Thing> getListOfBooks() {
        return listOfBooks;
    }

    public List<Thing> getListOfPhones() { return listOfPhones; }

    public List<Thing> getListOfGlasses() {
        return listOfGlasses;
    }

    public Storage() {
        listOfBooks=new ArrayList<>();
        listOfPhones=new ArrayList<>();
        listOfGlasses= new ArrayList<>();
        listOfPens= new ArrayList<>();
    }
}
