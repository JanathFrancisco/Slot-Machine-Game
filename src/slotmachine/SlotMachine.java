/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slotmachine;

/**
 *
 * @author win 8
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

public class SlotMachine extends JFrame{
    static int clickCounter=0;
   

    public SlotMachine(){

        super("Slot Machine Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1220,720);

        //creating the layout
        GridBagConstraints c = new GridBagConstraints();
        JPanel panel = new JPanel();  
        panel.setBackground(Color.cyan);
        panel.setLayout(new GridBagLayout());
        add(panel);
        
        //placing images for reel1
        ImageIcon img1 = new ImageIcon(getClass().getResource("images/bell.png"));
        JLabel lbl1 = new JLabel(img1);
     
        lbl1.setBorder(BorderFactory.createLineBorder(Color.blue,5));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.insets = new Insets(0,0,100,10);
        panel.add(lbl1,c);

        //placing images for reel2
        ImageIcon img2 = new ImageIcon(getClass().getResource("images/cherry.png"));
        JLabel lbl2=new JLabel(img2);
        lbl2.setBorder(BorderFactory.createLineBorder(Color.blue,5));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=0;
        c.insets = new Insets(0,0,100,10);
        panel.add(lbl2,c);

        //placing images for reel3
        ImageIcon img3 = new ImageIcon(getClass().getResource("images/lemon.png"));
        JLabel lbl3 = new JLabel(img3);
        lbl3.setBorder(BorderFactory.createLineBorder(Color.blue,5));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx= 2;
        c.gridy=0;
        c.insets = new Insets(0,0,100,10);
        panel.add(lbl3,c);

        //textfiled for status
        JLabel txtStatus = new JLabel("");
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=2;
        c.gridy=1;
        c.insets = new Insets(10,50,10,0);
        panel.add(txtStatus,c);

        //button for Spin
        JButton btnSpin = new JButton("Spin");
        btnSpin.setFont(new java.awt.Font("Arial", Font.BOLD, 14));   
        c.gridx=1;
        c.gridy=1;
        c.gridwidth=1;
        panel.add(btnSpin,c);
        Reel reel1 = new Reel(lbl1);
        Reel reel2 = new Reel(lbl2);
        Reel reel3 = new Reel(lbl3);

        //status(Win,lose or try again)
        JLabel status = new JLabel();
        c.gridy=-3;
        c.gridx=-3;
        c.insets = new Insets(0,10,10,10);
        panel.add(status,c);
        User user = new User(status,lbl1,lbl2,lbl3);
        user.setText();
       
        //button for add a coin
        JButton btnAddCoin = new JButton("Add Coin");    
        c.gridx=-3;
        c.gridy=-1;      
        panel.add(btnAddCoin,c);
        btnAddCoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                user.setCredit();
                user.setText();
            }
        });
        //button for reset
        JButton btnReset = new JButton("Reset");
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=3;
        panel.add(btnReset,c);
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                user.setReset();
            }
        });

        //button for bet one
        JButton btnBetOne = new JButton("Bet One");
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=-1;
        c.gridy=0;
        panel.add(btnBetOne,c);
        btnBetOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                user.setBet();
                user.setText();
            }
        });

        //button for bet max
        JButton btnBetMax = new JButton("Bet Max");
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=-1;
        c.gridy=0;
        panel.add(btnBetMax,c);
        btnBetMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                user.setMaxBet();
                user.setText();
                btnBetOne.setEnabled(false);
            }
        });

        //button for statics
        JButton btnStatics = new JButton("Statics");
        c.gridx=0;
        c.gridy=4;
        panel.add(btnStatics,c);
        btnStatics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                         user.viewStatics();

            }
        });
        //Stopping reel1
        lbl1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                reel1.stopSpin();
                clickCounter++;
                if(clickCounter==3){
                    txtStatus.setText(user.winning(reel1.getGenNumber(),reel2.getGenNumber(),reel3.getGenNumber()));
                }
            }
        });
        //Stopping reel2
        lbl2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                reel2.stopSpin();
                clickCounter++;
                if(clickCounter==3){
                    txtStatus.setText(user.winning(reel1.getGenNumber(),reel2.getGenNumber(),reel3.getGenNumber()));
                }
            }
        });
        //Stopping reel3
        lbl3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                reel3.stopSpin();
                clickCounter++;
                if(clickCounter==3){
                    txtStatus.setText(user.winning(reel1.getGenNumber(),reel2.getGenNumber(),reel3.getGenNumber()));
                }
            }
        });
        //Action event for spin Button
        btnSpin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(clickCounter==3){
                    txtStatus.setText("");
                    reel1.timer.start();
                    reel2.timer.start();
                    reel3.timer.start();
                    clickCounter=0;
                    lbl1.setBorder(BorderFactory.createLineBorder(Color.orange,5));
                    lbl2.setBorder(BorderFactory.createLineBorder(Color.orange,5));
                    lbl3.setBorder(BorderFactory.createLineBorder(Color.orange,5));
                    btnBetOne.setEnabled(true);
                }else {
                    if(user.getBet()!=0){
                        reel1.start();
                        reel2.start();
                        reel3.start();

                    }else{
                        JOptionPane.showMessageDialog(null,"Bet First","",JOptionPane.WARNING_MESSAGE);
                    }

                }

            }
        });

        setVisible(true);  
        
    }
    public static void main(String[] args){
        SlotMachine slGame = new SlotMachine();
        //User user = new User();

    }
}
