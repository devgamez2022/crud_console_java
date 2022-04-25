package recursohumano;

import java.util.Scanner;

public class pruebas {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese el nombre de la tabla");
        String tabla = leer.nextLine();
        System.out.println("Ingrese listado de campos de la tabla");
        String camposTabla = leer.nextLine();
        System.out.println("Ingrese los valores de cada campo");
        String valoresCampos = leer.nextLine();
        
        //String sqlQueryStmt = "insert into " + tabla + "(" + camposTabla + ") values "
        //            + "(" + valoresCampos + ")";
        
        String sqlQueryStmt = "INSERT INTO " + tabla + " (" + camposTabla + ") VALUES (" + valoresCampos + ");";
        
        System.out.println(sqlQueryStmt);
    }
}
