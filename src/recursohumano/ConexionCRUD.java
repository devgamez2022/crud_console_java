package recursohumano;

import java.sql.*;

public class ConexionCRUD {
    private final String servidor = "jdbc:mysql://127.0.0.1:3306/bd_recurso_humano";
    
    //Nombre del usuario (root por defecto) de la base de datos.
    private final String usuario = "root";
    //Clave del usuario de la base de datos.
    private final String clave = "";
    //Libreria de mysql.
    private final String driverConector = "com.mysql.jdbc.Driver";
    //Objeto de la clase Connection del paquete java.sql
    private static Connection conexion;
    
    //Estableciendo los métodos para la conexión a la BD con las variables (constantes) anteriores.
    public ConexionCRUD(){
    try{
        Class.forName(driverConector);  //Levantar el driver.
        conexion = DriverManager.getConnection(servidor,usuario,clave);
    }catch (ClassNotFoundException | SQLException e){
        System.out.println("Conexión fallida! Error! : "+e.getMessage());
    }
    }
    
    public Connection getConnection(){
        return conexion;               //Devuelve el objeto conexión.
    }
    
    public static void main(String[] args) throws SQLException {
        ConexionCRUD objeto = new ConexionCRUD();
        
        /*Bloque de código para probar guardar registro
        String tabla = "tb_contacto";
        String camposTabla = "nom_contacto, email_contacto, tel_contacto";
        String valoresCampos = "'IRCA', 'IRCA2022@itca.edu.sv', '2334-1122'";
        objeto.guardarRegistros(tabla, camposTabla, valoresCampos);*/
        
        /*Bloque de código para probar consulta
        String tablaBuscar = "tb_contacto";
        String camposBuscar = "nom_contacto, email_contacto, tel_contacto";
        String condicionBuscar = "";
        objeto.desplegarRegistros(tablaBuscar, camposBuscar, condicionBuscar);*/
        
        /*Bloque de código para eliminar registro
        String tabla = "tb_contacto";
        String valoresCamposNuevos = "";
        String condicion = "id_contacto = 3";
        objeto.actualizarEliminarRegistro(tabla, valoresCamposNuevos, condicion);*/
        
        /*Bloque de código para actualizar registro
        String tabla = "tb_contacto";
        String valoresCamposNuevos = "nom_contacto='ITCA-FEPADE', email_contacto='manuel.gamez@itca.edu.sv', tel_contacto='6110-7065'";
        String condicion = "id_contacto = 1";
        objeto.actualizarEliminarRegistro(tabla, valoresCamposNuevos, condicion);*/
        
    }
    
    //Creando el método para guardar registros en la BD.
    public void guardarRegistros(String tabla, String camposTabla, String valoresCampos){
        ConexionCRUD conectar = new ConexionCRUD();
        Connection cone = conectar.getConnection();
        
        try{
            //Definir la sentencia SQL.
            //String sqlQueryStmt = "insert into " + tabla + "(" + camposTabla + ") values "
            //        + "(" + valoresCampos + ")";
            
            String sqlQueryStmt = "INSERT INTO " + tabla + " (" + camposTabla + ") VALUES (" + valoresCampos + ");";
            //Estableciendo la comunicación entre nuestra aplicación java y la base de datos.
            Statement stmt;                      //Envia sentencias sql a la base de datos.
            stmt = cone.createStatement();
            stmt.executeUpdate(sqlQueryStmt);    //Ejecutar la sentencia SQL.
            //Cerrar el statement y la conexión; se cierran en orden inverso de como se han abierto.
            stmt.close();
            cone.close();
            System.out.println("Registro guardado correctamente");      
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
      }
        
        //Método para actualizar y eliminar los registros de la base de datos.
        public void actualizarEliminarRegistro(String tabla, String valoresCamposNuevos, String condicion){
        
        ConexionCRUD conectar = new ConexionCRUD();
        Connection cone = conectar.getConnection();
        
        try{
            Statement stmt;
            String sqlQueryStmt;
            //Verificar que valoresCamposNuevos venga vacia y asi seleccionar si es borrar o actualizar registro.
            if(valoresCamposNuevos.isEmpty()){
                sqlQueryStmt = "DELETE FROM " + tabla + " WHERE " + condicion + ";";
                System.out.println("Registro eliminado");
            }else{
                sqlQueryStmt = "UPDATE " + tabla + " SET " + valoresCamposNuevos + " WHERE " + condicion + ";";
                System.out.println("Registro actualizado");
            }
            
            stmt = cone.createStatement();
            stmt.executeUpdate(sqlQueryStmt);    //Ejecutar la sentencia SQL.
            //Cerrar el statement y la conexión; se cierran en orden inverso de como se han abierto.
            stmt.close();
            cone.close();
            //System.out.println("Operación completada correctamente.");
        }catch(Exception e){
            System.out.println("Ha ocurrido el siguiente error: " + e.getMessage());
        }
    }
        
        //Método para hacer consultas en la BD.
        public void desplegarRegistros (String tablaBuscar, String camposBuscar, String condicionBuscar) throws SQLException{
            ConexionCRUD conectar = new ConexionCRUD();
            Connection cone = conectar.getConnection();
        
            try{
                Statement stmt;
                String sqlQueryStmt;
                if(condicionBuscar.equals("")){
                    sqlQueryStmt = "SELECT " + camposBuscar + " FROM " + tablaBuscar + ";";
                }else{
                    sqlQueryStmt = "SELECT " + camposBuscar + " FROM " + tablaBuscar + " WHERE " + condicionBuscar;
                }
                stmt = cone.createStatement();
                stmt.executeQuery(sqlQueryStmt);
                
                //Le indicamos que ejecute la consulta de la tabla y le pasamos por argumentos nuestra sentencia.
                try(ResultSet miResultSet = stmt.executeQuery(sqlQueryStmt)){
                    if(miResultSet.next()){   //Ubica el cursor en la primera fila de la tabla de resultado.
                        ResultSetMetaData metaData = miResultSet.getMetaData();
                        int numColumnas = metaData.getColumnCount();  //Obtiene el número de columnas de la consulta.
                        System.out.println("<<REGISTROS ALMACENADOS>>");
                        System.out.println("Registros encontrados " +numColumnas);
                        System.out.println();
                        for(int i=1;i<=numColumnas;i++){
                            //Muestra los títulos de las columnas y %-20s\t indica la separación entre columnas.
                            System.out.printf("%-20s\t", metaData.getColumnName(i));
                        }
                        System.out.println();
                        System.out.println("---------------------------------------------------------------------------------------");
                        
                        do{
                            for (int i=1;i<=numColumnas;i++){
                                System.out.printf("%-20s\t",miResultSet.getObject(i));
                            }
                            System.out.println();  
                        }while(miResultSet.next());
                        System.out.println();                                          
                    }else{
                        System.out.println("No se han encontrado registros.");
                    }
                    miResultSet.close();
                
                }finally{
                    //Cerrar el Statement y la conexión, se cierran en orden inverso 
                   stmt.close();
                   cone.close();
                }
                
                }catch(SQLException ex){
                    System.out.println("Ha ocurrido el siguiente error: " + ex);
                }
               
        }
}
    