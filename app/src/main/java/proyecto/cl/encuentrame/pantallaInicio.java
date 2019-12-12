package proyecto.cl.encuentrame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class pantallaInicio extends AppCompatActivity {

    private AnimationDrawable animacion;

    private AnimationDrawable animacion2;
    private ImageView imageView;

    private ImageView imagenInicio;
    private Animation transicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_pantalla_inicio);
        getSupportActionBar().hide();
        imagenInicio = findViewById(R.id.imagenInicio);
        imagenInicio.setBackgroundResource(R.drawable.cargando);
        animacion = (AnimationDrawable) imagenInicio.getBackground();
        animacion.start();
        transicion = AnimationUtils.loadAnimation(this,R.anim.mitransicion);
        imagenInicio.startAnimation(transicion);
        transicion.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                siguienteActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public void siguienteActivity(){
        animacion.stop(); //Paramos el AnimationDrawable
        Intent intento = new Intent(this, LoginActivity.class); // Lanzamos SiguienteActivity
        startActivity(intento);
        finish(); //Finalizamos este activity
    }
}
