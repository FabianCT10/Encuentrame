package proyecto.cl.encuentrame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Text;

public class registrar_Mascota extends AppCompatActivity {

    private EditText nombre;
    private EditText fechaNacimiento;
    private Spinner tipoMascota;
    private EditText descripcion;
    private EditText codigoMascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__mascota);

        nombre = findViewById(R.id.txt_nombre);
        fechaNacimiento = findViewById(R.id.txt_fechaNac);
        tipoMascota = findViewById(R.id.tipo_mascota);
        descripcion = findViewById(R.id.txt_descripcion);
        codigoMascota = findViewById(R.id.txt_código);

    }

    public void RegistrarMascota(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Base de datos",null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombreMascota = nombre.getText().toString();
        String fechaNac = fechaNacimiento.getText().toString();
        String tipoDeMascota = tipoMascota.getSelectedItem().toString();
        String detalle = descripcion.getText().toString();
        String codigo = codigoMascota.getText().toString();

        if(!nombreMascota.isEmpty() && !fechaNac.isEmpty() && !tipoDeMascota.isEmpty() && !detalle.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("nombre",nombreMascota);
            registro.put("fechaNacimiento", fechaNac);
            registro.put("tipo", tipoDeMascota);
            registro.put("descripcion", detalle);

            BaseDeDatos.insert("mascota",null,registro);
            BaseDeDatos.close();

            nombre.setText("");
            fechaNacimiento.setText("");
            descripcion.setText("");
            codigoMascota.setText("");

            Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Debe llenar todos los campos", Toast.LENGTH_SHORT).show();

        }
    }

    public void Buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Base de datos",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = codigoMascota.getText().toString();

        if(!codigo.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("select nombre, descripcion from mascota",null);
            if(fila.moveToFirst()){
                nombre.setText(fila.getString(0));
                descripcion.setText(fila.getString(1));
                BaseDeDatos.close();
            } else{
                Toast.makeText(this,"No existe el artículo", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        }else {
            Toast.makeText(this,"Debe ingresar el código de la mascota", Toast.LENGTH_SHORT).show();
        }
    }

    public void Eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Base de datos",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = codigoMascota.getText().toString();
        if (!codigo.isEmpty()){
            int cantidad = BaseDeDatos.delete("mascota", "codigo="+ codigo, null);
            BaseDeDatos.close();
            nombre.setText("");
            fechaNacimiento.setText("");
            descripcion.setText("");
            if(cantidad==1){
                Toast.makeText(this,"Mascota eliminada", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"La mascota no existe", Toast.LENGTH_SHORT).show();

            }

        }else {
            Toast.makeText(this,"Debe ingresar el código de la mascota", Toast.LENGTH_SHORT).show();

        }
    }
}
