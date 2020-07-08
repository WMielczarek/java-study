package ClassLoader;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import TSP.TSP;


public class RunMain {

    private Method methodToRun;
    private Class classToRun;

    private List<String> packageWithClassList = new ArrayList<>();

    public TSP tspInstance;
    public MyClassLoader myClassLoader;
    public RunMain() {
        this.tspInstance = new TSP();
        this.myClassLoader = null;
    }

    public void findPackagesWithClasses(String directoryName) {
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                String packageWithClass = file.toString().replace(Configuration.PATH_TO_FOLDER, "").replace(".class", "").replace("\\", ".");
                packageWithClassList.add(Configuration.NAME_OF_FOLDER + "." + packageWithClass);
                System.out.println(packageWithClass);
            } else if (file.isDirectory()) {
                findPackagesWithClasses(file.getAbsolutePath());
            }
        }
    }

    private static String getSignature(Method m) {
        String sig;
        try {
            Field gSig = Method.class.getDeclaredField("signature");
            gSig.setAccessible(true);
            sig = (String) gSig.get(m);
            if (sig != null) return sig;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder("(");
        for (Class<?> c : m.getParameterTypes())
            sb.append((sig = Array.newInstance(c, 0).toString()).substring(1, sig.indexOf('@')));

        return sb.append(')')
                .append(
                        m.getReturnType() == void.class ? "V" : (sig = Array.newInstance(m.getReturnType(), 0).toString()).substring(1, sig.indexOf('@'))
                ).toString();
    }

    private void searchClassesWithMethodsWithSignature(String methodName, String signature) throws ClassNotFoundException {
        myClassLoader = new MyClassLoader();

        for (String packageWithClass : packageWithClassList) {
            Class myClass = myClassLoader.loadClass(packageWithClass);
            Method[] methods = myClass.getDeclaredMethods();

            for (Method m : methods) {
                if (m.getName().equals(methodName) && getSignature(m).contains(signature)) {
                }
            }
        }

    }

    public void initialize() {
        findPackagesWithClasses(Configuration.PATH_TO_FOLDER);
    }

    public void loadedClassMenu() {
        this.myClassLoader.getClass();
        System.out.println(this.myClassLoader.loadedClasses);
        System.out.println(this.myClassLoader.loadedClassStrings);
        while(true) {
            findPackagesWithClasses(Configuration.PATH_TO_FOLDER);
            System.out.println("1. Wykonaj algorytm danej klasie");
            System.out.println("2. Wyladuj klase");
            Scanner reader = new Scanner(System.in);
            char x = reader.next().charAt(0);
            switch (x) {
                case '1':
                    break;
                case '2':
                    break;
                default:
                    System.out.println("Nie ma takiej opcji");
                break;

            }
        }
    }

    public void readFromFile() {
        System.out.println("Podaj nazwe pliku: ");
        Scanner reader = new Scanner(System.in);
        String fileName = reader.nextLine();
        this.tspInstance.readFromFile(fileName);
    }


    public static void main(String[] args) {

        RunMain main = new RunMain();
        while (true) {
            System.out.println("1. Wczytaj instancje TSP z pliku.");
            System.out.println("2. Wyswietl zaladowane klasy.");
            System.out.println("0. Zakoncz");
            Scanner reader = new Scanner(System.in);
            char x = reader.next().charAt(0);
            switch (x) {
                case '1':
                    main.readFromFile();
                    break;
                case '2':
                    main.loadedClassMenu();
                    break;
                case '0':
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nie ma takiej opcji");
                    break;

            }
        }
    }
}
