package formularios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import relojDigital.AlarmaDialog;
/**
 *
 * @author Jose
 */
public class RelojForm extends javax.swing.JDialog {

    /**
     * Creates new form RelojForm
     */
    public RelojForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initListeners();
    }

    private void initListeners() {
        formato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarFormato();
            }
        });

        alarma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configurarAlarma();
            }
        });
    }

    private void cambiarFormato() {
        componenteRelojDigitalBean.setFormato24Horas(!componenteRelojDigitalBean.isFormato24Horas());
    }

    private void configurarAlarma() {
        String horaStr = JOptionPane.showInputDialog(this, "Introduce la hora de la alarma (0-23):");
        String minutoStr = JOptionPane.showInputDialog(this, "Introduce el minuto de la alarma (0-59):");

        try {
            int hora = Integer.parseInt(horaStr);
            int minuto = Integer.parseInt(minutoStr);

            componenteRelojDigitalBean.setAlarmaActivada(true);
            componenteRelojDigitalBean.setHoraAlarma(hora);
            componenteRelojDigitalBean.setMinutoAlarma(minuto);
            JOptionPane.showMessageDialog(this, "Alarma configurada para " + hora + ":" + minuto);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La hora y el minuto no son válidos.");
        }
    }

   

    private void mostrarVentanaAlarma() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AlarmaDialog dialog = new AlarmaDialog(new JFrame(), "¡ES LA HORA DEFINIDA PARA LA ALARMA!");
                dialog.setVisible(true);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        componenteRelojDigitalBean = new relojDigital.ComponenteRelojDigitalBean();
        formato = new javax.swing.JButton();
        alarma = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        componenteRelojDigitalBean.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        componenteRelojDigitalBean.setForeground(new java.awt.Color(0, 0, 0));
        componenteRelojDigitalBean.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        componenteRelojDigitalBean.setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
        componenteRelojDigitalBean.setHoraAlarma(19);
        componenteRelojDigitalBean.setMinutoAlarma(47);
        componenteRelojDigitalBean.addDefinirAlarmaListener(new relojDigital.ComponenteRelojDigitalBean.DefinirAlarmaListener() {
            public void capturarAlarma(relojDigital.DefinirAlarmaEvent evt) {
                componenteRelojDigitalBeanCapturarAlarma(evt);
            }
        });

        formato.setText("12H/24H");

        alarma.setText("ALARMA");
        alarma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alarmaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(componenteRelojDigitalBean, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(alarma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(formato, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(formato)
                        .addGap(60, 60, 60)
                        .addComponent(alarma))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(componenteRelojDigitalBean, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void componenteRelojDigitalBeanCapturarAlarma(relojDigital.DefinirAlarmaEvent evt) {//GEN-FIRST:event_componenteRelojDigitalBeanCapturarAlarma
         mostrarVentanaAlarma();
    }//GEN-LAST:event_componenteRelojDigitalBeanCapturarAlarma

    private void alarmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alarmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alarmaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RelojForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RelojForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RelojForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RelojForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RelojForm dialog = new RelojForm(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alarma;
    private relojDigital.ComponenteRelojDigitalBean componenteRelojDigitalBean;
    private javax.swing.JButton formato;
    // End of variables declaration//GEN-END:variables
}
