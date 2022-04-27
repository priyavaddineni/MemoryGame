package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var previousCardIndex: Int?= null
    private lateinit var buttons: List<ImageButton>
    private lateinit var cards: List<MemoryCard>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images =
            mutableListOf(R.drawable.index, R.drawable.hoya, R.drawable.ganache, R.drawable.theia)
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
            for (card in cards) {   // flip cards back if not matched
                if (!card.isMatched) card.isFaceUp=false
            }

        } else {
            if (cards[previousCardIndex!!].identifier == cards[index].identifier) {
                cards[previousCardIndex!!].isMatched = true
                cards[index].isMatched = true
            }
            previousCardIndex = null
        }
        card.isFaceUp = !card.isFaceUp
    }
}
