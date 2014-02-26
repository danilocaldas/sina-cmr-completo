import java.awt.*;  
import java.awt.event.*;  
 
import java.util.Calendar;  
import javax.swing.JTextField;
import javax.swing.Timer;
  
class RelogioDigital extends JTextField {  
  
    private static final long serialVersionUID = 1L;  
  
    Timer m_t;  
  
    public RelogioDigital() {  
        this.setColumns(4);  
        this.setFont(new Font("sansserif", Font.PLAIN, 32));  
  
        m_t = new Timer(1000, new ClockTickAction());  
        m_t.start(); // Inicia a hora  
    }  
  
    // Classe interna que pega a hora do sistema  
    private class ClockTickAction implements ActionListener {  
        public void actionPerformed(ActionEvent e) {  
            // Pega a hora corrente  
            Calendar now = Calendar.getInstance();  
            int h = now.get(Calendar.HOUR_OF_DAY);  
            int m = now.get(Calendar.MINUTE);  
            int s = now.get(Calendar.SECOND);  
            setText("" + h + ":" + m + ":" + s);  
        }  
    }  
  
}  
