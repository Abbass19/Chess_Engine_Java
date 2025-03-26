package com.chess.engine.pieces;
import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {


    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;



    public Piece(final int piecePosition, final Alliance pieceAlliance, boolean isFirstMove) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        //TODO: more work here
        this.isFirstMove = isFirstMove;
    }

    public int getPiecePosition(){
        return this.piecePosition;
    }

    public Alliance getPieceAlliance(){
        return pieceAlliance;
    }
    public boolean isFirstMove(){
        return isFirstMove;
    }
    public abstract Collection<Move> calculateLegalMoves(final Board board);


}
