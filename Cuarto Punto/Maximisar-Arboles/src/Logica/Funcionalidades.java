/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import ModuloArchivos.Escritura;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Soporte
 */
public class Funcionalidades {

    int maximoNumeroArboles = 0;
    int tmpNumeroArboles = 0;
    int tmparbol = 0;
    int costo = 0;
    public int umbral = 0;
    public ArrayList<Rodal> entrada = new ArrayList<>();
    public ArrayList<Rodal> mayorNumeroArboles = new ArrayList<>();
    public ArrayList<Rodal> mayorNumeroArbolesTemporal = new ArrayList<>();
    SimpleDateFormat cambio_Formato_Fecha = new SimpleDateFormat("dd/MM/yyyy");
    Rodal inicial_Rodal = new Rodal();
    public ArrayList<Rodal> Solucion = new ArrayList<>();

    /*Complejida O(n)
     Esta funcion se para en en un punto compara sino se solapa el rodal con los demas elementos del arreglo*/
    public ArrayList<Rodal> MaximoNumeroRodales(int inicio) {
        int punto = inicio;
        System.out.println("+++++++++++++ Punto " + punto + " entrada " + entrada.size());
        inicial_Rodal = entrada.get(punto);
        Solucion.add(inicial_Rodal);
        //Almaceno el valor de numero de arboles par saber quien es mayor
        mayorNumeroArbolesTemporal.add(inicial_Rodal);
        tmpNumeroArboles += inicial_Rodal.numero_arboles;
        tmparbol += inicial_Rodal.numero_arboles;

        //Este algoritmo resuel el problema del maximo numero de rodales permitido    
        for (int i = punto; i < entrada.size(); i++) {
            Date date1 = Solucion.get(Solucion.size() - 1).fecha_fin;
            Date date2 = entrada.get(i).fecha_inicio;

            if (!date2.before(date1)) {
                Solucion.add(entrada.get(i));
                tmpNumeroArboles += entrada.get(i).numero_arboles;
                tmparbol += entrada.get(i).numero_arboles;
                
                mayorNumeroArbolesTemporal.add(entrada.get(i));
                System.out.println(" Date1 " + cambio_Formato_Fecha.format(date1) + " Date2 " + cambio_Formato_Fecha.format(date2));
            }
        }
        return Solucion;
    }

    /*Complejida O(n)
     Esta funcion separa en un punto del arreglo que identifique com la varieable inicio y compara si no se solapa con todos los elementos anteriores 
     a el*/
    public ArrayList<Rodal> MaximoNumeroRodalesReverse(int inicio) {
        int punto = inicio - 1;
        System.out.println("Punto " + punto + " entrada " + entrada.size());
        ArrayList<Rodal> salida = new ArrayList<>();
        //entrada.
        //Este algoritmo resuel el problema del maximo numero de rodales permitido
        int contador = 1;
        for (int i = punto; i > 0; i--) {
            Date date1 = entrada.get(entrada.size() - contador).fecha_inicio;
            Date date2 = entrada.get(i).fecha_fin;

            if (date2.before(date1)) {
                salida.add(entrada.get(i));
                //Almacenamiento de valores de arboles
                tmpNumeroArboles += entrada.get(i).numero_arboles;
                tmparbol += entrada.get(i).numero_arboles;
                if (!mayorNumeroArbolesTemporal.contains(entrada.get(i))) {
                    mayorNumeroArbolesTemporal.add(entrada.get(i));
                }
                contador++;
                System.out.println(" Date1 Fecha donde esto inicio : " + cambio_Formato_Fecha.format(date1) + "  Date2 Fecha donde anterior fin: " + cambio_Formato_Fecha.format(date2));
            }
        }
        return salida;
    }
//Complejidad O(1)
    //Realiza las comparacion para saber si esta operacion obtuvo la mayor ganancia en arboles
    //Comparo el valor que ya existe y el nuevo si el nuevo es mayor almaceno el nuevo. de lo contrario lo ignoro
    //Ademas agrego la condicion de el umbral establecido

    public void Probar() {
        if (tmpNumeroArboles > maximoNumeroArboles && tmparbol <= umbral ) {
            maximoNumeroArboles = tmpNumeroArboles;
            costo = tmparbol;
            try {
                mayorNumeroArboles = (ArrayList<Rodal>) mayorNumeroArbolesTemporal.clone();
            } catch (Exception e) {
                JOptionPane.showInputDialog(this, "Si sale este Mensaja Algo anda Muy Mal ");
            }
            System.out.println("|||||||||||||||||||||| Ganador |||||||||||||| " + maximoNumeroArboles + " Costo " + costo);
        }
        tmpNumeroArboles = 0;
        tmparbol = 0;
        mayorNumeroArbolesTemporal.clear();

    }

    /*Funcion que imprime en pantalla full HD*/
    public void ImprimirPantalla(ArrayList<Rodal> entrada_arraylist) {

        ArrayList<Rodal> c = entrada_arraylist;
        for (Rodal c1 : c) {
            System.out.println("-- Imprimir --");
            System.out.println("Id " + c1.getId());
            System.out.println("Numero de Arboles " + c1.getNumero_arboles());
            System.out.println("Costo " + c1.getCosto_estimado());
            System.out.println("Inicio fecha " + cambio_Formato_Fecha.format(c1.getFecha_inicio()));
            System.out.println("Fin fecha " + cambio_Formato_Fecha.format(c1.getFecha_fin()));
            System.out.println("Numero empleados " + c1.getNumero_empleados());
        }

    }
//Imprime el informacion en un archivo plano
//
//    
    public void ImprimirArchivo(ArrayList<Rodal> entrada_arraylist) {
        Escritura escritura = new Escritura();
        ArrayList<Rodal> c = entrada_arraylist;
        escritura.setAddelement(c.size());
        for (int i = 0; i < c.size(); i++) {
            escritura.setAddelement("id: " + entrada_arraylist.get(i).id);
        }
        escritura.setArchivoWrite();
    }
}
