package com.delitx.pescodetest.ui.action

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.NotificationCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.delitx.pescodetest.App
import com.delitx.pescodetest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActionFragment : Fragment() {
    companion object {
        const val NUMBER_KEY = "number_key"
        fun newInstance(number: Int): ActionFragment {
            return ActionFragment().apply {
                arguments = bundleOf(NUMBER_KEY to number)
            }
        }
    }

    private val viewModel: ActionViewModel by viewModels()
    private var newNotification: CardView? = null
    private var plus: CardView? = null
    private var minus: CardView? = null
    private var numberText: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_action, container, false)
        with(view) {
            newNotification = findViewById(R.id.new_notification_button)
            plus = findViewById(R.id.plus_button)
            minus = findViewById(R.id.minus_button)
            numberText = findViewById(R.id.number_text)
            newNotification?.setOnClickListener {
                createNotificationForPage(viewModel.currentFragmentNumber)
            }
            plus?.setOnClickListener {
                viewModel.createNewPage()
            }
            minus?.setOnClickListener {
                viewModel.removePage()
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            viewModel.currentFragmentNumber = try {
                it.getInt(NUMBER_KEY)
            } catch (e: RuntimeException) {
                1
            }
        }
        if (viewModel.currentFragmentNumber == 1) {
            minus?.visibility = View.GONE
        }
        numberText?.text = viewModel.currentFragmentNumber.toString()
    }

    private fun createNotificationForPage(
        page: Int,
        notificationId: Int = viewModel.getNewNotificationId()
    ) {
        val notificationManager = requireActivity()
            .applicationContext
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat
            .Builder(requireContext(), App.NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_message)
            .setContentTitle(getString(R.string.notification_header, page.toString()))
            .setContentText(getString(R.string.notification_body, page.toString()))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(notificationId, notification)
        viewModel.registerNotification(page, notificationId)
    }
}
