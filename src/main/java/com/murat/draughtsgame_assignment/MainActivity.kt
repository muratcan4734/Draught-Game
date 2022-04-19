package com.murat.draughtsgame_assignment

import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() ,DraughtsInterface{

    private var modelDraughts = ModelDraughts(this)
    private lateinit var customViewDraughts: CustomViewDraughts
    private var color1Name:String = ""
    private var color2Name:String = ""
    private var color1Value:String =""
    private var color2Value:String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customViewDraughts = findViewById(R.id.draught_costumview_id)
        customViewDraughts.draughtsInterface = this

        findViewById<Button>(R.id.BtnReset).setOnClickListener {
            modelDraughts.reset()
            customViewDraughts.invalidate()
            findViewById<TextView>(R.id.TxtViewPlayer1).clearComposingText()
            findViewById<TextView>(R.id.TxtViewPlayer1).setText("12")
            findViewById<TextView>(R.id.TxtViewPlayer2).clearComposingText()
            findViewById<TextView>(R.id.TxtViewPlayer2).setText("12")
        }

        val SpnColor1 = findViewById<Spinner>(R.id.Spn_Color1)
        val SpnColor2 = findViewById<Spinner>(R.id.Spn_Color2)

        SpnColor1.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                color1Name = p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        SpnColor2.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                color2Name = p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        findViewById<Button>(R.id.BtnmModify).setOnClickListener {
            if (color1Name == "Cyan"){
                color1Value = "#b2fef7"
            }
            if (color1Name == "Verdigris"){
                color1Value = "#3aa8c1"
            }
            if (color1Name == "DustyGrey"){
                color1Value = "#9e9e9e"
            }
            if (color1Name == "GreenPea"){
                color1Value = "#1D6142"
            }
            if (color2Name == "Petrol"){
                color2Value = "#006064"
            }
            if (color2Name == "DarkGray"){
                color2Value = "#7e7e7e"
            }
            if (color2Name == "PinkSwan"){
                color2Value = "#bab2cd"
            }
            if (color2Name == "Whitesmoke"){
                color2Value = "#f5f5f5"
            }
            customViewDraughts.holdBoardColors(color1Value,color2Value)
            customViewDraughts.invalidate()
        }
    }
    fun uptadeUI(white:Int,black:Int){
        findViewById<TextView>(R.id.TxtViewPlayer1).clearComposingText()
        findViewById<TextView>(R.id.TxtViewPlayer1).setText("" + white)
        findViewById<TextView>(R.id.TxtViewPlayer2).clearComposingText()
        findViewById<TextView>(R.id.TxtViewPlayer2).setText("" + black)
    }

    override fun pieceAt(col: Int, row: Int): PieceDraughts? {
        return modelDraughts.pieceAt(col, row)
    }

    override fun movePiece(start: PiecePosition, destination: PiecePosition) {
        modelDraughts.movePiece(PiecePosition(start.indexX,start.indexY),
            PiecePosition(destination.indexX,destination.indexY)
        )
        customViewDraughts.invalidate()
    }



}