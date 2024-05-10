/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import biblioteca.GestorBibliotecaIntf;
import biblioteca.TDatosRepositorio;
import biblioteca.TLibro;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Angel
 */
public class GestorBibliotecaImpl implements GestorBibliotecaIntf{

    private int IdAdmin = 14;
    private List<String> repositoriosCargados = new ArrayList<>();
        private List<TLibro> libros = new ArrayList<>(); 
    private int totalLibros = 0;
    
    @Override
    public int Conexion(String pPasswd) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Desconexion(int pIda) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int NRepositorios(int pIda) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TDatosRepositorio DatosRepositorio(int pIda, int pPosRepo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int AbrirRepositorio(int pIda, String pNomFichero) throws RemoteException {
       
        int result;
        int pruebaEnt = 0;
        
        if(IdAdmin == -1 /*|| pIda != IdAdmin*/){
            result = -1;
        }else if(repositoriosCargados.contains(pNomFichero)){
            result = -2;
        }else{
            try (DataInputStream dis = new DataInputStream(new FileInputStream(pNomFichero))){
               
                int nLibros = dis.readInt();
                
                totalLibros += nLibros;
                
                String nombreRepositorio = dis.readUTF();
                String direccionRepositorio = dis.readUTF();
                
                for (int i = 0; i < nLibros; i++) {
                String idioma = dis.readUTF(); // Leer el idioma
                String isbn = dis.readUTF(); // Leer el ISBN
                String pais = dis.readUTF(); // Leer el país
                String titulo = dis.readUTF(); // Leer el título
                String autor = dis.readUTF(); // Leer el autor
                int noLibros = dis.readInt(); // Leer el número de copias
                int noPrestados = dis.readInt(); // Leer el número de prestados
                int noListaEspera = dis.readInt(); // Leer el número en lista de espera
                int anio = dis.readInt(); // Leer el año

                // Crear el objeto TLibro con la información leída
                TLibro libro = new TLibro(idioma, isbn, pais, titulo, autor, noLibros, noPrestados, noListaEspera, anio);
                libros.add(libro); // Agregar el libro a la lista
                pruebaEnt+=1;
            }
                    repositoriosCargados.add(pNomFichero);
                    result = 1;
                
                
                } catch (FileNotFoundException fnf) {
                return 0; // Archivo no encontrado
            } catch (IOException e) {
                throw new RemoteException("Error al cargar el repositorio: " + e.getMessage());
        }
        }
        System.out.println("PRUEBA 1:" + pruebaEnt);
        System.out.println("RESULTADO: " + result);
        System.out.println("TOTAL LIBROS: " + totalLibros);
        return result;
    }

    @Override
    public int GuardarRepositorio(int pIda, int pRepo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int NuevoLibro(int pIda, TLibro L, int pRepo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int Comprar(int pIda, String pIsbn, int pNoLibros) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int Retirar(int pIda, String pIsbn, int pNoLibros) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Ordenar(int pIda, int pCampo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int NLibros(int pRepo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int Buscar(int pIda, String pIsbn) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TLibro Descargar(int pIda, int pRepo, int pPos) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int Prestar(int pPos) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int Devolver(int pPos) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
