import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;


public class Formulario {
    private JPanel estudiantes;
    private JButton cargarButton;
    private JButton guardarButton;
    private JTextField codigo;
    private JTextField cedula;
    private JTextField nombre;
    private JTextField apellido;
    private JComboBox signoop;

    private JRadioButton siRadioButton;
    private JRadioButton noRadioButton;
    private JCheckBox rojoCheckBox;
    private JCheckBox verdeCheckBox;
    private JCheckBox ningunoCheckBox;
    private JComboBox anio;
    private JComboBox mes;
    private JComboBox dia;
    private JButton leftButton;
    private JButton rightbutton;

    public Formulario() {
        signoop.addItem("Elije");
        signoop.addItem("Aries");
        signoop.addItem("Tauro");
        signoop.addItem("Géminis");
        signoop.addItem("Cáncer");
        signoop.addItem("Leo");
        signoop.addItem("Virgo");
        signoop.addItem("Libra");
        signoop.addItem("Escorpio");
        signoop.addItem("Sagitario");
        signoop.addItem("Capricornio");
        signoop.addItem("Acuario");
        signoop.addItem("Piscis");
        anio.addItem("Año");
        mes.addItem("Mes");
        dia.addItem("Dia");
        for (int an=2002; an<=2022; an++){
            anio.addItem(an);
        }
        for (int m=1; m<=12; m++){
            mes.addItem(m);
        }
        for (int d=1; d<=31; d++){
            dia.addItem(d);
        }
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoText = codigo.getText();
                String cedulaText = cedula.getText();
                String nombreText = nombre.getText();
                String apellidoText = apellido.getText();
                String signoopText = (String) signoop.getSelectedItem();
                String anioText = (String) anio.getSelectedItem();
                String mesText = (String) mes.getSelectedItem();
                String diaText = (String) dia.getSelectedItem();
                String color;
                if (rojoCheckBox.isSelected()){
                    color = "Rojo";
                } else if (verdeCheckBox.isSelected()) {
                    color ="Verde";
                }else {
                    color="No tiene";
                }


                try (FileOutputStream fileOutputStream = new FileOutputStream("datos.dat");
                     DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)) {
                    dataOutputStream.writeUTF(codigoText);
                    dataOutputStream.writeUTF(cedulaText);
                    dataOutputStream.writeUTF(nombreText);
                    dataOutputStream.writeUTF(apellidoText);
                    dataOutputStream.writeUTF(signoopText);
                    dataOutputStream.writeUTF(anioText);
                    dataOutputStream.writeUTF(mesText);
                    dataOutputStream.writeUTF(diaText);
                    dataOutputStream.writeUTF(color);
                    codigo.setText("");
                    cedula.setText("");
                    nombre.setText("");
                    apellido.setText("");

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        cargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (FileInputStream fileInputStream = new FileInputStream("datos.dat");
                     DataInputStream dataInputStream = new DataInputStream(fileInputStream)) {
                    codigo.setText(dataInputStream.readUTF());
                    cedula.setText(dataInputStream.readUTF());
                    nombre.setText(dataInputStream.readUTF());
                    apellido.setText(dataInputStream.readUTF());
                    signoop.setSelectedItem(dataInputStream.readUTF());
                    anio.setSelectedItem(dataInputStream.readUTF());
                    mes.setSelectedItem(dataInputStream.readUTF());
                    dia.setSelectedItem(dataInputStream.readUTF());

                    JOptionPane.showMessageDialog(null, "Datos cargados exitosamente.");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Formulario");
        frame.setContentPane(new Formulario().estudiantes);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
