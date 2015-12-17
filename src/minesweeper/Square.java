package minesweeper;

import java.util.ArrayList;

public class Square{
	public int square;
	public int numberOnSquare;
	public ArrayList<Integer> surroundingSquares;
	public ArrayList<Integer> surroundingNonClickedSquares;
	public int surroundingFlags;
	
	public Square(int square){
		this.square = square;
		this.numberOnSquare = Board.getNumberOnSquare(square);
		this.surroundingSquares = Board.getSurroundingSquares(square);
		this.surroundingNonClickedSquares = getSurroundingNonClicked();
		this.surroundingFlags = getSurroundingFlags();
	}
	
	private int getSurroundingFlags(){
		int surroundingFlags = 0;
		
		for (int surroundingSquare : this.surroundingSquares){
			if (Board.getNumberOnSquare(surroundingSquare) == 9){
				surroundingFlags++;
			}
		}
		return surroundingFlags;
	}
	
	private ArrayList<Integer> getSurroundingNonClicked(){
		ArrayList<Integer> surroundingNonClicked = new ArrayList<Integer>();
		
		for (int surroundingSquare : this.surroundingSquares){
			if (Board.getNumberOnSquare(surroundingSquare) == 8){
				surroundingNonClicked.add(surroundingSquare);
			}
		}
		return surroundingNonClicked;
	}
	
	public void updateSurroundings(){
		this.surroundingFlags = getSurroundingFlags();
		this.surroundingNonClickedSquares = getSurroundingNonClicked();
	}

	private void removeNonClicked(int squareToRemove){
		try{
			int index = surroundingNonClickedSquares.indexOf(squareToRemove);
			surroundingNonClickedSquares.remove(index);
		}
		catch(Exception E){
		}
	}

	public boolean hasNextNonClicked() {
		if (surroundingNonClickedSquares.isEmpty()){
			return false;
		}
		return true;
	}
	
	public Integer nextNonClicked() {
		return surroundingNonClickedSquares.get(0);
	}
	
	public void printData(){
		System.out.print("square:"+this.square);
		System.out.print(" number:"+this.numberOnSquare);
		System.out.print(" flags:"+this.surroundingFlags);
		System.out.print(" nonClicked:"+this.surroundingNonClickedSquares.size());
		System.out.println();
	}

	public static void updateTheSurroundingSquares(int centerSquare){
		ArrayList<Integer> surroundingSquares = Board.getSurroundingSquares(centerSquare);
		int ceneterSquareNumber = Board.getNumberOnSquare(centerSquare);
		
		for (int surrSquare : surroundingSquares){
			Square surroundingSquareWithNumber = Maps.squareMap.get(surrSquare);
			
			if (surroundingSquareWithNumber != null){
				if (ceneterSquareNumber == 9){
					surroundingSquareWithNumber.surroundingFlags++;
				}
				surroundingSquareWithNumber.removeNonClicked(centerSquare);
				Maps.squareMap.put(surrSquare, surroundingSquareWithNumber);
			}
		}
	}
}