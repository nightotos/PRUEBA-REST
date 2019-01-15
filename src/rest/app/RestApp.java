package rest.app;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
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
    System.out.println(jsonobject.get("website"));
    }
     
     
     conexion.disconnect();   
    }
    
}
