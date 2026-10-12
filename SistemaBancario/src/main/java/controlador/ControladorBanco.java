package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.*;
import vista.VistaBanco;

public class ControladorBanco implements ActionListener {

    private VistaBanco vista;
    private ArrayList<Cuenta> listaCuentas;

    public ControladorBanco(VistaBanco vista) {
        this.vista = vista;
        this.listaCuentas = new ArrayList<>();

        this.vista.getBtnCrear().addActionListener(this);
        this.vista.getBtnConsignar().addActionListener(this);
        this.vista.getBtnRetirar().addActionListener(this);
        this.vista.getBtnVerSaldo().addActionListener(this);
        this.vista.getBtnVerTodo().addActionListener(this);
    }

    public void iniciar() {
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnCrear()) {
            crearCuenta();
        } else if (e.getSource() == vista.getBtnConsignar()) {
            realizarOperacion("consignar");
        } else if (e.getSource() == vista.getBtnRetirar()) {
            realizarOperacion("retirar");
        } else if (e.getSource() == vista.getBtnVerSaldo()) {
            mostrarInformacion(true); // Uso de sobrecarga
        } else if (e.getSource() == vista.getBtnVerTodo()) {
            mostrarInformacion(false); // Uso de sobrecarga
        }
    }

    private void crearCuenta() {
        try {
            String tipo = vista.getCmbTipoCuenta().getSelectedItem().toString();
            String num = vista.getTxtNumero().getText().trim();
            String titular = vista.getTxtTitular().getText().trim();
            double saldo = Double.parseDouble(vista.getTxtSaldo().getText().trim());
            
            // Parseo básico de fecha "dd/mm/yyyy"
            String[] partesFecha = vista.getTxtFecha().getText().split("/");
            Fecha fecha = new Fecha(Integer.parseInt(partesFecha[0]), Integer.parseInt(partesFecha[1]), Integer.parseInt(partesFecha[2]));

            if (tipo.equals("Ahorros")) {
                listaCuentas.add(new CuentaAhorros(num, titular, fecha, saldo));
            } else {
                double cupo = Double.parseDouble(vista.getTxtSobregiro().getText().trim());
                listaCuentas.add(new CuentaCorriente(num, titular, fecha, saldo, cupo));
            }
            vista.getTxtResultados().setText("¡Cuenta creada exitosamente!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al crear cuenta. Revise los datos (Fecha formato dd/mm/yyyy).");
        }
    }

    private void realizarOperacion(String tipoOperacion) {
        Cuenta c = buscarCuenta();
        if (c != null) {
            try {
                double monto = Double.parseDouble(vista.getTxtMonto().getText().trim());
                if (tipoOperacion.equals("consignar")) {
                    c.consignar(monto);
                    vista.getTxtResultados().setText("Consignación exitosa. Nuevo saldo: $" + c.getSaldo());
                } else if (tipoOperacion.equals("retirar")) {
                    // Polimorfismo automático según el tipo de cuenta
                    boolean exito = c.retirar(monto);
                    if (exito) vista.getTxtResultados().setText("Retiro exitoso. Nuevo saldo: $" + c.getSaldo());
                    else vista.getTxtResultados().setText("Error: Fondos insuficientes o excede sobregiro.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Ingrese un monto válido.");
            }
        }
    }

    private void mostrarInformacion(boolean soloSaldo) {
        Cuenta c = buscarCuenta();
        if (c != null) {
            // Se utiliza el método sobrecargado imprimirDatos()
            vista.getTxtResultados().setText(c.imprimirDatos(soloSaldo));
        }
    }

    private Cuenta buscarCuenta() {
        String numBuscar = vista.getTxtNumero().getText().trim();
        for (Cuenta c : listaCuentas) {
            if (c.getNumero().equals(numBuscar)) return c;
        }
        JOptionPane.showMessageDialog(vista, "No se encontró la cuenta Nro: " + numBuscar);
        return null;
    }
}