
package recursohumano;

import java.sql.SQLException;
import java.util.Scanner;

public class Create {
    Create() throws SQLException{
        Scanner leer = new Scanner(System.in);
        Persona person = new Persona();     //Objeto person de la clase persona.
        System.out.println("<< CREAR REGISTRO >>");
        
        System.out.println("Nombre: ");
        person.setNomPersona(leer.nextLine());
        
        System.out.println("Correo Electrónico: ");
        person.setEmailPersona(leer.nextLine());
        
        System.out.println("Teléfono: ");
        person.setTelPersona(leer.nextLine());
        
        String tabla = "tb_contacto";
        String camposTabla = "nom_contacto, email_contacto, tel_contacto";
        String valoresCampos ="'" + person.getNomPersona() + "','" + person.getEmailPersona() + "','" + person.getTelPersona() + "'";
        //Instancia u objeto de la clase ConexionCRUD
        ConexionCRUD utilerias = new ConexionCRUD();
        //Se envian los parámetros necesarios para guardar el registro al método guardarRegistros.
        utilerias.guardarRegistros(tabla, camposTabla, valoresCampos);
        //Llamada la método del menú principal.
        MenuPrincipal.desplegarMenu();
    }
}
