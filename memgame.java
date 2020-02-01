import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

class game
{
	public static char[][] create_empty()
	{
		char[][] blank_board = new char[5][6];
		int i = 0;
		int j = 1;
		char c = 'A';
		char num = '1';

		blank_board[0][0] = ' ';
		while (j < 5)
		{
			blank_board[0][j] = c;
			j++;
			c++;
		}
		blank_board[0][j] = '\n';
		i = 1;
		
		while (i < 5)
		{
			j = 1;
			blank_board[i][0] = num;
			while (j < 5)
			{
					blank_board[i][j] = '.';
					j++;
			}
			blank_board[i][j] = '\n';
			i++;
			num++;
		}
		
		return blank_board;
		
	}
	
	public static char[][] hidden_board()
	{
		char[][] blank_board = new char[5][6];
		int i = 0;
		int j = 1;
		char c = 'A';
		char num = '1';

		blank_board[0][0] = ' ';
		while (j < 5)
		{
			blank_board[0][j] = c;
			j++;
			c++;
		}
		blank_board[0][j] = '\n';
		i = 1;
		
		while (i < 5)
		{
			j = 1;
			blank_board[i][0] = num;
			while (j < 5)
			{
					blank_board[i][j] = '?';
					j++;
			}
			blank_board[i][j] = '\n';
			i++;
			num++;
		}
		
		return blank_board;
	}
	
	public static char[][] tmp_board()
	{
		char[][] blank_board = new char[5][6];
		int i = 0;
		int j = 1;
		char c = 'A';
		char num = '1';

		blank_board[0][0] = ' ';
		while (j < 5)
		{
			blank_board[0][j] = c;
			j++;
			c++;
		}
		blank_board[0][j] = '\n';
		i = 1;
		
		while (i < 5)
		{
			j = 1;
			blank_board[i][0] = num;
			while (j < 5)
			{
					blank_board[i][j] = '?';
					j++;
			}
			blank_board[i][j] = '\n';
			i++;
			num++;
		}
		
		return blank_board;
	}

	public static char[][] set_cards()
	{
		char [][] board = create_empty();
		String str = "QQWWEERRTTYYXXZZ";
		char[] cards = str.toCharArray();
		int xcoord = ThreadLocalRandom.current().nextInt(1, 5);
		int ycoord = ThreadLocalRandom.current().nextInt(1, 5);
		int i = 0;
		
		while (i < cards.length)
		{
			xcoord = ThreadLocalRandom.current().nextInt(1, 5);
			ycoord = ThreadLocalRandom.current().nextInt(1, 5);
			while (board[ycoord][xcoord] != '.')
			{
				xcoord = ThreadLocalRandom.current().nextInt(1, 5);
				ycoord = ThreadLocalRandom.current().nextInt(1, 5);
			}
			board[ycoord][xcoord] = cards[i];
			i++;
		}
	
		return board;
	}
	
	public static void print_board(char[][] board)
	{
		int i = 0;
		int j = 0;
		
		set_cards();
		
		while (i < 5)
		{
			j = 0;
			System.out.print("  ");
			while (j < 6)
			{
				System.out.print(board[i][j]);
				if ((j == 0 && i != 0) || (j != 5 && i != 0))
					System.out.print(" | ");
				else if (j != 5 && i == 0)
					System.out.print("   ");
				j++;
			}
			if (i != 5)
				System.out.println("    o---o---o---o---o");
			i++;
		}
	}
	
	public static void game(String[] args)
	{
		char[][] temp_board = tmp_board();
		char[][] game_board = hidden_board();
		char[][] solved_board = set_cards();
		char temp = '0';
		int count = 0;
		int exit = 0;
		int i = 0;
		int j = 0;
		int it = 0;
		int jt = 0;
		
		while (exit < 8)
		{
			Scanner scanner = new Scanner(System.in);
			print_board(game_board);
			while (count < 2)
			{
				System.out.println("____________________________________");
				String input = scanner.next();
				char[] coords = input.toCharArray();
				i = Character.getNumericValue(coords[1]);
				j = 0;
				if (coords[0] == 'A')
					j = 1;
				else if (coords[0] == 'B')
					j = 2;
				else if (coords[0] == 'C')
					j = 3;
				else if (coords[0] == 'D')
					j = 4;
				else
					j = 5;
				if (temp_board[i][j] != '?')
				{
					System.out.println("ALREADY UNLOCKED - CHOOSE ANOTHER CARD");
				}
				else if (i > 4 || j > 4)
					System.out.println("COORDINATE ERROR - TRY AGAIN (EXAMPLE: C4)");
				else
				{
					temp_board[i][j] = solved_board[i][j];
					if (count == 0)
					{
						temp = temp_board[i][j];
						it = i;
						jt = j;
					}
					print_board(temp_board);
					count++;
					System.out.println("____________________________________");
				}
			}
			if (temp == temp_board[i][j])
			{
					game_board = temp_board;
					exit++;
				System.out.println("____________________________________");
					System.out.print("MATCH FOUND! NUMBER OF PAIRS: ");
					System.out.println(exit);
					count = 0;
			}
			else if (temp != temp_board[i][j])
			{
					print_board(temp_board);
				System.out.println("____________________________________");
					System.out.println("NO MATCH - TRY AGAIN");
					temp_board[i][j] = '?';
					temp_board[it][jt] = '?';
					count = 0;
			}
		}
		System.out.println("CONGRATULATIONS! YOU WON!");
	}
	
	public static void main(String[] args)
	{
		System.out.println("____________________________________");
		System.out.println("------------MEMORY GAME-------------");
		System.out.println("Enter coordinates (example: A1)");
		System.out.println("____________________________________");
		game(args);
	}
}