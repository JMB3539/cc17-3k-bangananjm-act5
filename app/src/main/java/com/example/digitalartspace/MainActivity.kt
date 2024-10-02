package com.example.digitalartspace

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

data class Artwork(
    val title: String,
    val artist: String,
    val year: Int,
    val imageResourceId: Int
)

class MainActivity : AppCompatActivity() {

    private var currentArtworkIndex = 0

    private val artworkList = listOf(
        Artwork("Igorot Tribal Woman With the Rice", "Keren Su", 2008, R.drawable.artwork1),
        Artwork("The Birth of Venus", "Sandro Botticelli", 1486, R.drawable.artwork2),
        Artwork("La Primavera", "Sandro Botticelli", 1482, R.drawable.artwork3),
        Artwork("Assumption of the Virgin", "Titian", 1518, R.drawable.artwork4),
        Artwork("Annunciation", "Leonardo da Vinci", 1475, R.drawable.artwork5)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            currentArtworkIndex = it.getInt("currentArtworkIndex", 0)
        }

        val imageView: ImageView = findViewById(R.id.artworkImage)
        val titleTextView: TextView = findViewById(R.id.artworkTitle)
        val artistTextView: TextView = findViewById(R.id.artworkArtist)
        val yearTextView: TextView = findViewById(R.id.artworkYear)
        val previousButton: Button = findViewById(R.id.previousButton)
        val nextButton: Button = findViewById(R.id.nextButton)

        fun displayArtwork(index: Int) {
            val artwork = artworkList[index]
            imageView.setImageResource(artwork.imageResourceId)
            titleTextView.text = artwork.title
            artistTextView.text = artwork.artist
            yearTextView.text = artwork.year.toString()

            previousButton.isEnabled = index > 0
            nextButton.isEnabled = index < artworkList.size - 1
        }

        displayArtwork(currentArtworkIndex)

        previousButton.setOnClickListener {
            if (currentArtworkIndex > 0) {
                currentArtworkIndex--
                displayArtwork(currentArtworkIndex)
            }
        }

        nextButton.setOnClickListener {
            if (currentArtworkIndex < artworkList.size - 1) {
                currentArtworkIndex++
                displayArtwork(currentArtworkIndex)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentArtworkIndex", currentArtworkIndex)
    }
}