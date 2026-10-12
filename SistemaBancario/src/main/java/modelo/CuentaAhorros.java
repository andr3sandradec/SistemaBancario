package modelo;

public class CuentaAhorros extends Cuenta {

    public CuentaAhorros(String numero, String titular, Fecha fechaApertura, double saldo) {
        super(numero, titular, fechaApertura, saldo);
    }
    @Override
    public boolean retirar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            return true; 
        }
        return false; 
    }
}