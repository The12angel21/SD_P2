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
import java.util.Random;

/**
 *
 * @author Angel
 */
public class GestorBibliotecaImpl implements GestorBibliotecaIntf {

    public int IdAdmin = 0;
    private List<String> repositoriosCargados = new ArrayList<>();
    private List<TLibro> Biblioteca = new ArrayList<>();
    private int totalLibros = 0;
    private List<TDatosRepositorio> Repositorios = new ArrayList<>();

    @Override
    public int Conexion(String pPasswd) throws RemoteException {
        
        int result = Integer.parseInt(pPasswd);
        
            if(result == IdAdmin){
                result = -1;
            }else if(result != 1234){
                result = -2;
            }else{
                Random r = new Random();
                result = r.nextInt(1000000)+1;
            }
        
        return result;
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

        int result = -2;
        int pruebaEnt = 0;

        if (IdAdmin == -1 /*|| pIda != IdAdmin*/) {
            result = -1;
        } else if (repositoriosCargados.contains(pNomFichero)) {
            result = -2;
        } else {
            try (DataInputStream dis = new DataInputStream(new FileInputStream(pNomFichero))) {
                int nLibros = dis.readInt();

                System.out.println("TOTAL LIBROS: " + nLibros);
                totalLibros += nLibros;

                String nombreRepositorio = dis.readUTF();
                String direccionRepositorio = dis.readUTF();

                TDatosRepositorio nuevoRepo = new TDatosRepositorio(nombreRepositorio, direccionRepositorio, nLibros);
                Repositorios.add(nuevoRepo);

                for (int i = 0; i < nLibros; i++) {
                    int j = i + 1;
                    System.out.println("");
                    System.out.println("*****LIBRO " + j + " *****");
                    String isbn = dis.readUTF();
                    System.out.println("isbn: " + isbn);
                    String titulo = dis.readUTF();
                    System.out.println("titulo: " + titulo);
                    String autor = dis.readUTF();
                    System.out.println("autor: " + autor);
                    int anio = dis.readInt();
                    System.out.println("anio: " + anio);
                    String pais = dis.readUTF();
                    System.out.println("pais: " + pais);
                    String idioma = dis.readUTF();
                    System.out.println("Idioma: " + idioma);
                    int noLibros = dis.readInt();
                    System.out.println("noLibros: " + noLibros);
                    int noPrestados = dis.readInt();
                    System.out.println("noPrestados: " + noPrestados);
                    int noListaEspera = dis.readInt();
                    System.out.println("noListaEspera: " + noListaEspera);

                    TLibro libro = new TLibro(idioma, isbn, pais, titulo, autor, noLibros, noPrestados, noListaEspera, anio);
                    Biblioteca.add(libro); // Agregar el libro a la lista
                }

                dis.close();
                repositoriosCargados.add(pNomFichero);
                result = 1;

            } catch (FileNotFoundException fnf) {
                return 0; // Archivo no encontrado
            } catch (IOException e) {
                throw new RemoteException("Error al cargar el repositorio: " + e.getMessage());
            }
        }
        return result;
    }

    @Override
    public int GuardarRepositorio(int pIda, int pRepo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int NuevoLibro(int pIda, TLibro L, int pRepo) throws RemoteException {

        int result = 3;

        if (IdAdmin == -1 /*|| pIda != IdAdmin*/) {
            result = -1;
        } else if (pRepo > repositoriosCargados.size() || pRepo < 0) {
            result = -2;
        } else if (compararIsbn(L.getIsbn())) {
            result = 0;
        } else {
            Biblioteca.add(L);
            Repositorios.get(pRepo).aumentarNLibros();
            result = 1;
        }

        return result;
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

    @Override
    public List<TDatosRepositorio> DevolverRepositorios() throws RemoteException {
        return Repositorios;
    }
    
    @Override
    public  List<TLibro> DevolverBiblioteca() throws RemoteException {
        return Biblioteca;
    }

    private boolean compararIsbn(String isbn) {

        boolean encontrado = false;

        int i = 0;
        do {
            if (isbn.equals(Biblioteca.get(i))) {
                encontrado = true;
            } else {
                i++;
            }

        } while (!encontrado && i < totalLibros);

        return encontrado;
    }

}
