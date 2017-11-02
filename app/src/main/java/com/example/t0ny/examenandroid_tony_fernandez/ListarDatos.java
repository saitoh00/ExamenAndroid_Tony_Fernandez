package com.example.t0ny.examenandroid_tony_fernandez;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListarDatos extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView lista;
    private ArrayAdapter adapter;
    private AlertDialog ventana;
    private Contacto contacto;
    private ArrayList<Contacto> contactos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_datos);
        Intent intent = getIntent();
        Button btnvolveratras=(Button)findViewById(R.id.botonvueltaatras);
        btnvolveratras.setOnClickListener(this);

        lista=(ListView)findViewById(R.id.listafinal);

        contactos=(ArrayList<Contacto>) intent.getSerializableExtra("listacontactos");

        adapter=new ArrayAdapter<Contacto>(this,android.R.layout.simple_list_item_1,
                (ArrayList)intent.getSerializableExtra("listacontactos"));
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(this);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        finish();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(ventana==null){
            ventana=CreateDialog(contactos.get(i));

        }
        ventana.show();
    }



    private AlertDialog CreateDialog(final Contacto contact) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Estás seguro de que quieres editar este contacto?");
        builder.setPositiveButton("ok",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent;
                intent = new Intent(ListarDatos.this, EditarDatos.class);
                contacto = contact;
                intent.putExtra("contacto", contact);
                startActivityForResult(intent, MainActivity.EDITAR);
            }
        });
        builder.setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return builder.create();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(MainActivity.EDITAR==requestCode) {
            if(resultCode == Activity.RESULT_OK){
                if(data.hasExtra("editar")){
                    Contacto editado=data.getParcelableExtra("editar");
                    contactos.remove(contacto);
                    contactos.add(editado);
                    adapter=new ArrayAdapter<Contacto>(this,android.R.layout.simple_list_item_1,contactos);
                    lista.setAdapter(adapter);
                }

            }
        }
    }
}
