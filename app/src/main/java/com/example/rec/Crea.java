package com.example.rec;

import androidx.fragment.app.Fragment;

public class Crea extends Fragment {

    private String nombre;
    private int icono;

    public Crea() {

    }

    public Crea(String nombre, int icono) {
        this.nombre = nombre;
        this.icono = icono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }
}
