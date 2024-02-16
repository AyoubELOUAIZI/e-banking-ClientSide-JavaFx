package estm.dsic.jee.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

import estm.dsic.jee.Models.User;

public interface AuthenticationService extends Remote {
    User authenticateUser(String username, String password) throws RemoteException;
}

