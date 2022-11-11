package com.example.webservice;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.Range;

import java.util.ArrayList;
import java.util.List;

public class CEPDAO implements IDBCEPDAO
    {
    private SQLiteDatabase  leitura;
    private SQLiteDatabase  escrita;

    public CEPDAO(Context context)
        {
        DbHelper dbHelper = new DbHelper(context);

        leitura = dbHelper.getReadableDatabase();
        escrita = dbHelper.getWritableDatabase();
        }

    @Override
    public boolean salvar(DBCep dbCep)
        {
        ContentValues contentValues = new ContentValues();

        contentValues.put("logradouro",dbCep.logradouro);
        contentValues.put("cep",dbCep.cep);
        contentValues.put("complemento",dbCep.complemento);
        contentValues.put("bairro",dbCep.bairro);
        contentValues.put("localidade",dbCep.localidade);
        contentValues.put("uf",dbCep.uf);
        //contentValues.put("id",dbCep.id);

        this.escrita.insert(DbHelper.TABELA_CEP, null, contentValues);

        Log.i("rps","log: "+dbCep.cep);

        return true;
        }


        @Override
        public boolean atualizar(DBCep dbCep)
            {
            ContentValues contentValues = new ContentValues();
            contentValues.put("logradouro",dbCep.logradouro);
            contentValues.put("cep",dbCep.cep);
            contentValues.put("complemento",dbCep.complemento);
            contentValues.put("bairro",dbCep.bairro);
            contentValues.put("localidade",dbCep.localidade);
            contentValues.put("uf",dbCep.uf);

            String [] args = {String.valueOf(dbCep.id) };

            this.escrita.update(DbHelper.TABELA_CEP, contentValues, "id=?", args);

            return true;
            }


        @Override
        public boolean deletar(DBCep dbCep)
            {
            String [] args = {String.valueOf(dbCep.id) };

            this.escrita.delete(DbHelper.TABELA_CEP, "id=?", args);

            return true;
            }


        @SuppressLint("Range")
        @Override
        public List<DBCep> listar()
            {
            List<DBCep> dbCeps = new ArrayList<>();
            String sql = "SELECT * FROM "+DbHelper.TABELA_CEP+" ORDER BY id DESC; ";
            Cursor cursor = leitura.rawQuery(sql, null);

            while (cursor.moveToNext())
                {
                Long id = cursor.getLong(cursor.getColumnIndex("id"));
                String logradouro   = cursor.getString(cursor.getColumnIndex("logradouro"));
                String cep          = cursor.getString(cursor.getColumnIndex("cep"));
                String complemento  = cursor.getString(cursor.getColumnIndex("complemento"));
                String bairro       = cursor.getString(cursor.getColumnIndex("bairro"));
                String localidade   = cursor.getString(cursor.getColumnIndex("localidade"));
                String uf           = cursor.getString(cursor.getColumnIndex("uf"));

                //Log.i("vm","resp: "+logradouro);
                    dbCeps.add(new DBCep(cep, logradouro, complemento, bairro, localidade, uf, id));
                }

            return dbCeps;
            }
    }

