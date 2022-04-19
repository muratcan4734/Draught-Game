package com.murat.draughtsgame_assignment

interface DraughtsInterface {
    fun pieceAt(col:Int, row:Int):PieceDraughts?
    fun movePiece(start:PiecePosition, destination:PiecePosition)
}