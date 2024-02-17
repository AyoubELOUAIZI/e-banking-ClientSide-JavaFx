package estm.dsic.jee.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import estm.dsic.jee.Models.Transaction;

public interface TransactionService extends Remote {

    List<Transaction> getTransactionHistory(int userId) throws RemoteException;
}

