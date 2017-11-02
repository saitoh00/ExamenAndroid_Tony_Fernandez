package com.example.t0ny.examenandroid_tony_fernandez;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GrabarDatos extends AppCompatActivity implements View.OnClickListener {
    private AlertDialog ventana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grabar_datos);
        Button btnaceptar=(Button)findViewById(R.id.botonaceptar);
        btnaceptar.setOnClickListener(this);
        Button btncancelar=(Button)findViewById(R.id.botoncancelarguardar);
        btncancelar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.botonaceptar:
                Contacto contacto=crearContacto();
                if(contacto!=null){
                    Intent intent = new Intent();
                    intent.putExtra("alta", contacto);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case R.id.botoncancelarguardar:
                if(ventana==null){
                    ventana = CreateDialog();
                }
                ventana.show();
                break;
        }

    }

    public AlertDialog CreateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Estás seguro de que deseas cancelar?");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                GrabarDatos.this.finish();
            }
        });
        return builder.create();
    }

    public Contacto crearContacto(){
        EditText nombre=(EditText)findViewById(R.id.enombre);
        EditText apellidos = (EditText)findViewById(R.id.eapellidos);
        EditText telefono = (EditText)findViewById(R.id.etelefono);
        Contacto contacto = new Contacto(nombre.getText().toString(), apellidos.getText().toString(), telefono.getText().toString());
        return contacto;
    }

}
