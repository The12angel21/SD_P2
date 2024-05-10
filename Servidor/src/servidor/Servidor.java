/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package servidor;

import biblioteca.GestorBibliotecaIntf;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.rmi.registry.*;
import java.rmi.server.*;

/**
 *
 * @author Angel
 */
public class Servidor {

    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     */
    public static void main(String[] args) throws RemoteException{
        Registry registry; 
        try {
            int Puerto = 0;
            Scanner Teclado = new Scanner(System.in);
            
            System.out.println("Introduce el nº de puerto: ");
            Puerto = Teclado.nextInt();
            
            registry = LocateRegistry.createRegistry(Puerto);
            
            GestorBibliotecaImpl bib = new GestorBibliotecaImpl();
            
            GestorBibliotecaIntf biblio = (GestorBibliotecaIntf) UnicastRemoteObject.exportObject(bib, Puerto);
            
            registry = LocateRegistry.getRegistry(Puerto);
            registry.rebind("Biblioteca", biblio);
            
            System.out.println("Servidor esperando respuetas");
             
        } catch (RemoteException e) {
            System.out.println("Error en servidor: "+e);
        } 
    }
    
}
