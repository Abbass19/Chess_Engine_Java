package com.chess.engine.board;
import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

import static com.chess.engine.board.BoardUtils.NUM_TILES;

public abstract class Tile {


    protected final int tileCoordinate; //This is for Immutability

    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleTiles();

    private static Map<Integer, EmptyTile> createAllPossibleTiles() {
        final Map<Integer,EmptyTile> emptyTileMap = new HashMap<>();
        for (int i =0 ; i <NUM_TILES; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);

    }

    public static Tile CreateTile(final int tileCoordinate, final Piece piece){
        return piece!=null ? new OccupiedTile(tileCoordinate,piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }

    private Tile (final int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile{
        EmptyTile(final int coordinate) {
            super(coordinate);
        }
        @Override
        public boolean isTileOccupied(){
            return false;
        }
        @Override
        public Piece getPiece(){
            return null;

        }
    }

    public static final class OccupiedTile extends Tile{

        private final Piece pieceOnTile;
        OccupiedTile(final int coordinate,final Piece piece){
            super(coordinate);
            this.pieceOnTile = piece;
        }

        @Override
        public boolean isTileOccupied(){
            return true;
        }

        @Override
        public Piece getPiece(){
            return pieceOnTile;

        }
    }
}

//This class is now Immutable. We have no way of mutating this class. Then this is immutable.
//We can create all the empty Tiles before and when we want to use them we get them