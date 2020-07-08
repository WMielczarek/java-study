//This class interfaces directly between the client and the server
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.*;
public interface RMIPeerInterface extends Remote {
	
	void acceptFileChunk(String name, int part) throws RemoteException;
	byte[] getFileChunk(String name) throws RemoteException, FileNotFoundException, IOException;
}