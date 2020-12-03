package com.example.ifly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ifly.model.Flight
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*


class ItemDetailFragment : Fragment() {

    private var item: Flight? = null
    private lateinit var details: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            details = it.getStringArray("details") as Array<String>
//            if (it.containsKey(ARG_ITEM_ID)) {
//                // Load the dummy content specified by the fragment
//                // arguments. In a real-world scenario, use a Loader
//                // to load content from a content provider.
//                item = FlightContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
//                details =
//                activity?.toolbar_layout?.title = "Code: \n" + item?.code
//            }
            item = Flight(Integer.parseInt(details[0]), details[1], details[2], details[3], details[4], "")
            activity?.toolbar_layout?.title = "Code: \n" + details[4]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        // Show the dummy content as text in a TextView.
        item?.let {
            //rootView.item_detail.text = FlightContent.makeDetails(it.id)
            rootView.item_detail.text = "The flight from " + it.departure + " to " + it.arrival + " will take place on " + it.datetime + ".\n"
        }
        return rootView
    }

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }
}