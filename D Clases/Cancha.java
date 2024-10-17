public class AdministradorDeReservas {

            

    public void crearReserva(
        String nombreResponsable, 
        String apellidoResponsable,
        int dniResponsable, 
        Date fecha, 
        int nroTarjeta, 
        Date vencimientTarjeta,
        int codSeguridad, 
        Cancha canchaReservada,
        float total){

            DatosTarjeta tarjeta=new DatosTarjeta(nroTarjeta, vencimientTarjeta, codSeguridad);
            canchaReservada.addReserva(new Reserva(nombreResponsable, apellidoResponsable, dniResponsable, fecha,  tarjeta,  canchaReservada,  total));

    }

    

    public void cancelarReserva(Date date, Cancha cancha){

                Reserva reserva=cancha.removeReserva(date);

                reserva.realizarPago(10);

    }

    public void informarNoPresentacion(Date date, Cancha cancha){

                cancha.getReserva(date).realizarPago(50);

    }

}
public class Cancha {

    private String deporte;

    private Object ubicacion;

    private List<Date> diasApertura;

    private Time horarioApertura;

    private Time horarioCierre;

    private Time duracionTurno;

    private List<Reserva> reservas;

    private long id;

    

    public void addReserva(Reserva r){

                reservas.add(r);

    }

    private List<Reserva> getReservaProxDias(int nroDias){

                //Do something

    }

    private Object listarCaracteristicas(){

                //Do something

    }

    public Reserva getReserva(Date date){

                for (Iterator<Reserva> iterator = reservas.iterator(); iterator.hasNext();) {

                           Reserva reserva = (Reserva) iterator.next();

                           if(reserva.getFecha().equals(date))

                                       return reserva;

                }

    }

    public Reserva removeReserva(Date date){

                Reserva ret=getReserva(date);

                reservas.remove(ret);

                return ret;

    }

}

public class Reserva {

    private String nombreResponsable;

    private String apellidoResponsable;

    private int dniResponsable;

    private Date fecha;

    private DatosTarjeta tarjeta;

    private Cancha canchaReservada;

    private float total;

    private boolean pagado;

                

    public Reserva(
    String nombreResponsable, 
    String apellidoResponsable,
    int dniResponsable,
    Date fecha,
    DatosTarjeta tarjeta, 
    Cancha canchaReservada, 
    float total) {

                this.nombreResponsable = nombreResponsable;

                this.apellidoResponsable = apellidoResponsable;

                this.dniResponsable = dniResponsable;

                this.fecha = fecha;

                this.tarjeta = tarjeta;

                this.canchaReservada = canchaReservada;

                this.total = total;

                pagado=false;

    }



    public void realizarPago(float porcentaje){

                float montoFinal=total*(porcentaje/100);

                getSistemaBancario().hacerPago(tarjeta.getNro(), tarjeta.getVencimiento(), tarjeta.getCodSeguridad(), montoFinal);

                pagado=true;

    }

    

    private SistemaBancario getSistemaBancario(){

                //Do something

    }

    public Date getFecha() {

                return fecha;

    }

}

public class DatosTarjeta {

    

    private int nro;

    private Date vencimiento;

    private int codSeguridad;

    

    public DatosTarjeta(int nro, Date vencimiento, int codSeguridad) {

                this.nro = nro;

                this.vencimiento = vencimiento;

                this.codSeguridad = codSeguridad;

    }

    

    public int getCodSeguridad() {

                return codSeguridad;

    }

    public int getNro() {

                return nro;

    }

    public Date getVencimiento() {

                return vencimiento;

    }

}

public interface SistemaBancario {

    public boolean hacerPago(int nroTarjeta, Date vencimiento, int codSeguridad, float monto);

}