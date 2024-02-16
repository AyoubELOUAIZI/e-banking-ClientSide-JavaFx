package estm.dsic.jee.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AccountService extends Remote {
    double checkBalance(int userId) throws RemoteException;

    boolean deposit(int userId, double amount) throws RemoteException;

    boolean withdraw(int userId, double amount) throws RemoteException;

    boolean transfer(int senderUserId, String receiverUsername, double amount) throws RemoteException;
}
