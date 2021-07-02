package com.example.petevopierre.Activities.bo;

import android.content.Context;

import com.example.petevopierre.Activities.bean.Usuario;
import com.example.petevopierre.Activities.dao.UsuarioDao;

import mobi.stos.podataka_lib.interfaces.IOperations;
import mobi.stos.podataka_lib.service.AbstractService;

public class UsuarioBo extends AbstractService<Usuario> {
    private UsuarioDao usuarioDao;

    public UsuarioBo(Context context){
    super();
    this.usuarioDao = new UsuarioDao(context);
    }



    @Override
    protected IOperations<Usuario> getDao() {
        return usuarioDao;
    }
}
