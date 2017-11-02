package com.example.t0ny.examenandroid_tony_fernandez;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BorrarDatos extends AppCompatActivity implements View.OnClickListener {
    private AlertDialog ventana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_datos);
        Button btnborrar=(Button)findViewById(R.id.botonborrarcontacto);
        btnborrar.setOnClickListener(this);
        Button btncancelar=(Button)findViewById(R.id.botoncancelarborrar);
        btncancelar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.botonborrarcontacto:
                if (ventana == null) {
                    ventana = CreateDialog();
                }
                ventana.show();
                break;
            case R.id.botoncancelarborrar:

                break;
        }

    }

    private AlertDialog CreateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Estás seguro de que quieres borrar éste contacto?  Esta operación será permanente e irreversible.");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Contacto contacto = borrarContacto();
                if(contacto!=null){
                    Intent intent = new Intent();
                    intent.putExtra("borrar", contacto);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        builder.setNegativeButton("cancelar y volver al menú", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                BorrarDatos.this.finish();
            }
        });
        return builder.create();
    }

    private Contacto borrarContacto(){
        EditText nombre=(EditText)findViewById(R.id.bnombre);
        EditText apellidos=(EditText)findViewById(R.id.bapellidos);
        EditText telefono=(EditText)findViewById(R.id.btelefono);
        Contacto contacto = new Contacto(nombre.getText().toString(),apellidos.getText().toString(),telefono.getText().toString());
        return contacto;
    }
}
