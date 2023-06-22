package boardGame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {
		if(rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Piece piece(int row, int column) {
		if(! positionExits(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}

	public Piece piece(Position position) {
		if(! positionExits(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	public void placePiece(Piece piece, Position position) {
		if(theresIsAPiece(position)) {
			throw new BoardException("Theres is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	public Piece removePiece(Position position) {
		if(!positionExits(position)) {
			throw new BoardException("Position not on the board");
		}
		if(piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getColumn()][position.getRow()] = null;
		return aux;
	}

	public boolean positionExits(int row, int column) {
		return row >= 0 && row < rows && column >=0 && column < columns;

	}

	public boolean positionExits(Position position) {
		return positionExits(position.getRow(), position.getColumn());
	}
	
	public boolean theresIsAPiece(Position position) {
		if(! positionExits(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;
	}


}
