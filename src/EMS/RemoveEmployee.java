package EMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class RemoveEmployee extends JFrame implements ActionListener {

    Choice choiceempid;

    JButton delete,back;
    RemoveEmployee(){
        JLabel label= new JLabel("Employee ID");
        label.setBounds(50,50,100,30);
        label.setFont(new Font("Tahoma", Font.BOLD,15));
        add(label);

        choiceempid =new Choice();
        choiceempid.setBounds(200,50,150,30);
        add(choiceempid);
        try{
            conn c=new conn();
            ResultSet resultSet=c.statement.executeQuery("select * from employee");
            while(resultSet.next()){
                choiceempid.add(resultSet.getString("empid"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel labelName= new JLabel(" Name");
        labelName.setBounds(50,100,100,30);
        labelName.setFont(new Font("Tahoma", Font.BOLD,15));
        add(labelName);

        JLabel textName = new JLabel();
        textName.setBounds(200,100,100,30);
        add(textName);

        JLabel labelPhone= new JLabel("Phone");
        labelPhone.setBounds(50,150,100,30);
        labelPhone.setFont(new Font("Tahoma", Font.BOLD,15));
        add(labelPhone);

        JLabel textPhone = new JLabel();
        textPhone.setBounds(200,150,100,30);
        add(textPhone);

        JLabel labelemail= new JLabel("Email");
        labelemail.setBounds(50,200,100,30);
        labelemail.setFont(new Font("Tahoma", Font.BOLD,15));
        add(labelemail);

        JLabel textemail = new JLabel();
        textemail.setBounds(200,200,100,30);
        add(textemail);

        try{
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from employee where empid ='"+choiceempid.getSelectedItem()+"'");
            while(resultSet.next()){
                textName.setText(resultSet.getString("name"));
                textPhone.setText(resultSet.getString("phone"));
                textemail.setText(resultSet.getString("email"));



            }
        }catch (Exception e){
            e.printStackTrace();
        }
        choiceempid.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    conn c = new conn();
                    ResultSet resultSet = c.statement.executeQuery("select * from employee where empid ='"+choiceempid.getSelectedItem()+"'");
                    while(resultSet.next()) {
                        textName.setText(resultSet.getString("name"));
                        textPhone.setText(resultSet.getString("phone"));
                        textemail.setText(resultSet.getString("email"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });
        delete=new JButton("Delete");
        delete.setBounds(80,300,100,30);
        delete.setBackground(Color.black);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        back=new JButton("Back");
        back.setBounds(220,300,100,30);
        back.setBackground(Color.black);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2=i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(500,80,200,200);
        add(img);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icons/rback.png"));
        Image i12=i11.getImage().getScaledInstance(850,630,Image.SCALE_DEFAULT);
        ImageIcon i13=new ImageIcon(i12);
        JLabel imgage = new JLabel(i13);
        imgage.setBounds(0,0,850,630);
        add(imgage);

        setSize(800,400);
        setLocation(120,150);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==delete){
            try{
                conn c = new conn();
                String query = "delete from employee where empid='"+choiceempid.getSelectedItem()+"'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Employee Deleted successfully");
                setVisible(false);
                new Main_class();
            }catch (Exception E){
                E.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }

    public static  void main(String[] args){
        new RemoveEmployee();
    }
}
