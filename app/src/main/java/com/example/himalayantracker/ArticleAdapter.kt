package com.example.himalayantracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ArticleAdapter(context: Context, articles: List<Article>) :
    ArrayAdapter<Article>(context, 0, articles) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val currentArticle = getItem(position)

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false)
        }

//        val ivNewsImage: ImageView = convertView!!.findViewById(R.id.ivNewsImage)
        val tvTitle: TextView = convertView!!.findViewById(R.id.tvTitle)
        val tvDescription: TextView = convertView.findViewById(R.id.tvDescription)

        tvTitle.text = currentArticle!!.title
        tvDescription.text = currentArticle.description

//        if (currentArticle != null && currentArticle.imageUrl != null) {
//            Picasso.get().load(currentArticle.imageUrl).into(ivNewsImage)
//        } else {
//            // Handle the case where there's no image URL provided
//        }

        return convertView
    }
}