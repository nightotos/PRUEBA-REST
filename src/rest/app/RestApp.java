package rest.app;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

public class RestApp {

    
    public static void main(String[] args) throws MalformedURLException, IOException {
       
     URL url = new URL("https://jsonplaceholder.typicode.com/users"); 
     HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
     conexion.setRequestMethod("GET");
     conexion.setRequestProperty("Accept", "application/json");
     
     InputStreamReader dat = new InputStreamReader(conexion.getInputStream());
     BufferedReader buffer = new BufferedReader(dat);
     String salida = null;
     String datos_global;
     datos_global = "";
      
     while ((salida = buffer.readLine()) != null) {
               
        datos_global+=salida;
                         
            }
    
     JSONArray datos_array=new JSONArray(datos_global);
     
     
    Connection miConexion;
        
        String ruta="jdbc:mysql://localhost:3306/java";
        String user="java";
        String password="java"; 
     
    for(int y=0;y<datos_array.length();y++){
    JSONObject jsonobject=datos_array.getJSONObject(y);
      
    try {
            miConexion=DriverManager.getConnection(ruta, user, password);
            //CONEXION ALA BASE DE DATOS
            
            String sql;

            JSONObject jsonobject2=(JSONObject) jsonobject.get("address");
            JSONObject jsonobject3=(JSONObject) jsonobject2.get("geo");
            JSONObject jsonobject4=(JSONObject) jsonobject.get("company");
            
           
        sql = "INSERT INTO `usuario`( `name`, `username`, `email`, `street`, `suite`, `city`, `zipcode`, `lat`, `lng`, `phone`, `website`, `co_name`, `catchPhrase`, `bs`) VALUES"
                +"( '"+jsonobject.get("name")+"'"
                + ", '"+jsonobject.get("username")+"' "
                + ", '"+jsonobject.get("email")+"' "
                + ", '"+jsonobject2.get("street")+"' "
                + ", '"+jsonobject2.get("suite")+"' "
                + ", '"+jsonobject2.get("city")+"' "
                + ", '"+jsonobject2.get("zipcode")+"' "
                + ", '"+jsonobject3.get("lat")+"' "
                + ", '"+jsonobject3.get("lng")+"' "
                + ", '"+jsonobject.get("phone")+" '"
                + ", '"+jsonobject.get("website")+" '"
                + ", '"+jsonobject4.get("name")+"' "
                + ", '"+jsonobject4.get("catchPhrase")+" '"
                + ", '"+jsonobject4.get("bs")+"' );";
        
        
            System.out.println(sql);
            
            Statement clausula;
            
            clausula=miConexion.createStatement();
            
            clausula.execute(sql);
            //PREPARAR LA CONSULTA
            
            //EJECUTAR EL SQL
            
            //RECORRER EL RESULTADO
            
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    
    
    }
     
     JOptionPane.showMessageDialog(null,"CONSULTA FINALIZADA EXITOSAMENTE" );
     conexion.disconnect(); 
     
    }
    
}
