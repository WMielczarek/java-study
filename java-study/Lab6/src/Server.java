import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Server extends UnicastRemoteObject implements RMIServerInterface{

    private static final long serialVersionUID = 1L;
    private static int registeredNodesAmount = 0;
    private static RMIServerInterface inter;
    private static RMIPeerInterface peerInter;
    private static HashMap<String, Integer[]> peersForFile = new HashMap<String, Integer[]>();
    
    protected Server() throws RemoteException {

        super();

    }
    
    @Override
    public Integer[] getPeersForFile(String fileName) throws RemoteException{
        System.err.println("Asked for peers for " + fileName + " file!");
        return peersForFile.get(fileName);
    }
    
    @Override
    public int registerNode() throws RemoteException{
        System.err.println("New node is trying to register!");
        registeredNodesAmount++;
        return registeredNodesAmount;        
    }
    
	public static void uploadFile(String serverpath, String clientpath, String fileName, int part) throws NotBoundException, IOException {
    	//clientpath = args[1];
		inter = (RMIServerInterface) Naming.lookup("//localhost/MyServer");
		File serverpathfile = new File(serverpath);
		byte [] mydata = new byte[(int) serverpathfile.length()];
		FileInputStream in = new FileInputStream(serverpathfile);		
		in.read(mydata, 0, mydata.length);
		byte[] slice = Arrays.copyOfRange(mydata, (part-1)*((int) serverpathfile.length()/3), (part)*((int) serverpathfile.length()/3));
		System.out.println("Uploading file chunk to server...");	
		inter.uploadFileToServer(slice, clientpath, (int) serverpathfile.length());
		peerInter.acceptFileChunk(fileName, part);
		in.close();
	}
	
	@Override
	public void uploadFileToServer(byte[] mydata, String serverpath, int length) throws RemoteException {
		try {
    		File serverpathfile = new File(serverpath);
    		FileOutputStream out=new FileOutputStream(serverpathfile);
    		byte [] data=mydata;
			
    		out.write(data);
			out.flush();
	    	out.close();
	 
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("Done uploading");
		
	}
    

    public static void main(String[] args) throws NotBoundException, IOException{

    	
        try {

            Naming.rebind("//localhost/MyServer", new Server());
            System.err.println("Server ready");

        } catch (Exception e) {

            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();

        }
        Scanner reader = new Scanner(System.in);
        int option = 100;
		while(option != 0) {
			System.out.print("Choose option:\n1. Show registered nodes\n2. Send file to the server\n0. Close\n");
			option = reader.nextInt();
			reader.nextLine();
			switch(option) {
			case 1:
				if(registeredNodesAmount != 0)
				for(int i = 1; i <= registeredNodesAmount; i++) {
					System.out.println(i);
				}
				break;
			case 2:
				if(registeredNodesAmount < 3) {
					System.out.println("There are not enough nodes to send the file");
				}
				else {
					Integer[] chosenPeers = new Integer[3];
					System.out.println("Input file name.txt: ");
					String fileName = reader.nextLine();
					String spath = "C:\\Users\\Wojtek\\Dropbox\\[PJ] Laboratorium\\Lab6\\Files\\" + fileName;
					
					Random generator = new Random();
					int randId = 0, randId2 = 0, randId3 = 0;
					randId = generator.nextInt()%registeredNodesAmount + 1;
					while(randId < 1) {
						randId = generator.nextInt()%registeredNodesAmount + 1;
					}
					String cpath1 = "C:\\Users\\Wojtek\\Dropbox\\[PJ] Laboratorium\\Lab6\\peer" + randId + "/chunks/" + fileName;
					peerInter = (RMIPeerInterface) Naming.lookup("//localhost/Peer" + randId);
					uploadFile(spath, cpath1, fileName, 1);
					chosenPeers[0] = randId;
					
					randId2 = generator.nextInt()%registeredNodesAmount;
					while(randId2 == randId || randId2 < 1) {
						randId2 = generator.nextInt()%registeredNodesAmount + 1;
					}
					String cpath2 = "C:\\Users\\Wojtek\\Dropbox\\[PJ] Laboratorium\\Lab6\\peer" + randId2 + "/chunks/" + fileName;
					peerInter = (RMIPeerInterface) Naming.lookup("//localhost/Peer" + randId2);
					uploadFile(spath, cpath2, fileName, 2);
					chosenPeers[1] = randId2;
					
					randId3 = generator.nextInt()%registeredNodesAmount;
					while(randId3 == randId || randId3 == randId2 || randId3 < 1) {
						randId3 = generator.nextInt()%registeredNodesAmount + 1;
					}
					String cpath3 = "C:\\Users\\Wojtek\\Dropbox\\[PJ] Laboratorium\\Lab6\\peer" + randId3 + "/chunks/" + fileName;
					peerInter = (RMIPeerInterface) Naming.lookup("//localhost/Peer" + randId3);
					uploadFile(spath, cpath3, fileName, 3);
					chosenPeers[2] = randId3;
					
					peersForFile.put(fileName, chosenPeers);
				}
				break;
			case 0:
				reader.close();
				System.exit(0);
				return;
			}
		}
		reader.close();

    }

}