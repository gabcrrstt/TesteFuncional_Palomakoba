package com.example.webservice;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MyTask  extends AsyncTask<String, Void, String>
{

    private        TextView     txtresp;

    public          Activity    act;

    public  void AddTxtLoading(TextView ttx, Activity activity)
        {
        txtresp = ttx;

        act = activity;
        }

    @Override
    protected String doInBackground(String... strings) {
        String stringUrl = strings[0];
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        StringBuffer stringBuffer = new StringBuffer();


        try {
            URL url = new URL(stringUrl);
            HttpsURLConnection conexao = (HttpsURLConnection) url.openConnection();
            inputStream = conexao.getInputStream();

            inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader reader = new BufferedReader(inputStreamReader);
            String linha = "";
            while ( (linha = reader.readLine() ) != null)
            {
                stringBuffer.append(linha);
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();;
        }

        return stringBuffer.toString();
    }


    @Override
    //protected void onPostExecute(String s) {
    public void onPostExecute(String s) {
        super.onPostExecute(s);

        DialogResp dialogResp = new DialogResp();

        DBCep ceep = new DBCep();

        /*String logradouro = null;
        String cep = null;
        String complemento = null;
        String bairro = null;
        String localidade = null;
        String uf = null;//*/

        StringBuilder sb = new StringBuilder();

        try {
            JSONObject jsonObject = new JSONObject(s);

            ceep.logradouro = jsonObject.getString("logradouro");
            ceep.cep = jsonObject.getString("cep");
            ceep.complemento = jsonObject.getString("complemento");
            ceep.bairro = jsonObject.getString("bairro");
            ceep.localidade = jsonObject.getString("localidade");
            ceep. uf = jsonObject.getString("uf");
            sb.append(ceep.logradouro).append("\n").append(ceep.localidade).append("\n").append(ceep.cep)
                    .append("\n").append(ceep.complemento).
                    append("\n").append(ceep.bairro).append("\n").append(ceep.uf).append("\n");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        dialogResp.YNAlterData(sb.toString(), act, ceep, 0);

        txtresp.setText("");
    }

}