/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cliente;


import biblioteca.GestorBibliotecaIntf;
import java.rmi.Naming;
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
        
        int result_int;
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
                                    System.out.println("\n**Cargar Repositorio**"); 
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
                                            System.out.println("Error: El repositorio ya está cargado.");
                                        } else if (result_int == 0) {
                                            System.out.println("Error: El archivo no se pudo encontrar o abrir.");
                                        } else if (result_int == 1) {
                                            System.out.println("El repositorio se cargó con éxito.");
                                        }
                                    
                                    break;
                                 case 2:
                                     System.out.println("\n**Guardar Repositorio**");
                                    break;
                                case 3:
                                    System.out.println("\n**Nuevo libro**");
                                    //biblio.NuevoLibro(int, TLibro, int);
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
}
