package com.murat.draughtsgame_assignment


class ModelDraughts(mainActivity: MainActivity) {

    private var _mainactivity = mainActivity
    private var piecesBox = mutableSetOf<PieceDraughts>()
    private var player:String = ""
    private var pieceStatus:String = ""

    private var player1Boolean:Boolean = true
    private var player2Boolean:Boolean = false

    private var pieceUpRightCross:PieceDraughts? = null
    private var pieceUpLeftCross:PieceDraughts? = null
    private var pieceDownRightCross:PieceDraughts? = null
    private var pieceDownLeftCross:PieceDraughts? = null

    private var newdestinationUpRightCross:PieceDraughts? = null
    private var newdestinationUpLeftCross:PieceDraughts? = null
    private var newdestinationDownRightCross:PieceDraughts? = null
    private var newdestinationDownLeftCross:PieceDraughts? = null

    private var totalPieceWhite =0
    private var totalPieceBlack =0
    private var checkPiece:PieceDraughts? = null


    init {
        reset()
    }
    fun calculatePiece(){
        totalPieceWhite = 0
        totalPieceBlack = 0
        for (y in 0..7){
            for (x in 0..7){
                checkPiece = pieceAt(x,y)
                if (checkPiece?.player.toString() == "PLAYER1"){
                    totalPieceWhite += 1
                }
                if (checkPiece?.player.toString() == "PLAYER2"){
                    totalPieceBlack += 1
                }

            }
        }
        _mainactivity.uptadeUI(totalPieceWhite,totalPieceBlack)
    }

