package com.example.petevopierre.Activities.dao;

import android.content.Context;

import com.example.petevopierre.Activities.bean.Usuario;

import mobi.stos.podataka_lib.repository.AbstractRepository;
import mobi.stos.podataka_lib.service.AbstractService;

public class UsuarioDao extends AbstractRepository<Usuario> {
    public UsuarioDao(Context context) { super(context, Usuario.class);}
}
