package recursohumano;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete {
    Delete() throws SQLException{
        Scanner leer = new Scanner(System.in);
        ConexionCRUD utilerias = new ConexionCRUD();
        System.out.println("<< ELIMINAR REGISTROS >>");
        System.out.println("Ingrese el id del registro: ");
        String idContactoEliminar = leer.next();
        //Reingreso de datos para actualizar.
        String tabla = "tb_contacto";
        String campos = "*";
        String condicion = "id_contacto = " + idContactoEliminar;
        utilerias.desplegarRegistros(tabla, campos, condicion);
        System.out.println("Presionar << Y >> para confirmar: ");
        String confirmarBorrar = leer.next();
        if("Y".equals(confirmarBorrar)){
            String valoresCamposNuevos = "";
            utilerias.actualizarEliminarRegistro(tabla, valoresCamposNuevos, condicion);
            System.out.println("Registro borrado satisfactoriamente");
            
        }
        MenuPrincipal.desplegarMenu();
    }
}
