package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.MajorMove;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATE = {8,16};

    public Pawn(final int piecePosition,final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE){
            int candidateDestinationCoordinate = piecePosition + currentCandidateOffset*pieceAlliance.getDirections();
            if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                continue;
            }
            if(currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                //TODO : more work to do here (deal with promotions)
                legalMoves.add(new MajorMove(board,this, candidateDestinationCoordinate));
            }else if(currentCandidateOffset==16 &&this.isFirstMove() &&
                    (BoardUtils.SECOND_ROW[this.piecePosition] && pieceAlliance.isBlack()) ||
                    (BoardUtils.SEVENTH_ROW[this.piecePosition] && pieceAlliance.isWhite())/*  && (true and true)*/){
                final int behindCandidateDestinationCoordinate = piecePosition + (this.pieceAlliance.getDirections()*8);
                if (!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                }
            }else if (currentCandidateOffset==7 &&
                    !((BoardUtils.EIGHT_COLUMN[this.piecePosition] && pieceAlliance.isWhite()) ||
                     (BoardUtils.FIRST_COLUMN[this.piecePosition] && pieceAlliance.isBlack()))){
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance())
                        //TODO more work to do here.
                        legalMoves.add(new Move.MajorMove(board,this,candidateDestinationCoordinate));
                }
            }else if (currentCandidateOffset==9 &&
                    !((BoardUtils.EIGHT_COLUMN[this.piecePosition] && pieceAlliance.isBlack()) ||
                     (BoardUtils.FIRST_COLUMN[this.piecePosition] && pieceAlliance.isWhite()))
            ){
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance())
                        //TODO more work to do here.
                        legalMoves.add(new Move.MajorMove(board,this,candidateDestinationCoordinate));
                }
            }
        }
        return legalMoves;
    }
}
