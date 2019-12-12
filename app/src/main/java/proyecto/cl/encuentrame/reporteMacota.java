package proyecto.cl.encuentrame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



public class reporteMacota extends AppCompatActivity {

    ImageView imageView3;
    private Button btnSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_macota);

        Button button= (Button) findViewById(R.id.button);
        imageView3 =(ImageView) findViewById(R.id.imageView3);
        btnSensor = (Button) findViewById(R.id.btnSensor);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView3.setImageBitmap(bitmap);
    }

    public void btnSensor(View view){
        Intent i = new Intent(this, sensorProximidad.class);
        startActivity(i);
    }

}
