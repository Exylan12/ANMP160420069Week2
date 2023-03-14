package com.example.adv160420069week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null) {
            val playerName =
                GameFragmentArgs.fromBundle(requireArguments()).playerName
            val txtTurn = view.findViewById<TextView>(R.id.txtTurn)
            txtTurn.text = "$playerName's Turn"
        }
        val txtNumber1 = view.findViewById<TextView>(R.id.txtNumber1)
        val txtNumber2 = view.findViewById<TextView>(R.id.txtNumber2)
        txtNumber1.text = Random.nextInt(20).toString()
        txtNumber2.text = Random.nextInt(20).toString()
        var point = 0

        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            val txtAnswer = view.findViewById<TextView>(R.id.txtAnswer)
            val answer = Integer.parseInt(txtAnswer.text.toString())
            val finAnswer = Integer.parseInt(txtNumber1.text.toString()) + Integer.parseInt(txtNumber2.text.toString())
            if (answer == finAnswer){
                txtNumber1.text = Random.nextInt(20).toString()
                txtNumber2.text = Random.nextInt(20).toString()
                point += 1
            }
            else{
                val totalPoint = point.toString()
                val actionPoint = GameFragmentDirections.actionResultFragment(totalPoint.toString())
                Navigation.findNavController(it).navigate(actionPoint)
            }
        }
    }

}