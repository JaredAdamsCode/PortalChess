package com.example.demo.chessboard;
import java.util.ArrayList;

public class Knight extends ChessPiece {

	public Knight(ChessBoard board, Color color, String type) {
		super(board, color, type);
	}

	@Override
	public String toString() {
		if(this.getColor() == ChessPiece.Color.WHITE) {
			return "\u2658";
		}
		else {
			return "\u265E";
		}
	}

	@Override
	public ArrayList<String> legalMoves() {
		ArrayList<String> moves = new ArrayList<String>();
		// check up 2 right 1
		checkKnightMove(this.row + 2, this.column + 1, moves);
		// check up 1 right 2
		checkKnightMove(this.row + 1, this.column + 2, moves);
		// check down 1 right 2
		checkKnightMove(this.row - 1, this.column + 2, moves);
		// check down 2 right 1
		checkKnightMove(this.row - 2, this.column + 1, moves);
		// check down 2 left 1
		checkKnightMove(this.row - 2, this.column - 1, moves);
		// check down 1 left 2
		checkKnightMove(this.row - 1, this.column - 2, moves);
		// check up 1 left 2
		checkKnightMove(this.row + 1, this.column - 2, moves);
		// check up 2 left 1
		checkKnightMove(this.row + 2, this.column - 1, moves);
		// return arraylist
		return moves;
	}
			
	private void checkKnightMove(int row, int col, ArrayList<String> moves) {
		if(row <= 8 && row >= 1 && col <= 8 && col >= 1) {
			String position = createPositionString(row, col);
			try {
				ChessPiece checkPiece = board.getPiece(position);
				if(checkPiece == null) {
					moves.add(position);
				}
				if(checkPiece != null && checkPiece.getColor() != this.getColor() && !(checkPiece instanceof Portal)) {
					moves.add(position);
				}

				String portalLoc = portalInPath(row, col);
				if(portalLoc != null) {
					ChessPiece portal = board.getPiece(portalLoc);
					int portalRow = Character.getNumericValue(portalLoc.charAt(1));
					int portalCol = portalLoc.charAt(0) - 96;

					String otherPortalLocation = board.getOppositePortalPosition(portal.getColor());
					System.out.println(otherPortalLocation);
					int otherPortalRow = Character.getNumericValue(otherPortalLocation.charAt(1));
					int otherPortalColumn = otherPortalLocation.charAt(0) - 96;

					int rowDifference = row - portalRow;
					int colDifference = col - portalCol;

					int finalRow = otherPortalRow + rowDifference;
					int finalCol = otherPortalColumn + colDifference;
					if(finalRow <= 8 && finalRow >= 1 && finalCol <= 8 && finalCol >= 1) {
						String finalPosition = createPositionString(finalRow, finalCol);
						ChessPiece finalPiece = board.getPiece(finalPosition);
						if(finalPiece == null ||
								( finalPiece.getColor() != this.getColor() && !(finalPiece instanceof Portal) )) {
							moves.add(finalPosition);
						}
					}
				}

			} catch (IllegalPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String portalInPath(int row, int col) {
		try {
			int temp = this.row;
			// Moving up
			if (this.row < row) {
				while (temp <= row) {
					//System.out.println(createValidPositionString(temp, this.column));
					ChessPiece test = board.getPiece(createValidPositionString(temp, this.column));
					if(test instanceof Portal) { return test.getPosition(); }
					temp++;
				}
				temp = this.column;
				if(this.column < col) {
					while(temp < col + 1) {
						//System.out.println(createValidPositionString(row, temp));
						ChessPiece test = board.getPiece(createValidPositionString(row, temp));
						if(test instanceof Portal) { return test.getPosition(); }
						temp++;
					}
				}else {
					while(temp > col - 1) {
						//System.out.println(createValidPositionString(row, temp));
						ChessPiece test = board.getPiece(createValidPositionString(row, temp));
						if(test instanceof Portal) { return test.getPosition(); }
						temp--;
					}
				}
			}

			// Moving down
			else {
				while (temp >= row) {
					//System.out.println(createValidPositionString(temp, this.column));
					ChessPiece test = board.getPiece(createValidPositionString(temp, this.column));
					if(test instanceof Portal) { return test.getPosition(); }
					temp--;
				}
				temp = this.column;
				if(this.column < col) {
					while(temp < col + 1) {
						//System.out.println(createValidPositionString(row, temp));
						ChessPiece test = board.getPiece(createValidPositionString(row, temp));
						if(test instanceof Portal) { return test.getPosition(); }
						temp++;
					}
				}else {
					while(temp > col - 1) {
						//System.out.println(createValidPositionString(row, temp));
						ChessPiece test = board.getPiece(createValidPositionString(row, temp));
						if(test instanceof Portal) { return test.getPosition(); }
						temp--;
					}
				}
			}
		} catch (IllegalPositionException e) {
			e.printStackTrace();
		}
		return null;
	}
}
