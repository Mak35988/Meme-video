package com.mayurrai.youtubepaisa;

import static android.graphics.Color.RED;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideoViewHolder> {


    private List<VideoItem> mVideoItems;

    public VideosAdapter(List<VideoItem> videoItems) {
        mVideoItems = videoItems;
    }


    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_videoface, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {

        holder.setIsRecyclable(false);
        holder.setVideoData(mVideoItems.get(position));
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mVideoItems.size();
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {

        static private RequestQueue mQueue;

        private ArrayList<String> youtubevideoid = new ArrayList<>();


        YouTubePlayerView VideoView;
        TextView webView;
        ViewPager2 mViewPager;
        Random rand2;
        int r1,x=0,y=0,xx=0;

        String ss[] = {"NJFzg620MOE", "ejvgNcxvAfA", "UGmXCHFVVts", "cL7eDKdsxxI", "tUQlas18igU", "qV9WuEk3USs", "LpHmEt48qzM", "khf8nIIxmEo", "9CrfwFaYiyc", "XqQYv4wntts"};

        ImageView like, share, dislike, biglike, videochanger, kuchnhikarna,rotatingcd,reportvideo;
        boolean buttonlogiclike = false, buttonlogicdislike = false;

        TextView textView;


        @SuppressLint("CutPasteId")
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            VideoView = itemView.findViewById(R.id.youtube_player_view);

            mViewPager = itemView.findViewById(R.id.viewPagerVideos);

            like = itemView.findViewById(R.id.likevideo);
            dislike = itemView.findViewById(R.id.dislike);
            videochanger = itemView.findViewById(R.id.videochanger);
            kuchnhikarna = itemView.findViewById(R.id.kuchnhikarna);
            biglike = itemView.findViewById(R.id.likevideo2);
            rotatingcd = itemView.findViewById(R.id.rotatingcd);
            reportvideo = itemView.findViewById(R.id.reportvideo);


            webView = itemView.findViewById(R.id.webvidew);

            VideoView.getKeepScreenOn();

            textView = itemView.findViewById(R.id.likevideoidtext);

            VideoView.enableBackgroundPlayback(false);
            VideoView.setEnableAutomaticInitialization(false);


            ObjectAnimator imageViewObjectAnimator = ObjectAnimator.ofFloat(rotatingcd ,
                    "rotation", 1f, 360f);
            imageViewObjectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            imageViewObjectAnimator.setRepeatMode(ObjectAnimator.RESTART);
            imageViewObjectAnimator.setInterpolator(new AccelerateInterpolator());
            imageViewObjectAnimator.setDuration(2500);
            imageViewObjectAnimator.start();

            ///////////////////////////////////// for uploading view ///////////////////////////

//
//            uploadvideo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//

//                    Intent intent = new Intent();
//                        intent.setType("video/*");
//                        intent.setAction(Intent.ACTION_GET_CONTENT);
//                        //startActivityForResult(intent, 5);
////                   view.getContext().startActivity(intent);
//
//                    ((Activity) itemView.getContext()).startActivityForResult(intent, 5);
//
//
//                    ContentValues content = new ContentValues(4);
//                    content.put(MediaStore.Video.VideoColumns.DATE_ADDED,
//                            System.currentTimeMillis() / 1000);
//                    content.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4");
//                    content.put(MediaStore.Video.Media.DATA, path);
//                    ContentResolver resolver = itemView.getContext().getContentResolver();
//                    Uri uri1 =  Uri.fromFile(new File(path));
//
//                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//                    sharingIntent.setType("video/*");
//                    sharingIntent.setPackage("com.google.android.youtube");
//                    sharingIntent.putExtra(Intent.EXTRA_TITLE, "Title");
//                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Desc");
//                    sharingIntent.putExtra(android.content.Intent.EXTRA_STREAM, uri1);
//                    view.getContext().startActivity(Intent.createChooser(sharingIntent, "Share to"));


            ///////////////////////////////////// for uploading view ///////////////////////////




            ///////////////////////////////// for report video //////////////////////////////////

            reportvideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(itemView.getContext(),NotificationActivity.class);

                    view.getContext().startActivity(intent1);

                }
            });




            ///////////////////////////////// for report video //////////////////////////////////



            ////////////////// for like button ////////////////////

            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(x %2==0) {
                        like.setColorFilter(itemView.getResources().getColor(R.color.red));


                        biglike.setVisibility(View.VISIBLE);

                        biglike.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                biglike.setVisibility(View.INVISIBLE);
                            }
                        }, 1000);


                        if (buttonlogiclike == false) {
                            like.setColorFilter(itemView.getResources().getColor(R.color.red));

                            buttonlogiclike = true;
                            buttonlogicdislike = false;
                            dislike.setColorFilter(itemView.getResources().getColor(R.color.transparent));


                        }


                        if (buttonlogicdislike == true) {
                            buttonlogicdislike = false;
                            dislike.setColorFilter(itemView.getResources().getColor(R.color.transparent));

                            like.setColorFilter(itemView.getResources().getColor(R.color.red));
                            buttonlogiclike = true;

                        }

                    }


                    else{

                        like.setColorFilter(itemView.getResources().getColor(R.color.white));

                    }

                    x++;

                }

            });


            biglike.setVisibility(View.GONE);

            /////////////////////////// for like button ///////////////////////////


            ////////////////// for dislike button ////////////////////

            dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(y %2==0) {

                        like.setBackgroundTintList(ColorStateList.valueOf(RED));

                        if (buttonlogiclike == true) {
                            buttonlogiclike = false;
                            like.setColorFilter(itemView.getResources().getColor(R.color.transparent));

                            dislike.setColorFilter(itemView.getResources().getColor(R.color.red));

                            buttonlogicdislike = false;
                        }

                        if (buttonlogicdislike == false) {
                            dislike.setColorFilter(itemView.getResources().getColor(R.color.red));

                            buttonlogicdislike = true;
                            buttonlogicdislike = false;
                            like.setColorFilter(itemView.getResources().getColor(R.color.transparent));

                        }

                    }

                    else{
                        dislike.setColorFilter(itemView.getResources().getColor(R.color.transparent));
                    }

                    y++;
                }
            });

            /////////////////////////// for dislike button ///////////////////////////


            ////////////////// for share button ///////////////////////////////////////

            share = itemView.findViewById(R.id.share);


            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


