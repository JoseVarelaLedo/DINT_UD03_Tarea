package relojDigital;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.util.EventListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jose
 */
public class ComponenteRelojDigitalBean extends JLabel implements Serializable {

    private boolean formato24Horas;
    private boolean alarmaActivada;
    private int horaAlarma;
    private int minutoAlarma;

    private Timer timer;

    public ComponenteRelojDigitalBean() {
        //iniciamos el boolean del formato24Horas porque queremos que se inicie así
        formato24Horas = true;
        //el boolean de activación de alarma por defecto ya se crea a false

        //hemos llevado el ActionListener a otro método, por claridad de código
        iniciarTimer();
        setText(obtenerHoraActual());
    }

    public interface DefinirAlarmaListener extends EventListener {

        void capturarAlarma(DefinirAlarmaEvent event);
    }

    private void iniciarTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarHora();
            }
        });
        timer.start();
    }
   

    private String obtenerHoraActual() {
        Calendar calendario = Calendar.getInstance();
        SimpleDateFormat formato;
        if (formato24Horas) {
            formato = new SimpleDateFormat("HH:mm:ss");
        } else {
            formato = new SimpleDateFormat("hh:mm:ss a");
        }
        return formato.format(calendario.getTime());
    }

    private void actualizarHora() {
        String horaActual = obtenerHoraActual();
        setText(horaActual);

        // Comprobar si es hora de la alarma
        if (alarmaActivada && horaAlarma == Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                && minutoAlarma == Calendar.getInstance().get(Calendar.MINUTE)) {
            lanzarEventoAlarma();
        }
    }

    private void lanzarEventoAlarma() {
        DefinirAlarmaEvent evento = new DefinirAlarmaEvent(this);
        for (DefinirAlarmaListener listener : listeners) {
            listener.capturarAlarma(evento);
        }

        // Mostrar la ventana de diálogo cuando se dispare la alarma
        mostrarVentanaEmergente();
    }

    private void mostrarVentanaEmergente() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ComponenteRelojDigitalBean.this);
                AlarmaDialog dialog = new AlarmaDialog(frame, "¡ES LA HORA DEFINIDA PARA LA ALARMA!");
                dialog.setVisible(true);
            }
        });
    }

    // Métodos getters y setters para las propiedades
    public boolean isFormato24Horas() {
        return formato24Horas;
    }

    public void setFormato24Horas(boolean formato24Horas) {
        this.formato24Horas = formato24Horas;
        actualizarHora();
    }

    public boolean isAlarmaActivada() {
        return alarmaActivada;
    }

    public void setAlarmaActivada(boolean alarmaActivada) {
        this.alarmaActivada = alarmaActivada;
    }

    public int getHoraAlarma() {
        return horaAlarma;
    }

    public void setHoraAlarma(int horaAlarma) {
        this.horaAlarma = horaAlarma;
    }

    public int getMinutoAlarma() {
        return minutoAlarma;
    }

    public void setMinutoAlarma(int minutoAlarma) {
        this.minutoAlarma = minutoAlarma;
    }

    // Implementación para la gestión de eventos
    private transient DefinirAlarmaListener[] listeners = new DefinirAlarmaListener[0];

    public void addDefinirAlarmaListener(DefinirAlarmaListener listener) {
        listeners = addElement(listeners, listener);
    }

    public void removeDefinirAlarmaListener(DefinirAlarmaListener listener) {
        listeners = removeElement(listeners, listener);
    }

    private DefinirAlarmaListener[] addElement(DefinirAlarmaListener[] array, DefinirAlarmaListener element) {
        DefinirAlarmaListener[] newArray = new DefinirAlarmaListener[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = element;
        return newArray;
    }

    private DefinirAlarmaListener[] removeElement(DefinirAlarmaListener[] array, DefinirAlarmaListener element) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            DefinirAlarmaListener[] newArray = new DefinirAlarmaListener[array.length - 1];
            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
            return newArray;
        } else {
            return array;
        }
    }
}