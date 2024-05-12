/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cliente;


import biblioteca.GestorBibliotecaIntf;
import biblioteca.TDatosRepositorio;
import biblioteca.TLibro;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Angel
 */
public class Cliente {


    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args) {
        List<TDatosRepositorio> Repositorios = new ArrayList<>();
        List<TLibro> Biblioteca = new ArrayList<>();
        int result_int;
        int idAdmin = -1;
         try {
             int Puerto = 0;
             String Host;
             Scanner Teclado = new Scanner(System.in);
             
             System.out.println("Introduce el nº de puerto: ");
             Puerto = Teclado.nextInt();
             System.out.println("Introduce el nombre del host: ");
             Host = Teclado.next();
             
             Random rnd = new Random(System.nanoTime());
             
             GestorBibliotecaIntf biblio = (GestorBibliotecaIntf) Naming.lookup("rmi://"+Host+":"+Puerto+"/Biblioteca");
             
            int opc;
            
            do{
                opc = MenuPrincipal();
                
                switch(opc){
                    case 1:
                        int opc2;
                        do{
                            opc2 = MenuAdministracion();
                            
                            switch(opc2){
                                case 1:
                                    System.out.println("\n**CARGAR REPOSITORIO**"); 
                                    System.out.println("1.- Biblioteca.jdat_R1_");
                                    System.out.println("2.- Biblioteca.jdat_R2_");
                                    System.out.println("3.- Biblioteca.jdat_R3_");
                                    System.out.println("Elige opcion: ");
                                    int opc1 = Teclado.nextInt();
                                    String nomFich = "Biblioteca.jdat_R"+opc1+"_";
                                    result_int = biblio.AbrirRepositorio(14, nomFich);
        
                                     if (result_int == -1) {
                                            System.out.println("Error: El administrador no está autorizado.");
                                        } else if (result_int == -2) {
                                            System.out.println("Error: El repositorio ya esta cargado.");
                                        } else if (result_int == 0) {
                                            System.out.println("Error: El archivo no se pudo encontrar o abrir.");
                                        } else if (result_int == 1) {
                                            Repositorios = biblio.DevolverRepositorios();
                                            System.out.println("El repositorio se cargo con exito.");
                                        }
                                    
                                    break;
                                 case 2:
                                     System.out.println("\n**Guardar Repositorio**");
                                    break;
                                case 3:
                                    System.out.println("\n**NUEVO LIBRO**");
                                    
                                    String isbn, autor, titulo, pais, idioma;
                                    int anio, nLibrosIni;
                                           
                                    System.out.println("Introduce el Isbn: ");
                                    Teclado.nextLine();
                                    isbn = Teclado.nextLine();
                                    System.out.println("Introduce el Autor: ");
                                    autor = Teclado.nextLine();
                                    System.out.println("Introduce el Titulo: ");
                                    titulo = Teclado.nextLine();
                                    System.out.println("Introduce el anio: ");
                                    anio = Teclado.nextInt();
                                    System.out.println("Introduce el Pais: ");
                                    Teclado.nextLine();
                                    pais = Teclado.nextLine();
                                    System.out.println("Introduce el Idioma: ");
                                    idioma = Teclado.nextLine();
                                    System.out.println("Introduce Numero de libros inicial: ");
                                    nLibrosIni = Teclado.nextInt();
                                    
                                    MostrarRepositorios(Repositorios);
                                    
                                    System.out.println("Elige repositorio: ");
                                    int repo = Teclado.nextInt();
                                    
                                    TLibro nuevoLibro =new TLibro(idioma, isbn, pais, titulo, autor, nLibrosIni, 0, 0, anio);
                                    result_int = biblio.NuevoLibro(idAdmin, nuevoLibro, repo-1);
                                    
                                switch (result_int) {
                                    case -1 -> System.err.println("Ya hay un usuario identificado como administrador o su idAdmin es incorrecto");
                                    case -2 -> System.err.println("El repositorio cuya posicion se indica no existe");
                                    case 0 -> System.err.println(" Hay un libro en algún repositorio de la biblioteca que tiene el mismo Isbn");
                                    case 1 -> System.out.println("**Se ha añadido el nuevo libro al repositorio indicado**");

                                }
                                    
                                    break;


                                case 4:
                                    System.out.println("\n**Comprar Libros**");
                                    break;
                                case 5:
                                    System.out.println("\n**Retirar Libros**");
                                    break;
                                case 6:
                                    System.out.println("\n**Ordenar Libros**");
                                    break;
                                case 7:
                                    System.out.println("\n**Buscar Libros**");
                                    break;
                                case 8:
                                    System.out.println("\n**Listar Libros**");
                                    Biblioteca = biblio.DevolverBiblioteca();
                                    
                                    for (int i = 0; i < Biblioteca.size(); i++) {
                                        
                                        if(i == 0){
                                            Biblioteca.get(i).Mostrar(i, true);
                                        }else{
                                            Biblioteca.get(i).Mostrar(i, false);
                                        }
                                    }
                                    break;
                                case 0:
                                    break;
                            }
                        }while(opc2 != 0);
                        break;
                    case 2:    
                         System.out.println("\n**Consulta de libros**");    
                        break;
                    case 3:
                         System.out.println("\n**Prestamo de libros**");
                        break;
                    case 4:
                         System.out.println("\n**Devolucion de libros**");
                        break;
                    case 0:
                        break;
                }
                
            }while(opc != 0);
        
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
   
    }    

     public static int MenuPrincipal()
    {
        Scanner Teclado=new Scanner(System.in);
        int Salida;
        boolean valido = false;
            do{
            System.out.println("\nGESTOR BIBLIOTECARIO 2.0 (M.PRINCIPAL) ");
            System.out.println("****************************************");
            System.out.println("** 1.- M.Administracion");
            System.out.println("** 2.- Consulta de libros");
            System.out.println("** 3.- Prestamo de libros");
            System.out.println("** 4.- Devolucion de libros");
            System.out.println("** 0.- Salir");        
            System.out.print("** Elige Opcion: ");
            Salida=Teclado.nextInt();
            
            if(Salida>=0 && Salida<=4){
                valido = true;
            }else System.err.println("ERROR. Numero no valido");
            }while(!valido);
            
        return Salida;
    }
    
    private static int MenuAdministracion() 
            {
        Scanner Teclado=new Scanner(System.in);
        int Salida;
        boolean valido = false;
            do{
            System.out.println("\nGESTOR BIBLIOTECARIO 2.0 (M.ADMINISTRACION) ");
            System.out.println("****************************************");
            System.out.println("** 1.- Cargar Repositorio");
            System.out.println("** 2.- Guardar Repositorio");
            System.out.println("** 3.- Nuevo libro");
            System.out.println("** 4.- Comprar Libros");
            System.out.println("** 5.- Retirar Libros");
            System.out.println("** 6.- Ordenar Libros");
            System.out.println("** 7.- Buscar Libros");
            System.out.println("** 8.- Listar Libros");
            System.out.println("** 0.- Salir");        
            System.out.print("** Elige Opcion: ");
            Salida=Teclado.nextInt();
            
            if(Salida>=0 && Salida<=8){
                valido = true;
            }else System.err.println("ERROR. Numero no valido");
            }while(!valido);
            
        return Salida;
    }

    private static void MostrarRepositorios(List<TDatosRepositorio> rep) {
        
         System.out.println("POS\tNOMBRE\t\t\tDIRECCION\t\tNº LIBROS");
        System.out.println("**********************************************************************************************");
        int j = 0;
        for (int i = 0; i < rep.size() ; i++) {
            j++;
            System.out.println(j + "\t" + rep.get(i).getNombre() +"\t\t\t" + rep.get(i).getDireccion() +"\t\t" + rep.get(i).getnLibros());
        }
        
    }
}
