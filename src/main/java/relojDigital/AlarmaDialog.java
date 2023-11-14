package relojDigital;

/**
 *
 * @author Jose
 */
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AlarmaDialog extends JDialog {

    private Timer timer;

    public AlarmaDialog(JFrame frame, String mensaje) {
        super(frame, "¡Alarma!", true);

        JLabel label = new JLabel(mensaje);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout()); // Establecer el diseño para que sea más fácil configurar el tamaño
        panel.add(label);
        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana cuando se hace clic en "Cerrar"
            }
        });
        panel.add(cerrarButton);

        getContentPane().add(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();

        // Configurar el tamaño preferido del panel (ajusta según tus necesidades)
        panel.setPreferredSize(new Dimension(400, 100));
        setLocationRelativeTo(frame);
        // Configurar temporizador para cerrar automáticamente después de 5 segundos (5000 milisegundos)
        timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana
            }
        });
        timer.setRepeats(false); // No se repite
        timer.start();
    }
}
