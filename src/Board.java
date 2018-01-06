import java.util.Random;

public class Board {
	//size is the dimension of the square. Change this one value, and everything will scale appropriately
	//has an upper limit of 1000, where each cell will be the size of one pixel
	static int size = 101;
	boolean board[][] = new boolean[size][size];
	static int pop = 125000;
	
	public Board(){
		//initialize all the values to false
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				board[i][j] = false;
			}
		}
		
		//then randomly generate points on there. Decided I wanted 1/4 of the pixels to start alive.
		//randomly generates two coordinates and makes it true.
		Random rand = new Random();
		for(int x = 0; x < size*size/4; x++){
			int y = rand.nextInt(size);
			int z = rand.nextInt(size);
			board[y][z] = true;
		}
	
	}


	public void setSize(int size) {
		this.size = size;
	}

	public boolean[][] getBoard() {
		return board;
	}

	public void setBoard(boolean[][] board) {
		this.board = board;
	}

	//used to display the current board state in the console
	public String toString(){
		String ret = "";
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(board[i][j])
					ret+="○";
				else
					ret+="-";
			}
			ret+="\n";
		}
		ret+="______________";
		return ret;
	}

	//this method takes in the original board array and manipulates it directly, changing it to the next frame
	public void cycle(){
		int neighbors;

		//this is where we will store the changes as our count function operates on the 
		//original board parameter, otherwise if we changed the original board itself, it would affect the answer
		//of the counting algorithm in the later cells.
		boolean temp[][] = new boolean[size][size];

		//this loop will go through each item in the parameter array, copy its value to the temp array
		//count its neighbors, and then either revive, kill, or leave the item alone.
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				temp[i][j] = board[i][j];
				neighbors = countNeighbors(board, i, j);

				//kill if # of neighbors not 2 or 3
				if(board[i][j] && (neighbors < 2 || neighbors > 3)){
					temp[i][j] = false;
				}
				//revive if dead cell has 3 neighbors
				if(!board[i][j] && neighbors == 3)
					temp[i][j] = true;
			}
		}
		setBoard(temp);
	}
	
	//prints out current board state and 9 concurrent cycles in the console
	public void printTen(){
		System.out.println(this);
		this.cycle();
		System.out.println(this);
		this.cycle();
		System.out.println(this);
		this.cycle();
		System.out.println(this);
		this.cycle();
		System.out.println(this);
		this.cycle();
		System.out.println(this);
		this.cycle();
		System.out.println(this);
		this.cycle();
		System.out.println(this);
		this.cycle();
		System.out.println(this);
		this.cycle();
		System.out.println(this);
	}

	//checks every cell around it and counts how many are alive. Does take into account wraparound.
	public static int countNeighbors(boolean board[][], int i, int j){
		int ret = 0;
		int up = (i+size-1)%size;
		int down = (i+size+1)%size;
		int center = i;
		
		int left = (j+size-1)%size;
		int right = (j+size+1)%size;
		int middle = j;

		if(board[up][left])
			ret++;
		if(board[up][middle])
			ret++;
		if(board[up][right])
			ret++;
		if(board[center][left])
			ret++;
		if(board[center][right])
			ret++;
		if(board[down][left])
			ret++;
		if(board[down][middle])
			ret++;
		if(board[down][right])
			ret++;
		return ret;
	}

}
