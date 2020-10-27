package Menu;

import java.awt.event.*;
import DTBconnection.DTBconnection;
import Menu.SearchResult.SearchResult;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
/*
 * Created by JFormDesigner on Sun Oct 25 13:31:40 AST 2020
 */



/**
 * @author edison padilla
 */
public class Menu extends JFrame {
    public Menu() {

        initComponents();
        addModel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    DefaultTableModel model ;
    PreparedStatement ps ;
    ResultSet rs ;
    DTBconnection connection = new DTBconnection();

    public void addAgenda() {
        try{
            ps = connection.getConnection().prepareStatement("INSERT INTO agenda(plan , lugar, fecha) VALUES ( ?, ? ,? )");
            ps.setString(1 , textArea1.getText());
            ps.setString(2 , textField1.getText());
            ps.setString(3 , textField2.getText());
            int res = ps.executeUpdate();

            if(res  > 0) {
                JOptionPane.showMessageDialog(this, "Agregado Correctamente!");
                clean();
            }else
                JOptionPane.showMessageDialog(this, "No se agrego!");

        }catch (Exception e){
                System.out.println(e);
        }

    }

    private void button1ActionPerformed(ActionEvent e) {
            boolean validate = false ;

        if(textArea1.getText().length() < 1){
            JOptionPane.showMessageDialog(this, "Falta campo PLAN por llenar");
            return;
        }else if(textField1.getText().length() < 1){
            JOptionPane.showMessageDialog(this, "Falta campo LUGAR  por llenar");
            return;
        }else if(textField2.getText().length() < 1){
            JOptionPane.showMessageDialog(this, "Falta campo FECHA por llenar");
            return;
        }else{
            validate = true;
        }

        if(validate) {
            addAgenda();
        };
    }

    private  void addModel()  {
        Vector columns = new Vector();
        columns.addElement("ID");
        columns.addElement("Plan");
        columns.addElement("Lugar");
        columns.addElement("Fecha");
        Vector filas = new Vector();
         model = new DefaultTableModel(filas , columns);
        table1.setModel(model);
        showAgendas();
    }

    private  void clean(){
        textField1.setText("");
        textField2.setText("");
        textArea1.setText("Cual es tu plan?");
    }

    private  void showAgendas()  {
    try{
        ps = connection.getConnection().prepareStatement("SELECT * FROM agenda ORDER BY id DESC");
        rs = ps.executeQuery();
        if (rs.next()) {
            while (rs.next() ){
                model.addRow( new Object[] {rs.getString(1) ,rs.getString(2) , rs.getString(3) , rs.getString(4)});
            }
        }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private  void deleteAgenda(int id){
                ps = null ;
        try{
            ps = connection.getConnection().prepareStatement("delete from agenda where id=?");
            ps.setInt(1 , id);
            int res = ps.executeUpdate();
            if(res  > 0)
                JOptionPane.showMessageDialog(this,"Eliminado correctamente!");
            else
                JOptionPane.showMessageDialog(this, "No se elimino!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void button3ActionPerformed(ActionEvent e) {
            if(table1.getSelectedRow() != -1){
                int index =  table1.getSelectedRow();
                int id = Integer.parseInt((String) model.getValueAt(index , 0));
                deleteAgenda(id);
                model.removeRow(table1.getSelectedRow());
            }else {
                JOptionPane.showMessageDialog(this, "Selecciona una fila");
            }
    }

    private void button2ActionPerformed(ActionEvent e) {
        update();
    }

    private  void update(){

        try{
            if( table1.getSelectedRow() != -1) {
                ps = connection.getConnection().prepareStatement("update agenda set plan = ?, lugar =?, fecha= ? where id = ? ");
                int index = table1.getSelectedRow();
                int id = Integer.parseInt((String) model.getValueAt(index, 0));
                String plan = (String) model.getValueAt(index, 1);
                String lugar = (String) model.getValueAt(index, 2);
                String fecha = (String) model.getValueAt(index, 3);

                ps.setString(1, plan);
                ps.setString(2, lugar);
                ps.setString(3, fecha);
                ps.setInt(4, id);
                int rs = ps.executeUpdate();

                if (rs > 0) {
                    JOptionPane.showMessageDialog(this, " Actualizado correctamente (La tabla es editable!)");
                } else {
                    JOptionPane.showMessageDialog(this, " No se actualizo!");
                }

            }else{
                    JOptionPane.showMessageDialog(this , "Selecciona uno ( la tabla es editable ) ");
            }


        }catch (Exception e){
            System.out.println(e);
        }

    }

    private void button4ActionPerformed(ActionEvent e) {
        SearchResult result = new SearchResult();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - edison padilla
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane2 = new JScrollPane();
        textArea1 = new JTextArea();
        textField1 = new JTextField();
        label1 = new JLabel();
        button1 = new JButton();
        scrollPane3 = new JScrollPane();
        table1 = new JTable();
        button2 = new JButton();
        button3 = new JButton();
        textField2 = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        button4 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .
            EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder. CENTER ,javax . swing
            . border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,
            java . awt. Color .red ) ,dialogPane. getBorder () ) ); dialogPane. addPropertyChangeListener( new java. beans .PropertyChangeListener ( )
            { @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )
            throw new RuntimeException( ) ;} } );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //======== scrollPane2 ========
                {

                    //---- textArea1 ----
                    textArea1.setText("Escribe tu plan");
                    scrollPane2.setViewportView(textArea1);
                }
                contentPanel.add(scrollPane2);
                scrollPane2.setBounds(45, 25, 210, 95);
                contentPanel.add(textField1);
                textField1.setBounds(290, 70, 175, 50);

                //---- label1 ----
                label1.setText("Fecha: ");
                contentPanel.add(label1);
                label1.setBounds(490, 35, label1.getPreferredSize().width, 30);

                //---- button1 ----
                button1.setText("Agregar");
                button1.addActionListener(e -> button1ActionPerformed(e));
                contentPanel.add(button1);
                button1.setBounds(490, 155, 105, 30);

                //======== scrollPane3 ========
                {
                    scrollPane3.setViewportView(table1);
                }
                contentPanel.add(scrollPane3);
                scrollPane3.setBounds(40, 155, 425, 179);

                //---- button2 ----
                button2.setText("Actualizar");
                button2.addActionListener(e -> button2ActionPerformed(e));
                contentPanel.add(button2);
                button2.setBounds(new Rectangle(new Point(490, 195), button2.getPreferredSize()));

                //---- button3 ----
                button3.setText("Elminar");
                button3.addActionListener(e -> button3ActionPerformed(e));
                contentPanel.add(button3);
                button3.setBounds(490, 235, 100, button3.getPreferredSize().height);
                contentPanel.add(textField2);
                textField2.setBounds(485, 70, 110, 50);

                //---- label2 ----
                label2.setText("Lugar");
                contentPanel.add(label2);
                label2.setBounds(290, 45, 55, label2.getPreferredSize().height);
                contentPanel.add(label3);
                label3.setBounds(365, 15, 110, label3.getPreferredSize().height);
                contentPanel.add(label4);
                label4.setBounds(495, 300, 100, label4.getPreferredSize().height);

                //---- button4 ----
                button4.setText("Buscar");
                button4.addActionListener(e -> button4ActionPerformed(e));
                contentPanel.add(button4);
                button4.setBounds(490, 275, 100, button4.getPreferredSize().height);

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
//    private JPanel dialogPane;
//    private JPanel contentPanel;
//    private JScrollPane scrollPane2;
//    private JTextArea textArea1;
//    private JTextField textField1;
//    private JLabel label1;
//    private JButton button1;
//    private JScrollPane scrollPane3;
//    private JTable table1;
//    private JButton button2;
//    private JButton button3;
//    private JTextField textField2;
//    private JLabel label2;

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - edison padilla
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JScrollPane scrollPane2;
    private JTextArea textArea1;
    private JTextField textField1;
    private JLabel label1;
    private JButton button1;
    private JScrollPane scrollPane3;
    private JTable table1;
    private JButton button2;
    private JButton button3;
    private JTextField textField2;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
