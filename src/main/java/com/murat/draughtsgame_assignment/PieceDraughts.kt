package com.murat.draughtsgame_assignment

data class PieceDraughts(val piecePosition: PiecePosition,
                         val player:PlayerDraughts,
                         val pieceStatus: PieceStatus,
                         val resourceID:Int){
}
