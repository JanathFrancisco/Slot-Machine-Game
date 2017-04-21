/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slotmachine;

/**
 *
 * @author win 8
 *
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User extends JFrame {
    static private int wins = 0;
    private int credit;
    private int bet = 0;
    private int losses = 0;
     private float average=0;
      private int creditLost=0;
      
      
    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public void setNumberOfMatches() {
        numberOfMatches++;
    }

    private int numberOfMatches;
    private JLabel lbl, lbl2, lbl3, lbl4;

    
  

    public User(JLabel lbl, JLabel lbl2, JLabel lbl3, JLabel lbl4) {
        credit = 10;
        this.lbl = lbl;
        this.lbl2 = lbl2;
        this.lbl3 = lbl3;
        this.lbl4 = lbl4;
    
        
    }

    public void setCredit() {
        credit++;
    }

    public int getCredit() {
        return credit;
    }

    public int getBet() {
        return bet;
    }

    public void setBet() {

        bet++;
        credit--;
                if(bet>3){
            JOptionPane.showMessageDialog(null, "You can bet only upto 3 credits", "", JOptionPane.WARNING_MESSAGE);
            bet=3;
            credit=credit+1;
        }
        if (credit < 0) {
            JOptionPane.showMessageDialog(null, "Please add credits", "", JOptionPane.WARNING_MESSAGE);
        }
    }

    public int getWins() {

        return wins;
    }

    public void setWins() {

        wins++;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses() {

        losses++;
    }

    public String winning(int reel1, int reel2, int reel3) {
        String status = "Try again";

        if (((reel1 == reel2) && (reel2 == reel3))||reel1 == reel2||reel2==reel3||reel1==reel3) {
            //matching reels


            if ((reel1 == reel2) && (reel2 == reel3)) {
                lbl2.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                lbl3.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                lbl4.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                credit = credit + (Reel.symbol[reel1].getValue() * bet)*2;


            }else if (reel1 == reel2) {
                lbl2.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                lbl3.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                credit = credit + (Reel.symbol[reel1].getValue() * bet)*2;

            }else if (reel2 == reel3) {
                lbl3.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                lbl4.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                credit = credit + (Reel.symbol[reel2].getValue() * bet)*2;

            }else if (reel1 == reel3) {
                lbl2.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                lbl4.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                credit = credit + (Reel.symbol[reel1].getValue() * bet)*3;

            }
            status = "Win!";
            setWins();
            bet=0;
            setText();

        }else{
            setLosses();
            bet = 0;
            setText();
        }
        return status;
    }

    public void setText() {
         lbl.setText("Credits : " + credit);
         if (bet > 0) {
            lbl.setText("Credits: " + credit + " Bet: " + bet);

        }
    }

    public boolean setMaxBet() {
        bet = 3;
        credit = credit - 3;
        if(credit<0){
            JOptionPane.showMessageDialog(null,"Please add credits","",JOptionPane.WARNING_MESSAGE);
            bet=0;
            credit=credit+3;
            return false;
        }
        return true;
    }
     public float calAverage(){
        average = (getWins()-getLosses())/(float)getNumberOfMatches();
        return average;
    } 
         public void setReset(){
        credit=credit+bet;

        bet=0;
        lbl2.setBorder(BorderFactory.createLineBorder(Color.orange,5));
        lbl3.setBorder(BorderFactory.createLineBorder(Color.orange,5));
        lbl4.setBorder(BorderFactory.createLineBorder(Color.orange,5));
        setText();
    }

      public  void viewStatics(){
        
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(600,500);
        GridBagConstraints c = new GridBagConstraints();
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        add(panel);


        JLabel lbl = new JLabel();
        c.gridy=0;
        c.gridx=0;
        panel.add(lbl,c);
        lbl.setText("Number of Mathces: "+ getNumberOfMatches());

        JLabel  lbl2 = new JLabel();
        c.gridx=0;
        c.gridy=1;
        panel.add(lbl2,c);
        lbl2.setText("Number of Wins: " + getWins()+" "+"Number of Losses: "+getLosses());

        JLabel lbl3 = new JLabel();
        c.gridx=0;
        c.gridy=3;
        panel.add(lbl3,c);
        lbl3.setText("Number of Credits: "+ getCredit());

        JLabel lbl4 = new JLabel();
        c.gridx=0;
        c.gridy=4;
        panel.add(lbl4,c);
        lbl4.setText("Average: " + calAverage());

        JButton btn = new JButton("Back");
        c.gridx=0;
        c.gridy=5;
        panel.add(btn,c);

        JButton btn2 = new JButton("Save Statics");
        c.gridx=1;
        c.gridy=5;

        panel.add(btn2,c);
        setVisible(true);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("button cliked");
                setVisible(false);
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("buutton cliked");
                saveData();
            }
        });
    }

     public void saveData(){
        DateFormat df = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
        Date dateobj = new Date();
        String date = df.format(dateobj);
        String fileName = date + ".txt";
        try {
            File f = new File(System.getProperty("user.home") + "/Desktop");
            File file = new File(f, fileName);//create a text file
            file.createNewFile();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {//write data line by line

                bw.write(date);
                bw.newLine();
                bw.write("\tNumber of Matches:" + getNumberOfMatches());
                bw.newLine();
                bw.write("\tWons: " + getWins() );
                bw.newLine();
                bw.write("\tCredits: " + getCredit());
                bw.newLine();
                bw.write("\tLosses: " + getLosses());
                bw.newLine();
                bw.write("\tCredits Lost: " + creditLost);
                bw.newLine();
                bw.write("\tAverage: " + average);

                bw.flush();
                bw.close();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error Saving File!", "Message", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error Saving File!", "Message", JOptionPane.INFORMATION_MESSAGE);

        }

    }
  
}

