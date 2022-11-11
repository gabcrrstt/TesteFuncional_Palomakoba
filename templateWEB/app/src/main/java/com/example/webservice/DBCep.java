package com.example.webservice;

import java.io.Serializable;

public class DBCep implements Serializable
    {
    public String logradouro;
    public String cep;
    public String complemento;
    public String bairro;
    public String localidade;
    public String uf;
    public Long     id;

        //* Classe Construtora *//
    public DBCep(String cp, String end, String comp, String brr, String loc, String es, Long idd)
        {
        cep             = cp;
        logradouro      = end;
        complemento     = comp;
        bairro          = brr;
        localidade      = loc;
        uf              = es;
        id              = idd;
        }

    public DBCep()
        {

        }

    }


