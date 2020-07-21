package com.example.cryptoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.viewModels.CoinViewModel
import com.example.cryptoapp.R
import com.example.cryptoapp.adapters.CoinInfoAdapter
import com.example.cryptoapp.pojo.CoinPriceInfo
import kotlinx.android.synthetic.main.coins_info_fragment.*

class CoinsInfoFragment : Fragment() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var rVCoins: RecyclerView
    private lateinit var navC: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.coins_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rVCoins = recyclerView_coins_info_fragment
        navC = findNavController()
        val adapter = CoinInfoAdapter()

        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onClickItem(coinPriceInfo: CoinPriceInfo) {
                super.onClickItem(coinPriceInfo)
                val bundle = Bundle()
                bundle.putString(EXTRA_FROM_SYMBOL, coinPriceInfo.fromSymbol)
                navC.navigate(R.id.action_coins_info_to_full_coin_info, bundle)
            }
        }

        rVCoins.adapter = adapter

        viewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(CoinViewModel::class.java)

        viewModel.priceList.observe(viewLifecycleOwner, Observer {
            adapter.coinInfoList = it
        })
    }

    companion object {
        const val EXTRA_FROM_SYMBOL = "fSym"
    }
}