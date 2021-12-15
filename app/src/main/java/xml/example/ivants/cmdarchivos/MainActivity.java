package xml.example.ivants.cmdarchivos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public Button crear, leer;
    public TextView texto;
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crear = (Button)findViewById(R.id.crear);
        leer = (Button)findViewById(R.id.leer);
        texto = (TextView)findViewById(R.id.texto);
        edit = (EditText)findViewById(R.id.edit);

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edit.getText().toString();

                try {
                    FileOutputStream fos= openFileOutput("archivo.txt", MODE_PRIVATE);
                    try {
                        fos.write(text.getBytes());
                        fos.close();
                        texto.setText("Se ha creado el archivo");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });

        leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput("archivo.txt");
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    StringBuffer buffer = new StringBuffer();

                    while(bis.available() != 0){
                        char c = (char) bis.read();
                        buffer.append(c);
                    }

                    texto.setText(buffer.toString());
                    bis.close();
                    fis.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        File file = getFilesDir();
        String path = file.getAbsolutePath();
        texto.setText(path);
    }
}
