package com.example.demo.chessboard;

import java.util.ArrayList;

public class Pawn extends ChessPiece{

	public Pawn(ChessBoard board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		if(this.getColor() == ChessPiece.Color.WHITE) {
			return "\u2659";
		}
		else {
			return "\u265F";
		}
	}

	@Override
	public ArrayList<String> legalMoves() {
		ArrayList<String> moves = new ArrayList<String>();
		
		if(this.color == ChessPiece.Color.WHITE) {
			checkWhitePawnMoves(moves);
		}
		else {
			checkBlackPawnMoves(moves);
		}
		return moves;
	}
	
	private void checkWhitePawnMoves(ArrayList<String> moves) {
			// if in initial position
			if(this.row == 2) {
				for(int i = 1; i < 3; i++) {
					String position = createPositionString(this.row + i, this.column);
					try {
						ChessPiece checkPiece = board.getPiece(position);
						if(checkPiece == null) {
							moves.add(position);
						}
						else {
							break;
						}
					} catch (IllegalPositionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			// if not in initial position
			else if(this.row < 8) {
				String position = createPositionString(this.row + 1, this.column);
				try {
					ChessPiece checkPiece = board.getPiece(position);
					if(checkPiece == null) {
						moves.add(position);
					}

				} catch (IllegalPositionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// capture move
			if(this.row < 8) {
				// check diagonal right
				if(this.column < 8) {
					String position = createPositionString(this.row + 1, this.column + 1);
					try {
						ChessPiece checkPiece = board.getPiece(position);
						if(checkPiece != null && checkPiece.getColor() != this.color) {
							moves.add(position);
						}

					} catch (IllegalPositionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// check diagonal left
				if(this.column > 1) {
					String position = createPositionString(this.row + 1, this.column - 1);
					try {
						ChessPiece checkPiece = board.getPiece(position);
						if(checkPiece != null && checkPiece.getColor() != this.color) {
							moves.add(position);
						}

					} catch (IllegalPositionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		
	}
	
	private void checkBlackPawnMoves(ArrayList<String> moves) {
		if(this.row == 7) {
			for(int i = 1; i < 3; i++) {
				String position = createPositionString(this.row - i, this.column);
				try {
					ChessPiece checkPiece = board.getPiece(position);
					if(checkPiece == null) {
						moves.add(position);
					}
					else {
						break;
					}
				} catch (IllegalPositionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		// if not in initial position
		else if(this.row > 1) {
			String position = createPositionString(this.row - 1, this.column);
			try {
				ChessPiece checkPiece = board.getPiece(position);
				if(checkPiece == null) {
					moves.add(position);
				}

			} catch (IllegalPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// capture move
		if(this.row > 1) {
			// check diagonal right
			if(this.column < 8) {
				String position = createPositionString(this.row - 1, this.column + 1);
				try {
					ChessPiece checkPiece = board.getPiece(position);
					if(checkPiece != null && checkPiece.getColor() != this.color) {
						moves.add(position);
					}

				} catch (IllegalPositionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// check diagonal left
			if(this.column > 1) {
				String position = createPositionString(this.row - 1, this.column - 1);
				try {
					ChessPiece checkPiece = board.getPiece(position);
					if(checkPiece != null && checkPiece.getColor() != this.color) {
						moves.add(position);
					}

				} catch (IllegalPositionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	}

}