    fun movePiece(start:PiecePosition, destination:PiecePosition) {

        val movingPiece = pieceAt(start.indexX, start.indexY) ?: return

        player = movingPiece.player.toString()
        pieceStatus = movingPiece.pieceStatus.toString()

        if (player == "PLAYER1" && player1Boolean) {
            if (pieceStatus == "PAWN") {

                pieceUpRightCross = pieceAt(start.indexX + 1,start.indexY + 1)
                pieceUpLeftCross = pieceAt(start.indexX - 1,start.indexY + 1)

                if (pieceAt(destination.indexX, destination.indexY) != null) return

                if (pieceUpRightCross != null && pieceUpRightCross!!.player.toString() == "PLAYER2"){
                    if ((start.indexX + 2) == destination.indexX && (start.indexY + 2) == destination.indexY){
                        remove(movingPiece)
                        remove(pieceUpRightCross)
                        piecesBox.add(PieceDraughts(PiecePosition(destination.indexX,destination.indexY),movingPiece.player,movingPiece.pieceStatus,movingPiece.resourceID))
                        doubleCheck(destination)
                        player1Boolean=false
                        player2Boolean=true
                        calculatePiece()
                        if (7 == destination.indexY){
                            turntoKing(destination.indexX,destination.indexY)
                        }
                        return
                    }
                }
                if (pieceUpLeftCross != null && pieceUpLeftCross!!.player.toString() == "PLAYER2"){
                    if ((start.indexX - 2) == destination.indexX && (start.indexY + 2) == destination.indexY){
                        remove(movingPiece)
                        remove(pieceUpLeftCross)
                        piecesBox.add(PieceDraughts(PiecePosition(destination.indexX,destination.indexY),movingPiece.player,movingPiece.pieceStatus,movingPiece.resourceID))
                        doubleCheck(destination)
                        player1Boolean=false
                        player2Boolean=true
                        calculatePiece()
                        if (7 == destination.indexY){
                            turntoKing(destination.indexX,destination.indexY)
                        }
                        return
                    }
                }
                if ((start.indexX + 1) == destination.indexX || (start.indexX - 1) == destination.indexX) {
                    if ((start.indexY + 1) == destination.indexY) {
                        remove(movingPiece)
                        piecesBox.add(PieceDraughts(PiecePosition(destination.indexX,destination.indexY),movingPiece.player,movingPiece.pieceStatus,movingPiece.resourceID))
                        player1Boolean=false
                        player2Boolean=true
                        calculatePiece()
                        if (7 == destination.indexY){
                            turntoKing(destination.indexX,destination.indexY)
                        }
                    }
                }
            }
            if (pieceStatus == "KING"){
                pieceUpRightCross = pieceAt(start.indexX + 1,start.indexY + 1)
                pieceUpLeftCross = pieceAt(start.indexX - 1,start.indexY + 1)
                pieceDownRightCross = pieceAt(start.indexX + 1,start.indexY -1)
                pieceDownLeftCross = pieceAt(start.indexX - 1,start.indexY -1)

                if (pieceAt(destination.indexX, destination.indexY) != null) return

                if (pieceUpRightCross != null && pieceUpRightCross!!.player.toString() == "PLAYER2"){
                    if ((start.indexX + 2) == destination.indexX && (start.indexY + 2) == destination.indexY){
                        remove(movingPiece)
                        remove(pieceUpRightCross)
                        piecesBox.add(PieceDraughts(PiecePosition(destination.indexX,destination.indexY),movingPiece.player,movingPiece.pieceStatus,movingPiece.resourceID))
                        doubleCheck(destination)
                        player1Boolean=false
                        player2Boolean=true
                        calculatePiece()
                        return
                    }
                }
                if (pieceUpLeftCross != null && pieceUpLeftCross!!.player.toString() == "PLAYER2"){
                    if ((start.indexX - 2) == destination.indexX && (start.indexY + 2) == destination.indexY){
                        remove(movingPiece)
                        remove(pieceUpLeftCross)
                        piecesBox.add(PieceDraughts(PiecePosition(destination.indexX,destination.indexY),movingPiece.player,movingPiece.pieceStatus,movingPiece.resourceID))
                        doubleCheck(destination)
                        player1Boolean=false
                        player2Boolean=true
                        calculatePiece()
                        return
                    }
                }
                if (pieceDownRightCross != null && pieceDownRightCross!!.player.toString() == "PLAYER2"){
                    if ((start.indexX + 2) == destination.indexX && (start.indexY - 2) == destination.indexY){
                        remove(movingPiece)
                        remove(pieceDownRightCross)
                        piecesBox.add(PieceDraughts(PiecePosition(destination.indexX,destination.indexY),movingPiece.player,movingPiece.pieceStatus,movingPiece.resourceID))
                        doubleCheck(destination)
                        player1Boolean=false
                        player2Boolean=true
                        calculatePiece()
                        return
                    }
                }
                if (pieceDownLeftCross != null && pieceDownLeftCross!!.player.toString() == "PLAYER2"){
                    if ((start.indexX - 2) == destination.indexX && (start.indexY - 2) == destination.indexY){
                        remove(movingPiece)
                        remove(pieceDownLeftCross)
                        piecesBox.add(PieceDraughts(PiecePosition(destination.indexX,destination.indexY),movingPiece.player,movingPiece.pieceStatus,movingPiece.resourceID))
                        doubleCheck(destination)
                        player1Boolean=false
                        player2Boolean=true
                        calculatePiece()
                        return
                    }
                }


                if ((start.indexX + 1) == destination.indexX || (start.indexX - 1) == destination.indexX) {
                    if ((start.indexY + 1) == destination.indexY || (start.indexY) - 1 == destination.indexY) {
                        remove(movingPiece)
                        piecesBox.add(PieceDraughts(PiecePosition(destination.indexX,destination.indexY),movingPiece.player,movingPiece.pieceStatus,movingPiece.resourceID))
                        player1Boolean=false
                        player2Boolean=true
                        calculatePiece()
                        return
                    }
                }
            }
        }
        if (player == "PLAYER2" && player2Boolean) {
            if (pieceStatus == "PAWN") {

                pieceDownRightCross = pieceAt(start.indexX + 1,start.indexY - 1)
                pieceDownLeftCross = pieceAt(start.indexX - 1,start.indexY - 1)

                if (pieceAt(destination.indexX, destination.indexY) != null) return

                if (pieceDownRightCross != null && pieceDownRightCross!!.player.toString() == "PLAYER1"){
                    if ((start.indexX + 2) == destination.indexX && (start.indexY - 2) == destination.indexY){
                        remove(movingPiece)
                        remove(pieceDownRightCross)
                        piecesBox.add(PieceDraughts(PiecePosition(destination.indexX,destination.indexY),movingPiece.player,movingPiece.pieceStatus,movingPiece.resourceID))
                        doubleCheck(destination)
                        player1Boolean=true
                        player2Boolean=false
                        calculatePiece()
                        if (0 == destination.indexY){
                            turntoKing(destination.indexX,destination.indexY)
                        }
                        return
                    }
                }
                if (pieceDownLeftCross != null && pieceDownLeftCross!!.player.toString() == "PLAYER1"){
                    if ((start.indexX - 2) == destination.indexX && (start.indexY - 2) == destination.indexY){
                        remove(movingPiece)
                        remove(pieceDownLeftCross)
                        piecesBox.add(PieceDraughts(PiecePosition(destination.indexX,destination.indexY),movingPiece.player,movingPiece.pieceStatus,movingPiece.resourceID))
                        doubleCheck(destination)
                        player1Boolean=true
                        player2Boolean=false
                        calculatePiece()
                        if (0 == destination.indexY){
                            turntoKing(destination.indexX,destination.indexY)
                        }
                        return
                    }
                }

                if ((start.indexX + 1) == destination.indexX || (start.indexX - 1) == destination.indexX) {
                    if ((start.indexY - 1) == destination.indexY) {
                        remove(movingPiece)
                        piecesBox.add(PieceDraughts(PiecePosition(destination.indexX,destination.indexY),movingPiece.player,movingPiece.pieceStatus,movingPiece.resourceID))
                        player1Boolean=true
                        player2Boolean=false
                        calculatePiece()
                        if (0 == destination.indexY){
                            turntoKing(destination.indexX,destination.indexY)
                        }
                    }
                }

            }
            if (pieceStatus == "KING"){
                pieceUpRightCross = pieceAt(start.indexX + 1,start.indexY + 1)
                pieceUpLeftCross = pieceAt(start.indexX - 1,start.indexY + 1)
                pieceDownRightCross = pieceAt(start.indexX + 1,start.indexY -1)
                pieceDownLeftCross = pieceAt(start.indexX - 1,start.indexY -1)

                if (pieceAt(destination.indexX, destination.indexY) != null) return

                if (pieceUpRightCross != null && pieceUpRightCross!!.player.toString() == "PLAYER1"){
                    if ((start.indexX + 2) == destination.indexX && (start.indexY + 2) == destination.indexY){
                        remove(movingPiece)
                        remove(pieceUpRightCross)
                        piecesBox.add(PieceDraughts(PiecePosition(destination.indexX,destination.indexY),movingPiece.player,movingPiece.pieceStatus,movingPiece.resourceID))
                        doubleCheck(destination)
                        player1Boolean=true
                        player2Boolean=false
                        calculatePiece()
                        return
                    }
                }
                if (pieceUpLeftCross != null && pieceUpLeftCross!!.player.toString() == "PLAYER1"){
                    if ((start.indexX - 2) == destination.indexX && (start.indexY + 2) == destination.indexY){
                        remove(movingPiece)
                        remove(pieceUpLeftCross)
                        piecesBox.add(PieceDraughts(PiecePosition(destination.indexX,destination.indexY),movingPiece.player,movingPiece.pieceStatus,movingPiece.resourceID))
                        doubleCheck(destination)
                        player1Boolean=true
                        player2Boolean=false
                        calculatePiece()
                        return
                    }
                }
                if (pieceDownRightCross != null && pieceDownRightCross!!.player.toString() == "PLAYER1"){
                    if ((start.indexX + 2) == destination.indexX && (start.indexY - 2) == destination.indexY){
                        remove(movingPiece)
                        remove(pieceDownRightCross)
                        piecesBox.add(PieceDraughts(PiecePosition(destination.indexX,destination.indexY),movingPiece.player,movingPiece.pieceStatus,movingPiece.resourceID))
                        doubleCheck(destination)
                        player1Boolean=true
                        player2Boolean=false
                        calculatePiece()
                        return
                    }
                }
                if (pieceDownLeftCross != null && pieceDownLeftCross!!.player.toString() == "PLAYER1"){
                    if ((start.indexX - 2) == destination.indexX && (start.indexY - 2) == destination.indexY){
                        remove(movingPiece)
                        remove(pieceDownLeftCross)
                        piecesBox.add(PieceDraughts(PiecePosition(destination.indexX,destination.indexY),movingPiece.player,movingPiece.pieceStatus,movingPiece.resourceID))
                        doubleCheck(destination)
                        player1Boolean=true
                        player2Boolean=false
                        calculatePiece()
                        return
                    }
                }

                if ((start.indexX + 1) == destination.indexX || (start.indexX - 1) == destination.indexX) {
                    if ((start.indexY + 1) == destination.indexY || (start.indexY) - 1 == destination.indexY) {
                        remove(movingPiece)
                        piecesBox.add(PieceDraughts(PiecePosition(destination.indexX,destination.indexY),movingPiece.player,movingPiece.pieceStatus,movingPiece.resourceID))
                        player1Boolean=true
                        player2Boolean=false
                        calculatePiece()
                        return
                    }
                }
            }
        }
    }

