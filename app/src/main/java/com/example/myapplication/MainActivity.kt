package com.example.myapplication


import android.os.Bundle
import android.text.Html
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var previousCardIndex: Int?= null
    private lateinit var buttons: List<ImageButton>
    private lateinit var cards: List<MemoryCard>
    private lateinit var numMoves: Button
    private lateinit var numPaired: Button
    private var numOfPairsMatched: Int = 0
    private var numOfSelections: Int = 0
    private lateinit var infoText: TextView
    private lateinit var desc: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val images =
            mutableListOf(R.drawable.halo, R.drawable.hoya, R.drawable.ganache, R.drawable.frames)
        images.addAll(images)
        images.shuffle()

        buttons = listOf(
            findViewById<View>(R.id.imageButton1) as ImageButton,
            findViewById<View>(R.id.imageButton2) as ImageButton,
            findViewById<View>(R.id.imageButton3) as ImageButton,
            findViewById<View>(R.id.imageButton4) as ImageButton,
            findViewById<View>(R.id.imageButton5) as ImageButton,
            findViewById<View>(R.id.imageButton6) as ImageButton,
            findViewById<View>(R.id.imageButton7) as ImageButton,
            findViewById<View>(R.id.imageButton8) as ImageButton
        )

        numMoves = findViewById(R.id.button)
        numPaired = findViewById(R.id.button2)
        infoText = findViewById(R.id.textView2)
        // desc = findViewById(R.id.textView10)
        cards = buttons.indices.map { index ->
            MemoryCard(images[index])
        }


        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                filpCards(index)
                cards.forEachIndexed { index, card ->
                    if (card.isMatched) {
                        buttons[index].alpha = 0.1f
                    }
                    buttons[index].setImageResource(if (card.isFaceUp) card.identifier else R.drawable.amazon)

                }
            }
        }
    }

    private fun filpCards(index: Int) {
        val card = cards[index]
        if (card.isFaceUp) {    // same button clicked, return
            return
        }
        if (previousCardIndex == null) {   // 0 or 2 cards clicked
            previousCardIndex = index
            numOfSelections++
            var text = String.format("Selections: %s", numOfSelections)
            numMoves.setText(text)
            for (card in cards) {   // flip cards back if not matched
                if (!card.isMatched) card.isFaceUp=false
            }

        } else {
            if (cards[previousCardIndex!!].identifier == cards[index].identifier) {
                cards[previousCardIndex!!].isMatched = true
                cards[index].isMatched = true
                numOfPairsMatched++
                val tag1 = buttons[previousCardIndex!!].resources.getResourceEntryName(cards[previousCardIndex!!].identifier)
                val tag2 = buttons[index].resources.getResourceEntryName(cards[index].identifier)
                if( tag1 == "ganache" && tag2 == "ganache") {
                    buttonPopupwindow(infoText)
                }
                if( tag1 == "hoya" && tag2 == "hoya") {
                    buttonPopupwindow2(infoText)
                }
                if( tag1 == "halo" && tag2 == "halo") {
                    buttonPopupwindow3(infoText)
                }
                if( tag1 == "frames" && tag2 == "frames") {
                    buttonPopupwindow4(infoText)
                }
                var text = String.format("Paired: %s/4", numOfPairsMatched)
                numPaired.setText(text)

            }

            numOfSelections++
            var text = String.format("Selections: %s", numOfSelections)
            numMoves.setText(text)
            previousCardIndex = null
        }
        card.isFaceUp = !card.isFaceUp

    }

    private fun buttonPopupwindow(view: View?) {
        val layoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewPopupwindow: View = layoutInflater.inflate(R.layout.popwindowlayout, null)
        val popupWindow = PopupWindow(viewPopupwindow, 900, 500, true)
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
        viewPopupwindow.setOnTouchListener { v, event ->
            popupWindow.dismiss()
            true
        }
    }

    private fun buttonPopupwindow2(view: View?) {
        val layoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewPopupwindow: View = layoutInflater.inflate(R.layout.pop2, null)
        val popupWindow = PopupWindow(viewPopupwindow, 900, 500, true)
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
        viewPopupwindow.setOnTouchListener { v, event ->
            popupWindow.dismiss()
            true
        }
    }

    private fun buttonPopupwindow3(view: View?) {
        val layoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewPopupwindow: View = layoutInflater.inflate(R.layout.pop3, null)
        val popupWindow = PopupWindow(viewPopupwindow, 900, 500, true)
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
        viewPopupwindow.setOnTouchListener { v, event ->
            popupWindow.dismiss()
            true
        }
    }

    private fun buttonPopupwindow4(view: View?) {
        val layoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewPopupwindow: View = layoutInflater.inflate(R.layout.pop4, null)
        val popupWindow = PopupWindow(viewPopupwindow, 900, 500, true)
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
        viewPopupwindow.setOnTouchListener { v, event ->
            popupWindow.dismiss()
            true
        }
    }
}
