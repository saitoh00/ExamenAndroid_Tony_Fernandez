package com.example.t0ny.examenandroid_tony_fernandez;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditarDatos extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private Contacto contacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_datos);
        EditText nombre=(EditText)findViewById(R.id.edicionnombrecontacto);
        EditText apellidos=(EditText)findViewById(R.id.edicionapellidoscontacto);
        EditText telefono=(EditText)findViewById(R.id.ediciontelefonocontacto);
        intent = getIntent();
        contacto=intent.getParcelableExtra("contacto");
        nombre.setText(contacto.getNombre());
        apellidos.setText(contacto.getApellidos());
        telefono.setText(contacto.getTelefono());
        Button btnedicion=(Button)findViewById(R.id.botonguardarcambios);
        btnedicion.setOnClickListener(this);
        Button btncancelaredicion=(Button)findViewById(R.id.botoncancelareditar);
        btncancelaredicion.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.botonguardarcambios:
                Contacto contacto=editarContacto();
                intent= new Intent(this,MainActivity.class);
                intent.putExtra("editar", contacto);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.botoncancelareditar:
                finish();
                break;
        }
    }
    private Contacto editarContacto(){
        EditText nombre=(EditText)findViewById(R.id.edicionnombrecontacto);
        EditText apellidos=(EditText)findViewById(R.id.edicionapellidoscontacto);
        EditText telefono=(EditText)findViewById(R.id.ediciontelefonocontacto);
        Contacto contacto = new Contacto(nombre.getText().toString(), apellidos.getText().toString(),telefono.getText().toString());
        return contacto;
    }
}
