package mbean;

import java.lang.management.ManagementFactory;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import texters.WorkerManager;


public class SystemConfigManagement {
	private static final int DEFAULT_NO_THREADS = 3;
    private static final int DEFAULT_CACHE_SIZE = 5;
    private static final String DIRECTORY_PATH = "C:\\Users\\Wojtek\\Desktop\\Pliki";

    public static void main(String[] args) throws MalformedObjectNameException, InterruptedException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
        //Get the MBean server
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        //register the MBean
        WorkerManager manager = new WorkerManager(DEFAULT_NO_THREADS, DIRECTORY_PATH, DEFAULT_CACHE_SIZE);
        MyMBean mBean = new MyMBean(manager);
        
        ObjectName name = new ObjectName("com.journaldev.jmx:type=SystemConfig");
        mbs.registerMBean(mBean, name);
        
        manager.startWorkers();
        do{
            Thread.sleep(30000);
            String message = "Thread: " + mBean.getThreadCount();
            message += " Cache size: " + mBean.getCacheSize();
            message += " Folder: " + mBean.getWorkingDirectory();
            System.out.println(message);
        }while(manager.getWorkersAmount() != 0);
        
        System.out.println("Liczba wątków ustationa" + mBean.getThreadCount());
        System.out.println("Zamykanie aplikacji");
        manager.stopWorkers();
        
        
    }
}
