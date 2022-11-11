package com.example.webservice;

import java.util.List;

public  interface IDBCEPDAO
{
    public boolean salvar(DBCep dbCep);
    public boolean atualizar(DBCep dbCep);
    public boolean deletar(DBCep dbCep);

    public List<DBCep> listar();
}
