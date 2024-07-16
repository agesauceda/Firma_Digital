package com.example.firmadigital;

import static com.example.firmadigital.Adapter.draw_signature.isTouched;


import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firmadigital.Adapter.draw_signature;
import com.example.firmadigital.Class.list_signatures;
import com.example.firmadigital.Repository.database;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    private draw_signature dw_sign;
    Button btnguardar, btnmostrar;
    TextView txtdescripcion;
    database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnguardar = (Button) findViewById(R.id.btnGuardar);
        btnmostrar = (Button) findViewById(R.id.btnMostrar);
        txtdescripcion = (TextView) findViewById(R.id.txtDescripcion);
        dw_sign = findViewById(R.id.firma_digital);

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validar() == true){
                    SaveData();
                }else{
                    msg_adv();
                }

            }
        });

        btnmostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent list_firma = new Intent(getApplicationContext(), list_signatures.class);
                startActivity(list_firma);
            }
        });
    }
    private void SaveData()
    {
        try {
            db = new database(this, database.DATABASE_NAME, null, 1);
            SQLiteDatabase sql =  db.getWritableDatabase();
            byte[] firma =  GetSignature();


            ContentValues value = new ContentValues();
            value.put(database.Descripcion, txtdescripcion.getText().toString());
            value.put(database.FirmaDigital, firma);

            Long Result = sql.insert(database.TABLE_NAME, database.Id, value);

            if (Result != -1) {
                msg();
            } else {
                msg_error();
            }
            sql.close();
        }
        catch (Exception exception)
        {
            Log.d("El error","" + exception);
            msg_error();
        }

    }
    public byte[] GetSignature() {
        Bitmap signatureBitmap = dw_sign.getSignatureBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        signatureBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public boolean validar(){
        boolean retorno = true;

        if(txtdescripcion.getText().toString().isEmpty()){
            retorno = false;
        }

        if(isTouched == false){
            retorno = false;
        }
        return retorno;
    }

    private void showAlert(String title, String message, String positiveButtonText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void msg_adv() {
        if (txtdescripcion.getText().toString().isEmpty()) {
            showAlert("ADVERTENCIA", "¡Escriba una descripción!", "Cerrar");
        } else if (isTouched) {
            showAlert("ADVERTENCIA", "¡Dibuje una firma!", "Cerrar");
        }
    }

    private void msg() {
        isTouched = false;
        showAlert("REGISTRO EXITOSO", "¡La firma se creó con éxito!", "Aceptar");
        dw_sign.clearCanvas();
        txtdescripcion.setText("");
    }

    private void msg_error() {
        showAlert("ERROR", "¡Error al guardar firma!", "Aceptar");
    }

}