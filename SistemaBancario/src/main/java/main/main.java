package main;

import controlador.ControladorBanco;
import vista.VistaBanco;

public class main {
    public static void main(String[] args) {
        VistaBanco vista = new VistaBanco();
        ControladorBanco controlador = new ControladorBanco(vista);
        
        controlador.iniciar();
    }
}