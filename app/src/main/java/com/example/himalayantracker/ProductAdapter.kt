package com.example.himalayantracker

import android.app.Activity
import android.content.Intent
import android.location.Geocoder
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultListener
import com.razorpay.PaymentResultWithDataListener
import com.squareup.picasso.Picasso
import org.json.JSONObject
import java.io.IOException


class ProductAdapter(val context : Activity , val productArrayList : List<Hotel> ,  val PaymentResultWithDataListener: PaymentResultWithDataListener) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.hotels_list , parent , false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productArrayList[position]
        holder.title.text = "Hotel Name: ${currentItem.name}"
        // holder.image.setImageResource(currentItem)
        // third party library picasso
        Picasso.get().load(currentItem.pictureUrl).into(holder.image)
        holder.Location.text =  "Hotel Location : ${currentItem.location}"
        holder.ratingBar.rating = currentItem.rating.toFloatOrNull() ?: 0.0f
        holder.price.text = "Room Price :  ₹${currentItem.roomPrice}/-"
        holder.email.text = "Hotel Email : ${currentItem.email}"
        holder.phone.text = "Ph.No : ${currentItem.phone}"
        holder.btnBook.text = "Book Now : ₹${currentItem.roomPrice}"



        // payment gateway
        holder.btnBook.setOnClickListener{
            val co = Checkout()
            co.setKeyID("rzp_test_JIt75TRR2khxQv")
            val options = JSONObject()
            try {
                options.put("name", "Himalyan-Tracker")
                options.put("description", currentItem.name)
                options.put("image", currentItem.pictureUrl)
                options.put("theme.color", "#3399cc")
                options.put("currency", "INR")
//                options.put("App Name",R.string.app_name)
//                options.put("order_id", "order_DBJOWzybf0sJbb")
                val roomPrice = currentItem.roomPrice.toDouble() // Convert roomPrice to Double
                options.put("amount", (roomPrice * 100).toInt()) // M
                val retryObj = JSONObject()
                retryObj.put("enabled", true)
                retryObj.put("max_count", 4)
                options.put("retry", retryObj)

                val prefill = JSONObject()
                prefill.put("email", currentItem.email)
                prefill.put("contact", currentItem.phone)
                options.put("prefill", prefill)

                co.open(context as Activity, options)
            } catch (e: Exception) {
                Toast.makeText(context, "Error in payment: ${e.message}", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }

        }

        holder.Location.setOnClickListener{

            openGoogleMaps(currentItem.location)
        }



    }
    private fun openGoogleMaps(locationName: String) {
        val geocoder = Geocoder(context)
        try {
            val addresses = geocoder.getFromLocationName(locationName, 1)
            if (addresses!!.isNotEmpty()) {
                val latitude = addresses[0].latitude
                val longitude = addresses[0].longitude
                val uri = "geo:$latitude,$longitude"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                intent.setPackage("com.google.android.apps.maps")
                context.startActivity(intent)
            } else {
                Toast.makeText(context, "Location not found", Toast.LENGTH_SHORT).show()
            }
        } catch (e: IOException) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }






    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title : TextView
        var image : ShapeableImageView
        var btnBook : Button
        var Location : Button
        var ratingBar : RatingBar
        var price : TextView
        var email : TextView
        var phone : TextView
        init{
            price = itemView.findViewById(R.id.price)
            title = itemView.findViewById(R.id.productTitle)
            image = itemView.findViewById(R.id.productImage)
            btnBook = itemView.findViewById(R.id.bookNowButton)
            Location = itemView.findViewById(R.id.locationButton)
            ratingBar = itemView.findViewById(R.id.ratingBar)
            email = itemView.findViewById(R.id.email)
            phone = itemView.findViewById(R.id.phonenumber)

        }
    }

    fun onPaymentSuccess(p0: String?, p1: PaymentData?) {

    }

    fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Toast.makeText(context, "Error : ${p1.toString()}", Toast.LENGTH_SHORT).show()
    }
}