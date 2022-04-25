package recursohumano;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuPrincipal {
    
    public static void main(String[] args) throws SQLException {
        desplegarMenu();
    }
    
    public static void desplegarMenu() throws SQLException{
        Scanner opcionSeleccionada = new Scanner(System.in);
        String opcionMenu;
        
        System.out.println("**************************************");
        System.out.println("|        CRUD JAVA EN CONSALA        |");
        System.out.println("**************************************");
        
        System.out.println("|   Opciones:                        |");
        System.out.println("|         1. Crear Registros         |");
        System.out.println("|         2. Consultar Registros     |");
        System.out.println("|         3. Actualizar Registros    |");
        System.out.println("|         4. Eliminar Registros      |");
        System.out.println("|         5. Salir                   |");
        System.out.println("**************************************");
        
        System.out.print("Seleccionar opción: ");
        opcionMenu = opcionSeleccionada.next();
        
        switch(opcionMenu){
            case "1":
                Create create = new Create();
                break;
                
            case "2":
                Read read = new Read();
                break;
                
            case "3":
                Update updata = new Update();
                break;
                
            case "4":
                Delete delete = new Delete();
                break;
                
            case "5":
                System.out.println("Salir");
                break;
                
            default:
                System.out.println("Selección inválida!");
                break;          
        }
    }
    
}
