import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Peer extends UnicastRemoteObject implements RMIPeerInterface {

	private static final long serialVersionUID = 1L;
	private static RMIServerInterface look_up;
	private static RMIPeerInterface peerInter;
	private static int nodeId = 1000;
	private static HashMap<String, Integer> files = new HashMap<String, Integer>();

	protected Peer() throws RemoteException {

        super();

    }
	
	@Override
    public byte[] getFileChunk(String fileName) throws IOException{
        System.err.println("You have been asked for a chunk of " + fileName + "!");
        
        File clientpathfile = new File("C:/Users/Wojtek/Dropbox/[PJ] Laboratorium/Lab6/peer" + nodeId + "/chunks/" + fileName);
        byte[] mydata = new byte[(int) clientpathfile.length()];
        FileInputStream in = new FileInputStream(clientpathfile);
        in.read(mydata, 0, mydata.length);	
        in.close();
        return mydata;        
    }
	
	@Override
    public void acceptFileChunk(String name, int part) throws RemoteException{
        System.err.println("A part of a file has been sent!");
        files.put(name, part);
    }
	
	public static void filesInfo() {
		System.out.println("Owned files:");
		for(Map.Entry<String, Integer> entry : files.entrySet()) {
			System.out.println("File: " + entry.getKey() + " part: " + entry.getValue());
		}
	}
	
	
	public static void main(String[] args) 
		throws NotBoundException, FileNotFoundException, IOException {
		
		Scanner reader = new Scanner(System.in);
		
		look_up = (RMIServerInterface) Naming.lookup("//localhost/MyServer");
		
		int option = 100;
		while(option != 0) {
			System.out.print("Node ID: " + nodeId + "\nChoose option:\n1. Register node\n2. Show owned files\n3. Check chosen file owners\n4. Download missing parts of chosen file\n0. Close\n");
			option = reader.nextInt();
			reader.nextLine();
			switch(option) {
			case 1:
				if(nodeId == 1000) {
					nodeId = look_up.registerNode();
					try {

			            Naming.rebind("//localhost/Peer" + nodeId, new Peer());            
			            System.err.println("Peer ready");

			        } catch (Exception e) {

			            System.err.println("Peer exception: " + e.toString());
			            e.printStackTrace();

			        }
					System.out.println("Registered! Assigned id: " + nodeId);
					break;
				}
				else {
					System.out.println("Node is already registered");
					break;
				}
			case 2:
				filesInfo();
				break;
			case 3:
				System.out.println("Input file name.txt: ");
				String fileName = reader.nextLine();
				Integer[] peersForFile = look_up.getPeersForFile(fileName);
				if(peersForFile != null) {
					System.out.print("Parts of " + fileName + " are in: ");
					for(int a : peersForFile) {
						System.out.print(a + " ");
					}
					System.out.print("\n");
					break;
				}
				else {
					System.out.print("There is no such file in the system\n");
					break;
				}
			case 4:
				System.out.println("Input file name.txt: ");
				String downloadFileName = reader.nextLine();
				Integer[] loadPeersForFile = look_up.getPeersForFile(downloadFileName);
				if(loadPeersForFile != null) {
					peerInter = (RMIPeerInterface) Naming.lookup("//localhost/Peer" + loadPeersForFile[0]);
					byte[] part1 = peerInter.getFileChunk(downloadFileName);
					peerInter = (RMIPeerInterface) Naming.lookup("//localhost/Peer" + loadPeersForFile[1]);
					byte[] part2 = peerInter.getFileChunk(downloadFileName);
					peerInter = (RMIPeerInterface) Naming.lookup("//localhost/Peer" + loadPeersForFile[2]);
					byte[] part3 = peerInter.getFileChunk(downloadFileName);
					byte[] fullFile = new byte[(int) part1.length*3];
					System.arraycopy(part1, 0, fullFile, 0, part1.length);
					System.arraycopy(part2, 0, fullFile, part1.length, part2.length);
					System.arraycopy(part3, 0, fullFile, part1.length + part2.length, part3.length);
					String fullFilePath = "C:/Users/Wojtek/Dropbox/[PJ] Laboratorium/Lab6/peer" + nodeId + "/full/" + downloadFileName;
					look_up.uploadFileToServer(fullFile, fullFilePath, fullFile.length);
					System.out.println("Successfully downloaded " + downloadFileName + "!");
					break;
				}
				else {
					System.out.print("There is no such file in the system");
					break;
				}
			case 0:
				reader.close();
				break;
			}
		}

	}
	
	public int getId() {
		return Peer.nodeId;
	}

}