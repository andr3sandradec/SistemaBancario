package modelo;

public abstract class Cuenta {
    protected String numero;
    protected String titular;
    protected Fecha fechaApertura; 
    protected double saldo;



    public Cuenta(String numero, String titular, Fecha fechaApertura, double saldo) {
        this.numero = numero;
        this.titular = titular;
        this.fechaApertura = fechaApertura;
        this.saldo = saldo;
    }

   
    public Cuenta(String numero, String titular, Fecha fechaApertura) {
        this.numero = numero;
        this.titular = titular;
        this.fechaApertura = fechaApertura;
        this.saldo = 0.0;
    }

    public String getNumero() { return numero; }
    public double getSaldo() { return saldo; }

    public void consignar(double valor) {
        if (valor > 0) this.saldo += valor;
    }
    public abstract boolean retirar(double valor);
    public String imprimirDatos() {
        return "Cuenta: " + numero + " | Titular: " + titular + " | Apertura: " + fechaApertura.toString() + " | Saldo: $" + saldo;
    }

    
    public String imprimirDatos(boolean soloSaldo) {
        if (soloSaldo) {
            return "Cuenta: " + numero + " -> Saldo actual: $" + saldo;
        }
        return imprimirDatos();
    }
}