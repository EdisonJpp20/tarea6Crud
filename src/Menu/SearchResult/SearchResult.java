/*
 * Created by JFormDesigner on Sun Oct 25 22:09:33 AST 2020
 */

package Menu.SearchResult;

import java.awt.event.*;
import DTBconnection.DTBconnection;
import com.sun.tools.javac.Main;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author edison padilla
 */
public class SearchResult extends JFrame{
    public SearchResult() {

        initComponents();
        addModel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    DefaultTableModel model ;

    PreparedStatement ps ;
    ResultSet rs ;
    DTBconnection connection = new DTBconnection();

    private void addModel(){
        Vector columns = new Vector();
        columns.addElement("ID");
        columns.addElement("Plan");
        columns.addElement("Lugar");
        columns.addElement("Fecha");
        Vector filas = new Vector();
        model = new DefaultTableModel(filas , columns);
        table1.setModel(model);
    }



    private void search(){
        try{

            String consulta = "SELECT * FROM agenda WHERE plan LIKE '%" +textField1.getText()+"'%";

//                System.out.println(textField1.getText());
//                ps.setString(1 , "hola");
                rs = ps.executeQuery(consulta);
                if (rs.next()) {
                    while (rs.next() ){
                        System.out.println(rs.getString(2));
                        model.addRow( new Object[] {rs.getString(1) ,rs.getString(2) , rs.getString(3) , rs.getString(4)});
                    }
                }else JOptionPane.showMessageDialog(this, "0 resultados");

        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void button2ActionPerformed(ActionEvent e) {
        search();
    }

    private void button1ActionPerformed(ActionEvent e) {
        setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - edison padilla
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        textField1 = new JTextField();
        button2 = new JButton();

        //======== this ========
        setTitle("Buscar");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
            . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder
            . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .
            awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,dialogPane. getBorder () ) )
            ; dialogPane. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
            ;
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(table1);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(35, 135, 385, 205);

                //---- button1 ----
                button1.setText("Volver atras");
                button1.addActionListener(e -> button1ActionPerformed(e));
                contentPanel.add(button1);
                button1.setBounds(new Rectangle(new Point(35, 10), button1.getPreferredSize()));
                contentPanel.add(textField1);
                textField1.setBounds(35, 85, 230, textField1.getPreferredSize().height);

                //---- button2 ----
                button2.setText("Buscar");
                button2.addActionListener(e -> button2ActionPerformed(e));
                contentPanel.add(button2);
                button2.setBounds(new Rectangle(new Point(265, 85), button2.getPreferredSize()));

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < contentPanel.getComponentCount(); i++) {
                        Rectangle bounds = contentPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = contentPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    contentPanel.setMinimumSize(preferredSize);
                    contentPanel.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(contentPanel, BorderLayout.NORTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - edison padilla
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JTextField textField1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
