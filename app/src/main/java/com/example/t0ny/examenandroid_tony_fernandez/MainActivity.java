package com.example.t0ny.examenandroid_tony_fernandez;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final Integer ALTA = 100;
    private static final Integer BAJA = 200;
    public static final Integer LISTAR = 300;
    public static final Integer EDITAR = 400;
    private ArrayList<Contacto> listaContactos = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnalta = (Button) findViewById(R.id.botonadd);
        btnalta.setOnClickListener(this);
        Button btnborrar = (Button) findViewById(R.id.botonborrar);
        btnborrar.setOnClickListener(this);
        Button btnlistar = (Button) findViewById(R.id.botonvertodos);
        btnlistar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.botonadd:
                intent = new Intent(this, GrabarDatos.class);
                startActivityForResult(intent, ALTA);
                break;
            case R.id.botonborrar:
                intent = new Intent(this, BorrarDatos.class);
                startActivityForResult(intent, BAJA);
                break;
            case R.id.botonvertodos:
                intent = new Intent(this, ListarDatos.class);
                intent.putExtra("listacontactos", listaContactos);
                startActivityForResult(intent, LISTAR);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ALTA == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.hasExtra("alta")) {
                    listaContactos.add((Contacto) data.getParcelableExtra("alta"));
                }
            }
        } else {
            if (BAJA == requestCode) {
                if (resultCode == Activity.RESULT_OK) {
                    if (data.hasExtra("borrar")) {
                        Contacto contacto = data.getParcelableExtra("borrar");
                        Toast.makeText(this, listaContactos.remove(contacto) ? "Se ha borrado un contacto" : "No existe un contacto con esos datos", Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                if (LISTAR == requestCode) {
                    if (resultCode == Activity.RESULT_OK) {
                        if (data.hasExtra("listaContactos")) {
                            listaContactos = data.getParcelableArrayListExtra("listacontactos");
                        }
                    }
                }else{
                    if(EDITAR == requestCode){
                        if(resultCode == Activity.RESULT_OK){
                            if(data.hasExtra("editar")){
                                listaContactos=data.getParcelableArrayListExtra("editar");
                            }
                        }
                    }
                }
            }
        }
    }
}