    fun doubleCheck(position: PiecePosition){
        var movingPieceCheck = pieceAt(position.indexX,position.indexY)!!

        pieceUpRightCross = pieceAt(position.indexX + 1,position.indexY + 1)
        pieceUpLeftCross = pieceAt(position.indexX - 1,position.indexY + 1)
        pieceDownRightCross = pieceAt(position.indexX + 1,position.indexY - 1)
        pieceDownLeftCross = pieceAt(position.indexX - 1,position.indexY - 1)

        newdestinationUpRightCross = pieceAt(position.indexX + 2, position.indexY + 2)
        newdestinationUpLeftCross = pieceAt(position.indexX - 2, position.indexY + 2)
        newdestinationDownRightCross = pieceAt(position.indexX + 2 , position.indexY - 2)
        newdestinationDownLeftCross = pieceAt(position.indexX - 2 , position.indexY - 2)

        if (movingPieceCheck.player.toString() == "PLAYER1"){
            if (movingPieceCheck.pieceStatus.toString() == "PAWN"){

                if (pieceUpRightCross != null && pieceUpRightCross!!.player.toString() == "PLAYER2"){
                    if (newdestinationUpRightCross != null) return
                    if (position.indexX < 6 && position.indexY<6){
                        remove(pieceUpRightCross)
                        remove(movingPieceCheck)
                        piecesBox.add(PieceDraughts(PiecePosition(position.indexX + 2,position.indexY + 2),movingPieceCheck.player,movingPieceCheck.pieceStatus,movingPieceCheck.resourceID))
                        calculatePiece()
                        if (7 == position.indexY + 2){
                            turntoKing(position.indexX + 2,position.indexY + 2)
                        }
                        return
                    }
                    return
                }

                if (pieceUpLeftCross != null && pieceUpLeftCross!!.player.toString() == "PLAYER2"){
                    if (newdestinationUpLeftCross != null) return
                    if (position.indexX > 1 && position.indexY < 6){
                        remove(pieceUpLeftCross)
                        remove(movingPieceCheck)
                        piecesBox.add(PieceDraughts(PiecePosition(position.indexX - 2,position.indexY + 2),movingPieceCheck.player,movingPieceCheck.pieceStatus,movingPieceCheck.resourceID))
                        calculatePiece()
                        if (7 == position.indexY + 2){
                            turntoKing(position.indexX - 2,position.indexY + 2)
                        }
                        return
                    }
                    return
                }

            }
            if (movingPieceCheck.pieceStatus.toString() == "KING"){

                if (pieceUpLeftCross != null && pieceUpLeftCross!!.player.toString() == "PLAYER2"){
                    if (newdestinationUpLeftCross != null) return
                    if (position.indexX > 1 && position.indexY < 6){
                        remove(pieceUpLeftCross)
                        remove(movingPieceCheck)
                        piecesBox.add(PieceDraughts(PiecePosition(position.indexX - 2,position.indexY + 2),movingPieceCheck.player,movingPieceCheck.pieceStatus,movingPieceCheck.resourceID))
                        calculatePiece()
                        return
                    }
                    return
                }

                if (pieceUpRightCross != null && pieceUpRightCross!!.player.toString() == "PLAYER2"){
                    if (newdestinationUpRightCross != null) return
                    if (position.indexX < 6 && position.indexY < 6){
                        remove(pieceUpRightCross)
                        remove(movingPieceCheck)
                        piecesBox.add(PieceDraughts(PiecePosition(position.indexX + 2,position.indexY + 2),movingPieceCheck.player,movingPieceCheck.pieceStatus,movingPieceCheck.resourceID))
                        calculatePiece()
                        return
                    }
                    return
                }

                if (pieceDownRightCross != null && pieceDownRightCross!!.player.toString() == "PLAYER2"){
                    if (newdestinationDownRightCross != null) return
                    if (position.indexX < 6 && position.indexY > 1){
                        remove(pieceDownRightCross)
                        remove(movingPieceCheck)
                        piecesBox.add(PieceDraughts(PiecePosition(position.indexX + 2,position.indexY - 2),movingPieceCheck.player,movingPieceCheck.pieceStatus,movingPieceCheck.resourceID))
                        calculatePiece()
                        return
                    }
                    return
                }

                if (pieceDownLeftCross != null && pieceDownLeftCross!!.player.toString() == "PLAYER2"){
                    if (newdestinationDownLeftCross != null) return
                    if (position.indexX > 1 && position.indexY > 1){
                        remove(pieceDownLeftCross)
                        remove(movingPieceCheck)
                        piecesBox.add(PieceDraughts(PiecePosition(position.indexX - 2,position.indexY - 2),movingPieceCheck.player,movingPieceCheck.pieceStatus,movingPieceCheck.resourceID))
                        calculatePiece()
                        return
                    }
                    return
                }
            }
        }

        if (movingPieceCheck.player.toString() == "PLAYER2"){
            if (movingPieceCheck.pieceStatus.toString() == "PAWN"){

                if (pieceDownRightCross != null && pieceDownRightCross!!.player.toString() == "PLAYER1"){
                    if (newdestinationDownRightCross != null) return
                    if (position.indexX < 6 && position.indexY > 1){
                        remove(pieceDownRightCross)
                        remove(movingPieceCheck)
                        piecesBox.add(PieceDraughts(PiecePosition(position.indexX + 2,position.indexY - 2),movingPieceCheck.player,movingPieceCheck.pieceStatus,movingPieceCheck.resourceID))
                        calculatePiece()
                        if (0 == position.indexY - 2){
                            turntoKing(position.indexX + 2,position.indexY - 2)
                        }
                        return
                    }
                    return
                }

                if (pieceDownLeftCross != null && pieceDownLeftCross!!.player.toString() == "PLAYER1"){
                    if (newdestinationDownLeftCross != null) return
                    if (position.indexX > 1 && position.indexY > 1){
                        remove(pieceDownLeftCross)
                        remove(movingPieceCheck)
                        piecesBox.add(PieceDraughts(PiecePosition(position.indexX - 2,position.indexY - 2),movingPieceCheck.player,movingPieceCheck.pieceStatus,movingPieceCheck.resourceID))
                        calculatePiece()
                        if (0 == position.indexY - 2){
                            turntoKing(position.indexX - 2,position.indexY - 2)
                        }
                        return
                    }
                    return
                }

            }
            if (movingPieceCheck.pieceStatus.toString() == "KING"){

                if (pieceUpLeftCross != null && pieceUpLeftCross!!.player.toString() == "PLAYER1"){
                    if (newdestinationUpLeftCross != null) return
                    if (position.indexX > 1 && position.indexY < 6){
                        remove(pieceUpLeftCross)
                        remove(movingPieceCheck)
                        piecesBox.add(PieceDraughts(PiecePosition(position.indexX - 2,position.indexY + 2),movingPieceCheck.player,movingPieceCheck.pieceStatus,movingPieceCheck.resourceID))
                        calculatePiece()
                        return
                    }
                    return
                }

                if (pieceUpRightCross != null && pieceUpRightCross!!.player.toString() == "PLAYER1"){
                    if (newdestinationUpRightCross != null) return
                    if (position.indexX < 6 && position.indexY < 6){
                        remove(pieceUpRightCross)
                        remove(movingPieceCheck)
                        piecesBox.add(PieceDraughts(PiecePosition(position.indexX + 2,position.indexY + 2),movingPieceCheck.player,movingPieceCheck.pieceStatus,movingPieceCheck.resourceID))
                        calculatePiece()
                        return
                    }
                    return
                }

                if (pieceDownRightCross != null && pieceDownRightCross!!.player.toString() == "PLAYER1"){
                    if (newdestinationDownRightCross != null) return
                    if (position.indexX < 6 && position.indexY > 1){
                        remove(pieceDownRightCross)
                        remove(movingPieceCheck)
                        piecesBox.add(PieceDraughts(PiecePosition(position.indexX + 2,position.indexY - 2),movingPieceCheck.player,movingPieceCheck.pieceStatus,movingPieceCheck.resourceID))
                        calculatePiece()
                        return
                    }
                    return
                }

                if (pieceDownLeftCross != null && pieceDownLeftCross!!.player.toString() == "PLAYER1"){
                    if (newdestinationDownLeftCross != null) return
                    if (position.indexX > 1 && position.indexY > 1){
                        remove(pieceDownLeftCross)
                        remove(movingPieceCheck)
                        piecesBox.add(PieceDraughts(PiecePosition(position.indexX - 2,position.indexY - 2),movingPieceCheck.player,movingPieceCheck.pieceStatus,movingPieceCheck.resourceID))
                        calculatePiece()
                        return
                    }
                    return
                }
            }
        }
    }

