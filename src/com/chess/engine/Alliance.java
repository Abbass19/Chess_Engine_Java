package com.chess.engine;

public enum Alliance {
    WHITE {
        @Override
        public int getDirections() {
            return -1;
        }
        @Override
        public boolean isBlack(){
            return false;
        }
        @Override
        public boolean isWhite(){
            return true;
        }

    },
    BLACK {
        @Override
        public boolean isBlack(){
            return true;
        }
        @Override
        public boolean isWhite(){
            return false;
        }


        @Override
        public int getDirections() {
            return 1;
        }
    };

    public abstract int getDirections();
    public abstract boolean isWhite();
    public abstract boolean isBlack();
}
