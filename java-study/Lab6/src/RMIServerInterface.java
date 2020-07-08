//This class interfaces directly between the client and the server
import java.rmi.*;
public interface RMIServerInterface extends Remote {
	
	int registerNode() throws RemoteException;
	void uploadFileToServer(byte[] mydata, String serverpath, int length) throws RemoteException;
	Integer[] getPeersForFile(String fileName) throws RemoteException;

}