package com.example.cryptoapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.pojo.CoinInfo
import com.example.cryptoapp.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin_info.view.*

class CoinInfoAdapter: RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoList:List<CoinPriceInfo> = arrayListOf()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    var onCoinClickListener:OnCoinClickListener? = null

    interface OnCoinClickListener{
        fun onClickItem(coinPriceInfo: CoinPriceInfo){}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return coinInfoList.size
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        holder.tVCoinName.text = coin.fromSymbol + " / " + coin.toSymbol
        holder.tVCoinPrice.text = "${coin.price} $"
        holder.tVCoinUpdate.text = "${holder.itemView.context.getString(R.string.date_last_update)}  ${coin.getFormattedTime()}"
        Log.i("eeee","URL: ${coin.getFullImageUrl()}")
        Picasso.get().load(coin.getFullImageUrl()).into(holder.iVlogoCoin)
        holder.itemView.setOnClickListener{
            val coinPriceInfo = coinInfoList[position]
            onCoinClickListener?.onClickItem(coinPriceInfo)
        }
    }

    inner class CoinInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val iVlogoCoin = itemView.imageView_logo_coin
        val tVCoinName = itemView.textView_name
        val tVCoinPrice = itemView.textView_price
        val tVCoinUpdate = itemView.textView_update_time
    }

}