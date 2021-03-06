import com.toedter.calendar.JDateChooser;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
public class DalinType {
    
    public static void typeCharacter(KeyEvent evt){
       char ch=evt.getKeyChar();
         if(Character.isDigit(ch)){
             Toolkit.getDefaultToolkit().beep();
             evt.consume();
         }
   }
   public static void typeNumber(KeyEvent evt){
       char ch=evt.getKeyChar();
         if(!(Character.isDigit(ch))){
             Toolkit.getDefaultToolkit().beep();
             evt.consume();
         }
   }
   public static void type10(JTextField text,KeyEvent evt){
       char ch=evt.getKeyChar();
       if(!(Character.isDigit(ch))){
           Toolkit.getDefaultToolkit().beep();
           evt.consume();
       }
       int max=9;
       int t=text.getText().trim().length();
       if(t>max){
           Toolkit.getDefaultToolkit().beep();
           evt.consume();
       }
   }
   public static void enterJText(JTextField text,KeyEvent evt){
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           text.requestFocus();
       }
   }
   public static void enterCbo(JComboBox cbo,KeyEvent evt){
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           cbo.requestFocus();
       }
   }
   public static void enterJDate(JDateChooser date,KeyEvent evt){
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           date.requestFocus();
       }
   }
   public static void enterJTextArea(JTextArea area,KeyEvent evt){
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           area.requestFocus();
       }
   }
    public static void enterJButton(JButton b,KeyEvent evt){
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           b.requestFocus();
       }
   }
    public static void enterTextPassword( JPasswordField txt, KeyEvent evt){
      if(evt.getKeyCode()==KeyEvent.VK_ENTER){
          txt.requestFocus();
      }   
    }
  
    Connection con;
    ResultSet rst;
    Statement stm;
    PreparedStatement ps;
    DefaultTableModel mode=new DefaultTableModel();
   public void autoID(String Data,JLabel l){
       try{
           con=DalinConnectSql.getDalinConnection();
           stm=con.createStatement();
           rst=stm.executeQuery("select max(right(no,10000)) as no from "+Data+"");
           while(rst.next()){
               if(rst.first()==false){
                   l.setText("100001");
               }
               else {
                   
                   rst.last();
                         int set_id=rst.getInt(1)+1;
                         String no=String.valueOf(set_id);
                         int next_id=no.length();
                         for(int i=0;i<2-next_id;i++){
                             no="10000"+no;
                         }
                         l.setText(no);
               }
           }
       }catch(SQLException e){}
   }
   public static void edUperCase(JTextField input){
       String upercase=input.getText().toUpperCase();
       input.setText(upercase);
   }
   public static void type1Dot(KeyEvent evt,JTextField jl){
       if(evt.getKeyChar()==46){
           if(jl.getText().indexOf(46)!= -1){
               Toolkit.getDefaultToolkit().beep();
               evt.consume();
           }
       }
       else if(!(Character.isDigit(evt.getKeyChar())&&evt.getKeyChar()!=8)){
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
   }
   public void edSearch(String data,JTextField input,JTable tb){
       try{
           while(mode.getRowCount()>0)
               mode.removeRow(0);
               mode=(DefaultTableModel)tb.getModel();
               
               ps=con.prepareStatement("select * from "+data+" where id=? or name=?");
               ps.setString(1, input.getText());
               rst=ps.executeQuery();
               
               while(rst.next()){
                   mode.addRow(new String[]{
                       
                   });
               }
       }catch(SQLException e){JOptionPane.showMessageDialog(null, e);}
   }
}
