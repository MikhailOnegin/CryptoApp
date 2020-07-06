package com.example.cryptoapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.CoinViewModel
import com.example.cryptoapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.full_coins_info_fragment.*

class FullCoinInfoFragment: Fragment() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var iVCoin:ImageView
    private lateinit var tVCoinName:TextView
    private lateinit var tVCoinMaxPrice:TextView
    private lateinit var tVCoinMinPrice:TextView
    private lateinit var tVCoinLastDeal:TextView
    private lateinit var tVCoinUpdate:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.full_coins_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iVCoin = imageView_full_coins_info_fragment_logo
        tVCoinName = textView_full_coins_info_fragment_logo_name
        tVCoinMaxPrice = textView_full_coins_info_fragment_max_price
        tVCoinMinPrice = textView__full_coins_info_fragment_min_price
        tVCoinLastDeal = textView_full_coins_info_fragment_last_deal
        tVCoinUpdate = textView_full_coins_info_fragment_update
        viewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application).create(CoinViewModel::class.java)

        val fromSymbol:String? = arguments?.getString(CoinsInfoFragment.EXTRA_FROM_SYMBOL)
        if(fromSymbol == null){
            activity?.finish()
            return
        }
        viewModel.priceList.observe(viewLifecycleOwner, Observer {
            Log.i("eeee","Bundle: $fromSymbol")
            Log.i("eeee","Full fragment: $it")

        })
        viewModel.getDetailInfo(fromSymbol).observe(viewLifecycleOwner, Observer {
            Picasso.get().load(it.getFullImageUrl()).into(iVCoin)
            tVCoinMaxPrice.text = it.price
            tVCoinName.text = it.fromSymbol
            tVCoinMaxPrice.text = it.high24Hour
            tVCoinMinPrice.text = it.low24Hour
            tVCoinLastDeal.text = it.lastMarket
            tVCoinUpdate.text = it.getFormattedTime()


        })
    }
}