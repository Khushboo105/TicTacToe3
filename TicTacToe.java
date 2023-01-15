import javax.swing.*;

import org.w3c.dom.ls.LSInput;

import java.awt.*;
import java.awt.event.*;
public class TicTacToe 
{
	JFrame fr=new JFrame("Tic Tac Toe");
	JLabel la=new JLabel(new ImageIcon(getClass().getResource("images/t2.jpg")));
	JPanel [] pa=new JPanel[3];
	JLabel msg=new JLabel("First player turn...");
	JButton reset=new JButton("RESET");
	JButton [] bt=new JButton[9];
	ImageIcon icon1=new ImageIcon(getClass().getResource("images/user1.png"));
	ImageIcon icon2=new ImageIcon(getClass().getResource("images/user2.png"));
	int player=1,count=0;
	boolean winnerFound=false;
	public TicTacToe() 
	{
		fr.setSize(500,580);
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		fr.setDefaultCloseOperation(3);
		fr.add(la);
		addPanels();
		addInfo();
		addButtons();
		fr.setVisible(true);
	}
	private void addPanels()
	{
		la.setLayout(null);
		for(int i=0;i<3;i++)
		{
			pa[i]=new JPanel();
			la.add(pa[i]);
		}
		pa[0].setBounds(50,30,400,40);
		pa[1].setBounds(50,100,400,350);
		pa[2].setBounds(50,475,400,40);
	}
	private void addInfo() 
	{
		pa[0].add(msg);
		pa[0].setBackground(Color.white);
		msg.setFont(new Font("Times new roman",0,30));
		msg.setForeground(Color.blue);
		pa[2].add(reset);
		pa[2].setOpaque(false);
		reset.setFont(new Font("Times new roman",0,20));
		reset.addActionListener(new ResetListener());
		reset.setEnabled(false);
	}
	private void addButtons()
	{
		pa[1].setLayout(new GridLayout(3,3));
		TicListener listener=new TicListener();
		for(int i=0;i<9;i++)
		{
			bt[i]=new JButton();
			bt[i].addActionListener(listener);
			bt[i].setBackground(Color.yellow);
			pa[1].add(bt[i]);
		}
	}
	class TicListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt) 
		{
			JButton bb=(JButton)evt.getSource();
			if(player==1)
			{
				bb.setIcon(icon1);
				msg.setText("Second player turn...");
				pa[0].setBackground(Color.magenta);
				msg.setForeground(Color.white);
				player=2;
				findWinner(icon1);
			}
			else if(player==2)
			{
				bb.setIcon(icon2);
				msg.setText("First player turn...");
				pa[0].setBackground(Color.white);
				msg.setForeground(Color.blue);
				player=1;
				findWinner(icon2);
			}
			bb.setEnabled(false);
			//code to check tie/draw
			count++;
			if(count==9 && !winnerFound)
			{
				msg.setText("Game is over");
				msg.setForeground(Color.white);
				pa[0].setBackground(Color.red);
				JOptionPane.showMessageDialog(fr,"No one is winner");
				reset.setEnabled(true);
			}
		}//end of actionPerformed() method
		private void findWinner(ImageIcon icon)
		{
			//code match row wise images
			if(bt[0].getIcon()==icon && bt[1].getIcon()==icon && bt[2].getIcon()==icon)
				announceWinner(0,1,2);
			if(bt[3].getIcon()==icon && bt[4].getIcon()==icon && bt[5].getIcon()==icon)
				announceWinner(3,4,5);
			if(bt[6].getIcon()==icon && bt[7].getIcon()==icon && bt[8].getIcon()==icon)
				announceWinner(6,7,8);
			if(bt[0].getIcon()==icon && bt[3].getIcon()==icon && bt[6].getIcon()==icon)
				announceWinner(0,3,6);
			if(bt[1].getIcon()==icon && bt[4].getIcon()==icon && bt[7].getIcon()==icon)
				announceWinner(1,4,7);
			if(bt[2].getIcon()==icon && bt[5].getIcon()==icon && bt[8].getIcon()==icon)
				announceWinner(2,5,8);
			if(bt[0].getIcon()==icon && bt[4].getIcon()==icon && bt[8].getIcon()==icon)
				announceWinner(0,4,8);
			if(bt[2].getIcon()==icon && bt[4].getIcon()==icon && bt[6].getIcon()==icon)
				announceWinner(2,4,6);
		}
		private void announceWinner(int i,int j,int k)
		{
			//code to announce winner
			winnerFound=true;
			bt[i].setBackground(Color.red);
			bt[j].setBackground(Color.red);
			bt[k].setBackground(Color.red);
			msg.setText("Game is over");
			msg.setForeground(Color.green);
			pa[0].setBackground(Color.lightGray);
			enableButtons(false);
			if(player==2)
			   JOptionPane.showMessageDialog(fr,"First player won");
			else if(player==1)
				JOptionPane.showMessageDialog(fr,"Second player won");
			reset.setEnabled(true);
		}
		
	}//end of TicListener class
	class ResetListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt) 
		{
			//Code to reset game
			count=0;
			player=1;
			msg.setText("First player turn...");
			pa[0].setBackground(Color.white);
			msg.setForeground(Color.blue);
			for(int i=0;i<9;i++)
			{
				bt[i].setIcon(null);
				bt[i].setBackground(Color.yellow);
			}
			enableButtons(true);
			reset.setEnabled(false);
		}
	}
	private void enableButtons(boolean st)
	{
		//code to enable or disable all buttons
		for(int c=0;c<9;c++)
		{
			bt[c].setEnabled(st);
		}
	}
	public static void main(String[] args) 
	{
		new TicTacToe();
	}//end of main() method
}//end of TicTascToe class

