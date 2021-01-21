package com.androidstrike.javit.landing.fragments

//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.androidstrike.cofepa.utils.toast
import com.androidstrike.javit.R
import com.google.android.youtube.player.*
import kotlinx.android.synthetic.main.fragment_module.*


class Module : Fragment(), YouTubePlayer.OnInitializedListener {

    private val RECOVERY_DIALOG_REQUEST = 1
    private val TAG = "YoutubeActivity"

    lateinit var vidId: String
    lateinit var modInfo: String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (arguments?.getString("vidId") != null){
            vidId = arguments?.getString("vidId")!!
            modInfo = arguments?.getString("mod_txt")!!
        }
//
//        val youtubePlayerFragment = YouTubePlayerFragment()
//        youtubePlayerFragment.initialize("AIzaSyBnInSfd0VtNuan7blaRXrc2saqHDDTCHU", this)
//        val fragmentManager = requireFragmentManager()
//        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.official_player_view, youtubePlayerFragment as Fragment)
//        fragmentTransaction.commit()


        val youTubePlayerFragment = activity?.fragmentManager?.findFragmentById(R.id.official_player_view) as YouTubePlayerFragment?   //FragmentManager?.findFragmentById(R.id.official_player_view) as YouTubePlayerFragment?
        youTubePlayerFragment?.initialize("AIzaSyBnInSfd0VtNuan7blaRXrc2saqHDDTCHU", this)    //.initialize("AIzaSyBnInSfd0VtNuan7blaRXrc2saqHDDTCHU", activity)

        Log.d("EQUA", "onReady: $vidId")
//
//        third_party_player_view.getPlayerUiController().showFullscreenButton(true)
//        third_party_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//            override fun onReady(youTubePlayer: YouTubePlayer) {
//                val videoId = vidId
//                youTubePlayer.cueVideo(videoId, 0f)
//
//                Log.d("EQUA", "onReady: $videoId")
//            }
//        })
//
//
//        third_party_player_view.getPlayerUiController().setFullScreenButtonClickListener(View.OnClickListener {
//            if (third_party_player_view.isFullScreen()){
//                third_party_player_view.exitFullScreen()
//                activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
//
//            }else{
//                third_party_player_view.enterFullScreen()
//                activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//            }
//        })

        tv_module_text.text = modInfo
    }

    override fun onInitializationSuccess(
            provider: YouTubePlayer.Provider?,
            youTubePlayer: YouTubePlayer?,
            wasRestored: Boolean
    ) {
        youTubePlayer?.setPlayerStateChangeListener(playerStateChangeListener)
        youTubePlayer?.setPlaybackEventListener(playbackEventListener)
        if (!wasRestored){
            youTubePlayer?.cueVideo(vidId)
        }
    }

    override fun onInitializationFailure(
            provider: YouTubePlayer.Provider?,
            youTubeInitializationResult: YouTubeInitializationResult?
    ) {
        if (youTubeInitializationResult!!.isUserRecoverableError){
            youTubeInitializationResult.getErrorDialog(activity, RECOVERY_DIALOG_REQUEST).show()
        }else{
            val errorMessage = String.format(
                    "There was an error initializing the YouTubePlayer (%1\\\$s)",
                    youTubeInitializationResult.toString()
            )
            activity?.toast(errorMessage)
        }
    }

    private val playbackEventListener = object : YouTubePlayer.PlaybackEventListener{
        override fun onPlaying() {
            activity?.toast("Good!, Video is Playing")
        }

        override fun onPaused() {
            activity?.toast("Video is Paused")
        }

        override fun onStopped() {
            activity?.toast("Video is Stopped")
        }

        override fun onBuffering(p0: Boolean) {

        }

        override fun onSeekTo(p0: Int) {

        }
    }

    private val playerStateChangeListener = object : YouTubePlayer.PlayerStateChangeListener{
        override fun onLoading() {

        }

        override fun onLoaded(p0: String?) {

        }

        override fun onAdStarted() {
            activity?.toast("Click ad and support the video creator")
        }

        override fun onVideoStarted() {
            activity?.toast("Good!, Video is Started")
        }

        override fun onVideoEnded() {
            activity?.toast("Congrats!! One more Module Down")
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {

        }

    }
}