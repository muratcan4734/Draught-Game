package com.murat.draughtsgame_assignment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CustomViewDraughts(context: Context?,attrs:AttributeSet?):View(context,attrs) {
    private var gridsize = 0f

    private var colorBoard1:String = "#b2fef7"
    private var colorBoard2:String = "#006064"

    private var start_col:Int = 8
    private var start_row:Int = 8

    private val paint = Paint()
    private val imgResID = setOf(
        R.drawable.white_pawn,
        R.drawable.black_pawn,
        R.drawable.white_king,
        R.drawable.black_king)
    private val bitmap = mutableMapOf<Int, Bitmap>()

    var draughtsInterface:DraughtsInterface? = null

    init {
        loadBitmaps()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?:return
        val width:Float = width.toFloat()
        gridsize = width / 8

        drawDraughtboard(canvas)
        drawPieces(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?:return false
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                start_col = (event.x  / gridsize).toInt()
                start_row = 7 - (event.y  / gridsize).toInt()
            }

            MotionEvent.ACTION_UP -> {
                val destination_col = (event.x  / gridsize).toInt()
                val destination_row = 7 - (event.y / gridsize).toInt()

                draughtsInterface?.movePiece(PiecePosition(start_col,start_row),
                    PiecePosition(destination_col,destination_row)
                )
            }
        }
        return true
    }

    fun holdBoardColors(color1:String,color2:String){
        colorBoard1 = color1
        colorBoard2 = color2
    }

    fun drawDraughtboard(canvas: Canvas?){
        for (x in 0..7){
            for (y in 0..7) {
                if ((x+y) % 2 == 0 ) {
                    paint.color = Color.parseColor(colorBoard1)
                } else {
                    paint.color = Color.parseColor(colorBoard2)
                }
                canvas?.drawRect(
                    x * gridsize,
                    y * gridsize,
                    (x + 1) *gridsize,
                    (y + 1) * gridsize, paint)
            }
        }
    }

    private fun drawPieces(canvas: Canvas?){

        for (row in 0..7){
            for (col in 0..7){
                val piece = draughtsInterface?.pieceAt(col, row)
                if (piece != null){
                    drawPieceAt(canvas,col,row,piece.resourceID)
                }
            }
        }
    }

    private fun drawPieceAt(canvas: Canvas?, col:Int, row:Int, resourceId:Int){
        val bitmap = bitmap[resourceId]!!
        canvas?.drawBitmap(bitmap,null,RectF(
            col * gridsize,
            (7 - row) * gridsize,
            (col + 1) * gridsize,
            (8 - row) * gridsize),paint)
    }

    private fun loadBitmaps(){
        imgResID.forEach{
            bitmap[it] = BitmapFactory.decodeResource(resources,it)
        }
    }
}