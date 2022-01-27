package com.mayurrai.youtubepaisa;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class fakeActivity extends AppCompatActivity {


 //   Uri videouri;
 //   GoogleSignInClient mGoogleSignInClient;

    ViewPager2 mViewPager;

    int SELECT_PICTURE = 200;

   // private static final int PERMISSION_REQUEST_CODE = 7;
    int z = 0;

    ImageView upperimageview,nointernetimage;

    CircularImageView useremailidbtn;

  //  String FolderName = "Manishphoto";

    ImageView uploadvideo;

  //  static private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake);

        mViewPager = findViewById(R.id.viewPagerVideos);
        upperimageview=findViewById(R.id.upparwalaimageview);

        nointernetimage = findViewById(R.id.nointernetimage);


        Intent intent = getIntent();

        String str = intent.getStringExtra("message_key");

        uploadvideo = findViewById(R.id.uploadvideo);




        /////////////////////////////////////////// for checking internet connection //////////////////////////////

        if(haveNetworkConnection()==false){
            Toast.makeText(fakeActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
          //  nointernetimage.setVisibility(View.VISIBLE);

        }


        if(!isNetworkAvailable()==true)
        {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Internet Connection Alert")
                    .setMessage("Please Check Your Internet Connection")
                    .setPositiveButton("       Exit         ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }).setNegativeButton("Check                   ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Intent i11=new Intent(fakeActivity.this,fakeActivity.class);
                    startActivity(i11);

                }
            }).show();



        }



        /////////////////////////////////////////// for checking internet connection //////////////////////////////



        upperimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         //       Toast.makeText(fakeActivity.this, "Toast kuch bhi nhi kiya", Toast.LENGTH_SHORT).show();
            }
        });


        //////////////////////////////////////// for uploading video ///////////////////////////////////////


        uploadvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageChooser();

            }
        });


        //////////////////////////////////////// for uploading video ///////////////////////////////////////



        ////////////////////////////// for user email id floating button wali se ////////////////////


        ///////////////////////////////// for set image on floating  button ////////////////////////////////////////////


        boolean mama= true;

        useremailidbtn = findViewById(R.id.useremailid1);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if (acct != null) {

            String personName = acct.getDisplayName();
            Uri personPhoto = acct.getPhotoUrl();

            if (personPhoto != null) {

                Glide.with(this).load(String.valueOf(personPhoto)).into(useremailidbtn);

//                useremailidbtn.setImageURI(personPhoto);


           //     Toast.makeText(fakeActivity.this, "User image set", Toast.LENGTH_SHORT).show();
            }


            else{
                Glide.with(this).load(String.valueOf("https://i2.wp.com/www.cssscript.com/wp-content/uploads/2020/12/Customizable-SVG-Avatar-Generator-In-JavaScript-Avataaars.js.png?fit=438%2C408&ssl=1")).into(useremailidbtn);

            }


        }

        else{
            mama = false;
        }


        if(mama==false){
            Intent intent7 = new Intent(fakeActivity.this,SigningActivity.class);
        }


        ///////////////////////////////// for set image on floating  button ////////////////////////////////////////////


        useremailidbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fakeActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });


        ////////////////////////////// for user email id floating button wali se ////////////////////


        ////////////////////////////////for bottom navigattion  //////////////////////////////////////////////////////

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);




        //////////////////////////////////bottom baar  finish //////////////////////////////////////////////////

        final ViewPager2 videosViewPager = findViewById(R.id.viewPagerVideos);

        List<VideoItem> videoItems = new ArrayList<>();




        ////////////////////////////////// giving right layout position /////////////////////////////////////////////


        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {

                super.onPageSelected(position);

            }


            @Override
            public void onPageScrollStateChanged(int state) {


                super.onPageScrollStateChanged(state);
            }
        });


        /////////////////////////////////////////// giving right layout position /////////////////////////////////////////////////////////


        VideoItem item1 = new VideoItem();
        item1.videoURL = "6rdt4LBYd8k";
        item1.id = 1;
        videoItems.add(item1);


        videosViewPager.setAdapter(new VideosAdapter(videoItems));




//    public void download(View view)
//    {
//        new YouTubeExtractor(this) {
//            @Override
//            public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
//                if (ytFiles != null) {
//                    int itag = 22;
//                    String downloadUrl = ytFiles.get(itag).getUrl();
//                    DownloadManager downloadmanager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
//                    request.setTitle("My File");
//                    request.setDescription("Downloading");
//                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                    request.setVisibleInDownloadsUi(false);
//                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"downloads");
//                    downloadmanager.enqueue(request);
//                }
//            }
//        }.extract(link, true, true);
//    }




    }




    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("video/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();

                if (null != selectedImageUri) {

                    // update the preview image in the layout
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("video/*");

                    sharingIntent.setPackage("com.google.android.youtube");
                    sharingIntent.putExtra(Intent.EXTRA_TITLE, "Title");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Desc");

                    sharingIntent.putExtra(android.content.Intent.EXTRA_STREAM, selectedImageUri);
                    startActivity(Intent.createChooser(sharingIntent, "Upload:"));



                }
            }
        }
    }







    //////////////////////////////////////////for wifi and internet connection /////////////////////////////////////////////////////////////////////////


    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;


        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);


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


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {


            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {

                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {

                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {

                        return true;
                    }
                }
            }
        }

        return false;

    }


    //////////////////////////////////////////for wifi and internet connection /////////////////////////////////////////////////////////////////////////


}