package bistu.rookie.u_nity.androidproject_19;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private final static String MyFileName = "SD-File";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button writeButton=(Button)findViewById(R.id.WriteButton);
        Button readButton=(Button)findViewById(R.id.ReadButton);

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutputStream out = null;
                try {
                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        File file = Environment.getExternalStorageDirectory();
                        File myfile = new File(file.getCanonicalPath() + "/" + MyFileName);

                        FileOutputStream fileOutputStream = new FileOutputStream(myfile);
                        out = new BufferedOutputStream(fileOutputStream);
                        String content = "U-nity 2014011456 " + MyFileName;
                        try {
                            out.write(content.getBytes(StandardCharsets.UTF_8));
                        } finally {
                            if (out != null)
                                out.close();
                            Toast.makeText(MainActivity.this, "Write Success!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream in = null;
                try {
                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        File file = Environment.getExternalStorageDirectory();
                        File myfile = new File(file.getCanonicalPath() + "/" + MyFileName);
                        FileInputStream fileInputStream = new FileInputStream(myfile);
                        in = new BufferedInputStream(fileInputStream);

                        int c;
                        StringBuilder builder = new StringBuilder("");
                        try {
                            while ((c = in.read()) != -1) {
                                builder.append((char) c);
                            }
                            Toast.makeText(MainActivity.this, builder.toString(), Toast.LENGTH_LONG).show();
                        } finally {
                            if (in != null)
                                in.close();
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


}
