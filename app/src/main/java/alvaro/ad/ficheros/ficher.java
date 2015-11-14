package alvaro.ad.ficheros;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ficher extends AppCompatActivity {

    final static String FILE_NAME = "fich.txt";

    TextView respuesta;
    EditText mensaje;
    Button guardar;
    Button recuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficher);

        respuesta = (TextView) findViewById(R.id.leido);
        mensaje = (EditText) findViewById(R.id.mensaje);
        guardar = (Button) findViewById(R.id.agregar);
        recuperar = (Button) findViewById(R.id.recuperar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarFicher(mensaje.getText().toString());
            }
        });

        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leerFicher();
            }
        });
    }

    private void guardarFicher(String mensaje){
        //Creamos un fichero de salida que es privado a esta aplicación
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
            String cadenaOutput = new String(mensaje+"\n");
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeBytes(cadenaOutput);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void leerFicher(){
        //Creamos un nuevo fichero de entrada
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            DataInputStream dis = new DataInputStream(fis);
            byte[] leido = new byte[1000];
            dis.read(leido);
            respuesta.setText("He leído: " + new String(leido));
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
