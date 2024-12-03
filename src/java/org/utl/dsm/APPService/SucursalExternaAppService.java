
package org.utl.dsm.APPService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.model.SaboresViewModel;

/**
 *
 * @author oscar
 */
public class SucursalExternaAppService {
    private List<SaboresViewModel> getAllApiPublica(String apiUrl) {
        List<SaboresViewModel> libros = new ArrayList<>();
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    content.append(line);
                }
                in.close();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<SaboresViewModel>>() {}.getType();
                libros = gson.fromJson(content.toString(), listType);
            } else {
                System.out.println("Error en la solicitud a " + apiUrl + ": " + status);
            }
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return libros;
    }

    public List<SaboresViewModel> apiSucursalExterna() {
        return getAllApiPublica("http://192.168.240.10:8080/DonGalleto/api/inv/getAll");
    }
}