//                    Intent intent = new Intent();
//                            intent.setAction(Intent.ACTION_SEND);
//
//        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Android Studio Pro");
////        intent.putExtra(Intent.EXTRA_TEXT,"Learn Android App Development https://play.google.com/store/apps/details?id=com.tutorial.personal.androidstudiopro ");intent.setType("text/plain");
//
//        intent.putExtra(Intent.EXTRA_TEXT, " vffggggdggdgfd");
//        intent.setType("text/plain");
//
//                    view.getContext().startActivity(intent);


                    try {

                        ArrayList<String> ashok = new ArrayList<String>();

                        parseMovieDetails(new CallBack() {
                            @Override
                            public void onSuccess(ArrayList<String> detailsMovies) {

                                for (int i = 0; i < detailsMovies.size(); i++) {
                                    ashok.add(detailsMovies.get(i));
                                }

                                if(xx==0){

                                    String www = ss[r1];

                                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);


                                    sharingIntent.setType("text/plain");

                                    xx++;

                                    String shareBody = "https://youtu.be/" + www;

                                    String shareSubject = "Your Subject Here";

                                    sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

                                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                                    view.getContext().startActivity(Intent.createChooser(sharingIntent, "Share using"));

                                }


                                else {

                                    String www = ashok.get(r1);
                                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);

                                    sharingIntent.setType("text/plain");

                                    String shareBody = "https://youtu.be/" + www;

                                    String shareSubject = "Your Subject Here";

                                    sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

                                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                                    view.getContext().startActivity(Intent.createChooser(sharingIntent, "Share using"));


                                }


                            }

                            @Override
                            public void onFail(String msg) {
                                // Do Stuff
                            }
                        });


                    }
                    
                    catch (Exception e){

                    }

                }
            });

///////////////////////////////////////////////////// for share button ///////////////

            /////////////////////////////////// opening of app it delete cache memory automatically //////////////////////////////////


    //        deleteCache(itemView.getContext());

            /////////////////////////////////// opening of app it delete cache memory automatically //////////////////////////////////


        }



        /////////////////////////////////// opening of app it delete cache memory automatically //////////////////////////////////

