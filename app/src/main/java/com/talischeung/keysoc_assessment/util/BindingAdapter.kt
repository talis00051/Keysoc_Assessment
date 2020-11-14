package com.talischeung.keysoc_assessment.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.talischeung.keysoc_assessment.R
import java.lang.Exception

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    if (url != null) {
        Picasso.get().load(url).into(this, object: Callback {
            override fun onSuccess() {

            }

            override fun onError(e: Exception?) {
                this@setImageUrl.setImageResource(R.mipmap.ic_launcher)
            }
        })
    }else{
        Picasso.get().load(R.mipmap.ic_launcher).into(this)
    }
}