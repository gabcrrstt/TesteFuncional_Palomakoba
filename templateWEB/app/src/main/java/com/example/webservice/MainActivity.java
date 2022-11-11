package com.example.webservice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MainActivity extends AppCompatActivity
    {
    private Button btnRecupera, btnRefresh;
    private TextView txtResultado;
    private EditText edtcep;

    private RecyclerView rvlista;

    private Sensor acelerometro;
    private SensorManager sensorManager;
    private SensorEventListener listener;
    //private BroadcastBateria bateria = new BroadcastBateria();

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRefresh = findViewById(R.id.btnRefresh);

        btnRecupera = findViewById(R.id.btnRecupera);
        txtResultado = findViewById(R.id.txtResultado);
        edtcep        = findViewById(R.id.edtCEP);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //registerReceiver(bateria, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

/*        if(bateria == true){
            Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
        }*/

            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            listener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    if(sensorEvent.values[0] > 10){
                        finish();
                    }
                }
                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {
                }
            };

        Activity    act = this;

        Listar(act);

        btnRecupera.setOnClickListener(new View.OnClickListener()
            {
            @Override
            public void onClick(View view)
                {
                MyTask task = new MyTask();
                String urlApi = "https://viacep.com.br/ws/"+edtcep.getText().toString()+"/json/";

                task.AddTxtLoading(findViewById(R.id.txtResultado), act);
                edtcep.setText("");
                task.execute(urlApi);

                txtResultado.setText("Carregando...");
                }
            });




        //https://www.javatpoint.com/android-recyclerview-list-example
        }


        public void Listar(Activity act)
            {
            rvlista         = findViewById(R.id.rvLista);

            //List<DBCep> dbcep = new ArrayList<>();

            CEPDAO      cepdao  = new CEPDAO(getApplicationContext());

                //dbcep = cepdao.listar();

            //dbcep   = cepdao.listar();
            // dbcep.add(new DBCep("cep", "end", "comp", "brr", "loc", "es", 01L) );
            // dbcep.add(new DBCep("cep1", "end1", "comp1", "brr", "loc", "es", 02L) );

            CEPAdapter adapter  = new CEPAdapter(cepdao.listar());

            rvlista.setHasFixedSize(true);
            rvlista.setLayoutManager(new LinearLayoutManager(this.getApplicationContext() ));
            rvlista.setAdapter(adapter);


                //Log.i("val","tam: "+dbcep.size());

                rvlista.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), rvlista, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(getApplicationContext(), "FOI Joe", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        DialogResp dialogResp = new DialogResp();

                        List<DBCep> dbcep = new ArrayList<>();

                        dbcep = cepdao.listar();

                        dialogResp.YNAlterData("", act, dbcep.get(position) , 2);
                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }));
            }//*/



    }