package Section21_Game;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe extends JFrame implements ActionListener{
	
	public static int board_size=3; //setting boards size
	
	public static enum GameStatus
	{
		Incomplete, Xwins, Zwins, Tie;
	}
	
	private JButton[][] buttons = new JButton[board_size][board_size];
	boolean crossTurn = true;
	
	public TicTacToe() //constructor
	{
		super.setTitle("TicTacToe");
		super.setSize(800,800);
		GridLayout grid = new GridLayout(board_size,board_size);	//making a grid on the layout
		super.setLayout(grid);
		
		Font font = new Font("Roboto",1,150);	//choosing font type and size
		
		for(int row=0;row<board_size;row++)
		{
			for(int col=0;col<board_size;col++)
			{
				JButton button = new JButton("");
				buttons[row][col]=button;
				button.setFont(font);
				button.addActionListener(this);	//adding  listener to buttons
				super.add(button);
			}
		}
		
		super.setResizable(false);
		super.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		JButton clickedButton = (JButton)e.getSource();
		makeMove(clickedButton);
		
		GameStatus gs = this.getGameStatus();
		
		if(gs==GameStatus.Incomplete)
		{
			return;
		}
		
		declareWinner(gs);
		
		int choice = JOptionPane.showConfirmDialog(this,"Do you want to play again?");
		if(choice==JOptionPane.YES_OPTION)
		{
			for(int row=0;row<board_size;row++)
			{
				for(int col=0;col<board_size;col++)
				{
					buttons[row][col].setText("");
				}
			}
			crossTurn=true;
		}
		else
		{
			super.dispose();
		}
	}
	
	private void declareWinner(GameStatus gs)
	{
		if(gs==GameStatus.Xwins)
		{
			JOptionPane.showMessageDialog(this,"X Wins!");
		}
		else if(gs==GameStatus.Zwins)
		{
			JOptionPane.showMessageDialog(this,"Z Wins!");
		}
		else
		{
			JOptionPane.showMessageDialog(this,"It is a tie");
		}
	}
	
	private void makeMove(JButton clickedButton)
	{
		String btntext = clickedButton.getText();
		if(btntext.length()>0)
		{
			JOptionPane.showMessageDialog(this,"Invalid Move");
		}
		else
		{
			if(crossTurn)
			{
				clickedButton.setText("X");
			}
			else
			{
				clickedButton.setText("0");
			}
			crossTurn=!crossTurn;
		}
	}
	
	private GameStatus getGameStatus()
	{
		String text1="",text2="";
		int row=0,col=0;
		
		//text inside rows
		//first checking rows for winning condition if any
		row=0;
		while(row<board_size)
		{
			col=0;
			while(col<board_size-1)		//checks each for winnning condition
			{
				text1 = buttons[row][col].getText();
				text2 = buttons[row][col+1].getText();
				
				if(!text1.equals(text2) || text1.length()==0)
				{
					break;
				}
				col++;
			}
			
			//if you reached the last row
			if(col==board_size-1)
			{
				if(text1.equals("X"))
				{
					return  GameStatus.Xwins;
				}
				else
					return GameStatus.Zwins;
			}
			row++;
			
		}
		
		//text inside columns
		//now checking cols for winning conditions if any
		col=0;
		while(col<board_size)
		{
			row=0;
			while(row<board_size-1)		//checks each for winnning condition
			{
				text1 = buttons[row][col].getText();
				text2 = buttons[row+1][col].getText();
				
				if(!text1.equals(text2) || text1.length()==0)
				{
					break;
				}
				row++;
			}
			
			//if you reached the last col
			if(row==board_size-1)
			{
				if(text1.equals("X"))
				{
					return  GameStatus.Xwins;
				}
				else
					return GameStatus.Zwins;
			}
			col++;
			
		}
		
		//now checking diagonals
		
		row=0;
		col=0;
		while(row<board_size-1)
		{
			text1 = buttons[row][col].getText();
			text2 = buttons[row+1][col+1].getText();
			
			if(!text1.equals(text2) || text1.length()==0)
			{
				break;
			}
			row++;
			col++;
		}
		
		if(row==board_size-1)
		{
			if(text1.equals("X"))
			{
				return  GameStatus.Xwins;
			}
			else
				return GameStatus.Zwins;
		}
		
		
		//check the other  diagonal
		
		row=board_size-1;
		col=0;
		while(row>0)
		{
			text1 = buttons[row][col].getText();
			text2 = buttons[row-1][col+1].getText();
			
			if(!text1.equals(text2) || text1.length()==0)
			{
				break;
			}
			row--;
			col++;
		}
		
		if(row==0)
		{
			if(text1.equals("X"))
			{
				return  GameStatus.Xwins;
			}
			else
				return GameStatus.Zwins;
		}
		
		String txt = "";
		for(row=0;row<board_size;row++)
		{
			for(col=0;col<board_size;col++)
			{
				txt=buttons[row][col].getText();
				
				if(txt.length()==0)
				{
					return 	GameStatus.Incomplete;
				}
			}
		}
		
		return GameStatus.Tie;
	}
	
	public static void main(String[] args)
	{
		TicTacToe game = new TicTacToe();
		
	}


}
