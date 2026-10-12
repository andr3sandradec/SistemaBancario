package modelo;

public class CuentaCorriente extends Cuenta {
    private double cupoSobregiro;

    public CuentaCorriente(String numero, String titular, Fecha fechaApertura, double saldo, double cupoSobregiro) {
        super(numero, titular, fechaApertura, saldo);
        this.cupoSobregiro = cupoSobregiro;
    }

    @Override
    public boolean retirar(double valor) {
        if (valor > 0 && valor <= (saldo + cupoSobregiro)) {
            saldo -= valor; 
            return true;
        }
        return false; 
    }

    @Override
    public String imprimirDatos() {
        return super.imprimirDatos() + " | Cupo Sobregiro: $" + cupoSobregiro;
    }
}