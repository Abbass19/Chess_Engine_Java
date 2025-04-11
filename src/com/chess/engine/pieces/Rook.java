package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.board.BoardUtils.isValidTileCoordinate;

public class Rook extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATE = {-8 ,-1 , 1 ,8};

    public Rook(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> LegalMoves = new ArrayList<>();

        for(final int candidateCoordinateOffset : CANDIDATE_MOVE_COORDINATE){
            int candidateDestinationCoordinate = piecePosition;
            while (isValidTileCoordinate(candidateDestinationCoordinate)){

                if((isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)) || isEightColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset))
                    break;


                candidateDestinationCoordinate+= candidateCoordinateOffset;
                if(isValidTileCoordinate(candidateDestinationCoordinate)){
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if(candidateDestinationTile.isTileOccupied()){
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAtDestinationAlliance = pieceAtDestination.getPieceAlliance();
                        if(pieceAtDestinationAlliance!= pieceAlliance)
                            LegalMoves.add(new Move.AttackMove(board,this,candidateDestinationCoordinate,pieceAtDestination));
                        break;
                    }
                    else {
                        LegalMoves.add(new Move.MajorMove(board,this,candidateDestinationCoordinate));
                    }
                }
            }
        }
        return ImmutableList.copyOf(LegalMoves);
    }
    @Override
    public String toString(){
        return PieceType.ROOK.toString();
    }


    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1);
    }

    private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHT_COLUMN[currentPosition] && (candidateOffset == 1);
    }



}