//
//        public static void deleteCache(Context context) {
//            try {
//                File dir = context.getCacheDir();
//                if (dir != null && dir.isDirectory()) {
//                    deleteDir(dir);
//                }
//            } catch (Exception e) {}
//        }
//
//        public static boolean deleteDir(File dir) {
//            if (dir != null && dir.isDirectory()) {
//                String[] children = dir.list();
//                for (int i = 0; i < children.length; i++) {
//                    boolean success = deleteDir(new File(dir, children[i]));
//                    if (!success) {
//                        return false;
//                    }
//                }
//                return dir.delete();
//            }  else if (dir!= null && dir.isFile()) {
//                return dir.delete();
//            }
//            return false;
//        }


        /////////////////////////////////// opening of app it delete cache memory automatically //////////////////////////////////




        //////////////////////////////////////////for wifi and internet connection //////////////////////////////////////////////////

        
        private boolean haveNetworkConnection() {
            boolean haveConnectedWifi = false;
            boolean haveConnectedMobile = false;
            
            
            ConnectivityManager cm = (ConnectivityManager) itemView.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            
                    
        //    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] netInfo = cm.getAllNetworkInfo();
            for (NetworkInfo ni : netInfo) {
                if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                    if (ni.isConnected())
                        haveConnectedWifi = true;
                if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                    if (ni.isConnected())
                        haveConnectedMobile = true;
            }
            return haveConnectedWifi || haveConnectedMobile;
            
        }
        


        ///////////////////////////////////////////for wifi and internet coonection ///////////////////////////////////////////////////////////////////



        public void  parseMovieDetails(final CallBack onCallBack){


            String url = "https://api.json-generator.com/templates/xuiH_2fLmqdv/data?access_token=uzza9l78giktfc72hde2xnjnn85i9qro7hkzv5sp";

            //      String url = "https://api.jsonbin.io/b/61d6790e39a33573b3240e6e";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {

                    try {

                        ArrayList<String> list_service = new ArrayList<>();

                        JSONArray jsonArray = response.getJSONArray("mayur");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject employee = jsonArray.getJSONObject(i);

                            String firstName = employee.getString("videourl");

                            list_service.add(firstName);

                            youtubevideoid.add(firstName);

                        }

                        onCallBack.onSuccess(list_service);


                    } catch (JSONException e) {
                        e.printStackTrace();
                        onCallBack.onFail(e.toString());


                    }


                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    //   mTextViewResult.setText("" + error);
                    //     error.printStackTrace();
                }

            });

            mQueue.add(request);

        }



        public interface CallBack {
            void onSuccess(ArrayList<String> detailsMovies);

            void onFail(String msg);
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        void setVideoData(VideoItem videoItem) {
//            txtTitle.setText(videoItem.videoTitle);
//            txtDesc.setText(videoItem.videoDesc);
//            VideoView.set(videoItem.videoURL);


            //  VideoView = itemView.findViewById(R.id.youtube_player_view);

            VideoView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {

//                @Override
//                public void onPlaybackQualityChange(YouTubePlayer youTubePlayer, PlayerConstants.PlaybackQuality playbackQuality) {
//
//                    playbackQuality = PlayerConstants.PlaybackQuality.SMALL;
//
//                    super.onPlaybackQualityChange(youTubePlayer, playbackQuality);
//                }


//                String ss[] = {"NJFzg620MOE", "ejvgNcxvAfA", "UGmXCHFVVts", "cL7eDKdsxxI", "tUQlas18igU", "qV9WuEk3USs", "EqlLMWvS8Mo", "V0PWAhRnCJo", "LpHmEt48qzM", "khf8nIIxmEo", "9CrfwFaYiyc", "XqQYv4wntts"};


                String videolike[] = {"1.3k","87k","96k","99","999","1k","4.2lakh","60k","12k","42k","8.9k","1.6k","16k","54k","59k","99","999","1k","69k","57k","9.9k","7,3k","19k","37k","91k","23k","12k","76k","3.4lakh","88k","98k","99","999","1k","4.4lakh","1.2lakh","63k","57k","98k","7.9k","22.k","11k","651k","3.1lakh","1.7k","44k","2.2lakh","99","999","1k","98k","61k","89k","34k","9.8lakh","15lakh","17lakh","73k","27k","29k","37k","41k","1k","987","7.6k","3.2k","99","999","1k","9.2k","8.7k","6.5k","783k","5.8k","6.9k","1.3k","87k","96k","4.2lakh","60k","99","999","1k","12k","42k","8.9k","1.6k","16k","54k","59k","69k","57k","9.9k","7,3k","99","999","1k","19k","37k","91k","23k","12k","76k","3.4lakh","88k","98k","4.4lakh","1.2lakh","63k","99","999","1k","57k","98k","7.9k","22.k","11k","651k","3.1lakh","1.7k","44k","2.2lakh","98k","61k","89k","99","999","1k","34k","9.8lakh","15lakh","17lakh","73k","27k","29k","37k","41k","99","999","1k","1k","987","7.6k","3.2k","9.2k","8.7k","6.5k","783k","5.8k","6.9k","1.3k","87k","96k","4.2lakh","60k","12k","42k","8.9k","1.6k","16k","54k","59k","69k","57k","9.9k","7,3k","19k","37k","91k","23k","12k","76k","3.4lakh","88k","98k","4.4lakh","1.2lakh","63k","57k","98k","7.9k","22.k","11k","651k","3.1lakh","1.7k","44k","2.2lakh","98k","61k","89k","34k","9.8lakh","15lakh","17lakh","73k","27k","29k","37k","41k","1k","987","7.6k","3.2k","9.2k","8.7k","6.5k","783k","5.8k","6.9k","1.3k","87k","96k","4.2lakh","60k","12k","42k","8.9k","1.6k","16k","54k","59k","69k","57k","9.9k","7,3k","19k","37k","91k","23k","12k","76k","3.4lakh","88k","98k","4.4lakh","1.2lakh","63k","57k","98k","7.9k","22.k","11k","651k","3.1lakh","1.7k","44k","2.2lakh","98k","61k","89k","34k","9.8lakh","15lakh","17lakh","73k","27k","29k","37k","41k","1k","987","7.6k","3.2k","9.2k","8.7k","6.5k","783k","5.8k","6.9k"};


                int bbbb = 0;

                @Override
                public void onStateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlayerState state) {


                    if (state == PlayerConstants.PlayerState.ENDED) {

                        if (bbbb == 0) {

                            webView.setVisibility(View.VISIBLE);

//                            webView.setImageResource(R.drawable.videonext);

                         //   webView.getSettings().setJavaScriptEnabled(true);
                        //    webView.setWebViewClient(new WebViewClient());

                            // add the url of gif
                       //     webView.loadUrl("https://i.pinimg.com/originals/dd/b1/af/ddb1af9a8c9f6d50205b7acf39515f12.gif");


                            Toast.makeText(itemView.getContext(), "To change video touch on Right side or to pause video touch on left side", Toast.LENGTH_LONG).show();
                        }
                        bbbb++;


          //              Toast.makeText(itemView.getContext(), ""+videolike.length, Toast.LENGTH_LONG).show();

                        textView.setText(videolike[r1%videolike.length]);

                //        youTubePlayer.loadVideo(ss[r1], 0);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        mQueue = Volley.newRequestQueue(itemView.getContext());

                        ArrayList<String> ashok = new ArrayList<String>();

                        parseMovieDetails(new CallBack() {
                            @Override
                            public void onSuccess(ArrayList<String> detailsMovies) {

                                for (int i = 0; i < detailsMovies.size(); i++) {
                                    ashok.add(detailsMovies.get(i));
                                }
                                // Do Stuff

                                youTubePlayer.loadVideo(ashok.get(r1),0);

                       //         Toast.makeText(itemView.getContext(), ""+ashok, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFail(String msg) {
                                // Do Stuff
                            }
                        });



////////////////////////////////////////////////////////////////////////////////////////////////////////

                    }
                }



                @Override
                public void onError(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerError error) {


                    Toast.makeText(itemView.getContext(), "Video Error: Tap on right to change video", Toast.LENGTH_SHORT).show();

//
//                    ArrayList<String> ashok = new ArrayList<String>();
//
//                    parseMovieDetails(new CallBack() {
//                        @Override
//                        public void onSuccess(ArrayList<String> detailsMovies) {
//
//                            for (int i = 0; i < detailsMovies.size(); i++) {
//                                ashok.add(detailsMovies.get(i));
//                            }
//
//
//                     //       youTubePlayer.loadVideo(ashok.get(r1),0);
//
//                        }
//
//                        @Override
//                        public void onFail(String msg) {
//                            // Do Stuff
//                        }
//                    });
//
//
//
//
//                    super.onError(youTubePlayer, error);

                }








                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = videoItem.videoURL;

                    dislike.setColorFilter(itemView.getResources().getColor(R.color.transparent));
                    like.setColorFilter(itemView.getResources().getColor(R.color.transparent));

                    webView.setVisibility(View.VISIBLE);

                    ///////////////////////////////////////////////////////
                    buttonlogiclike = false;
                    buttonlogicdislike = false;
                    biglike.setVisibility(View.GONE);

                    ///////////////////////////////////////////////////////////

                    //                            rand2 = new Random();
//                            r1 = rand2.nextInt(ss.length);



                                                    rand2 = new Random();
                                r1 = rand2.nextInt(ss.length);

                                youTubePlayer.cueVideo(ss[r1],0);

                    //        youTubePlayer.cueVideo(ss[r1], 0);

                    textView.setText(videolike[r1%videolike.length]);


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        mQueue = Volley.newRequestQueue(itemView.getContext());

                        ArrayList<String> ashok = new ArrayList<String>();

                        parseMovieDetails(new CallBack() {
                            @Override
                            public void onSuccess(ArrayList<String> detailsMovies) {
//
//                Toast.makeText(MainActivity.this, ""+detailsMovies.get(2), Toast.LENGTH_SHORT).show();

                                for (int i = 0; i < detailsMovies.size(); i++) {
                                    ashok.add(detailsMovies.get(i));
                                }
                                // Do Stuff
                           //     Toast.makeText(itemView.getContext(), ""+ashok, Toast.LENGTH_SHORT).show();
//
//                                rand2 = new Random();
//                                r1 = rand2.nextInt(ss.length);
//
//                                youTubePlayer.loadVideo(ss[r1],0);

    //                            Toast.makeText(itemView.getContext(), ""+ashok.size(), Toast.LENGTH_SHORT).show();


                            }

                            @Override
                            public void onFail(String msg) {
                                // Do Stuff
                            }
                        });




////////////////////////////////////////////////////////////////////////////////////////////////////////



                    videochanger.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            webView.setVisibility(View.GONE);


                            /////////////////////////////////////////// for checking internet connection //////////////////////////////

                            if(haveNetworkConnection()==false){
                                Toast.makeText(itemView.getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                            }


                            /////////////////////////////////////////// for checking internet connection //////////////////////////////


                            dislike.setColorFilter(itemView.getResources().getColor(R.color.transparent));
                            like.setColorFilter(itemView.getResources().getColor(R.color.transparent));

                            ///////////////////////////////////////////////////////
                            buttonlogiclike = false;
                            buttonlogicdislike = false;

                            ///////////////////////////////////////////////////////////




///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                            mQueue = Volley.newRequestQueue(itemView.getContext());

                            ArrayList<String> ashok = new ArrayList<String>();

                            parseMovieDetails(new CallBack() {
                                @Override
                                public void onSuccess(ArrayList<String> detailsMovies) {
//

                                    for (int i = 0; i < detailsMovies.size(); i++) {
                                        ashok.add(detailsMovies.get(i));
                                    }

                                    rand2 = new Random();
                                    r1 = rand2.nextInt(ashok.size());

                                    youTubePlayer.loadVideo(ashok.get(r1),0);



                                }

                                @Override
                                public void onFail(String msg) {
                                    // Do Stuff
                                }

                            });

                            biglike.setVisibility(View.GONE);

                            textView.setText(videolike[r1%videolike.length]);



//////////////////////////////////////////////////////////////////////////////////////////////////////



                        }
                    });


                }


            });




        }



    }
}