    fun remove(piece:PieceDraughts?){
        piecesBox.remove(piece)
    }

    fun pieceAt(col:Int, row:Int):PieceDraughts?{
        for (piece in piecesBox){
            if (col == piece.piecePosition.indexX && row == piece.piecePosition.indexY){
                return piece
            }
        }
        return null
    }

    fun turntoKing(col: Int,row: Int){
        val player = pieceAt(col, row)

        if (player?.player.toString() == "PLAYER1"){
            remove(pieceAt(col, row))
            piecesBox.add(PieceDraughts(PiecePosition(col,row), PlayerDraughts.PLAYER1,PieceStatus.KING,R.drawable.white_king))
        }
        if (player?.player.toString() == "PLAYER2"){
            piecesBox.remove(pieceAt(col, row))
            piecesBox.add(PieceDraughts(PiecePosition(col,row), PlayerDraughts.PLAYER2,PieceStatus.KING,R.drawable.black_king))
        }
    }

    fun reset(){
        piecesBox.removeAll(piecesBox)

        player1Boolean = true
        player2Boolean = false

        for(y in 0..7){
            for (x in 0..7){
                if (y <= 2 && (x+y) % 2 == 0){
                    piecesBox.add(PieceDraughts(PiecePosition(x,y),PlayerDraughts.PLAYER1,PieceStatus.PAWN,R.drawable.white_pawn))
                }
                if (y >= 5 && (x+y) % 2 == 0){
                    piecesBox.add(PieceDraughts(PiecePosition(x,y),PlayerDraughts.PLAYER2,PieceStatus.PAWN,R.drawable.black_pawn))
                }
            }
        }
    }
}