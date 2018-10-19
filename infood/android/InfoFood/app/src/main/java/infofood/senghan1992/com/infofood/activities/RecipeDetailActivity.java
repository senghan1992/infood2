package infofood.senghan1992.com.infofood.activities;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import infofood.senghan1992.com.infofood.ServerInfo.ServerInfo;

public class RecipeDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("title");
        String content1 = bundle.getString("content1");
        String content2 = bundle.getString("content2");
        String content3 = bundle.getString("content3");
        String content4 = bundle.getString("content4");
        String content5 = bundle.getString("content5");
        //이미지
        String content_image1 = bundle.getString("content_image1");
        content_image1 = content_image1.replace("\"","");
        String content_image2 = bundle.getString("content_image2");
        content_image2 = content_image2.replace("\"","");
        String content_image3 = bundle.getString("content_image3");
        content_image3 = content_image3.replace("\"","");
        String content_image4 = bundle.getString("content_image4");
        content_image4 = content_image4.replace("\"","");
        String content_image5 = bundle.getString("content_image5");
        content_image5 = content_image5.replace("\"","");

        String regidate = bundle.getString("regidate");

        //동적으로 레이아웃 추가
        ScrollView scrollView = new ScrollView(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        TextView textView_intro = new TextView(this);
        textView_intro.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        textView_intro.setText("InFood");
        textView_intro.setTextColor(Color.WHITE);
        textView_intro.setBackgroundColor(Color.parseColor("#ffa200"));
        textView_intro.setHeight(150);
        textView_intro.setTextSize(30);
        textView_intro.setTypeface(Typeface.DEFAULT_BOLD);
        textView_intro.setGravity(Gravity.CENTER);

        TextView textView_title = new TextView(this);
        textView_title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView_title.setText(title);
        textView_title.setGravity(View.TEXT_ALIGNMENT_CENTER);
        textView_title.setTextSize(25);
        textView_title.setGravity(Gravity.CENTER);

        ImageView imageView_content_image1 = new ImageView(this);
        imageView_content_image1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        Glide.with(this).load(ServerInfo.SERVER_IP_PHOTO+"tip/"+content_image1).into(imageView_content_image1);

        TextView textView_content1 = new TextView(this);
        textView_content1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView_content1.setText(content1);
        textView_content1.setTextSize(20);

        layout.addView(textView_intro);
        layout.addView(textView_title);
        layout.addView(imageView_content_image1);
        layout.addView(textView_content1);

        if (!content2.contains("none")){
            ImageView imageView_content_image2 = new ImageView(this);
            imageView_content_image2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            Glide.with(this).load(ServerInfo.SERVER_IP_PHOTO+"tip/"+content_image2).into(imageView_content_image2);

            Log.d("Recipe_detail_image", ServerInfo.SERVER_IP_PHOTO+"tip/"+content_image2);

            TextView textView_content2 = new TextView(this);
            textView_content2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView_content2.setText(content2);
            textView_content2.setTextSize(20);

            layout.addView(imageView_content_image2);
            layout.addView(textView_content2);
        }
        if (!content3.contains("none")){
            ImageView imageView_content_image3 = new ImageView(this);
            imageView_content_image3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            Glide.with(this).load(ServerInfo.SERVER_IP_PHOTO+"tip/"+content_image3).into(imageView_content_image3);

            TextView textView_content3 = new TextView(this);
            textView_content3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView_content3.setText(content3);
            textView_content3.setTextSize(20);

            layout.addView(imageView_content_image3);
            layout.addView(textView_content3);
        }
        if (!content4.contains("none")){
            ImageView imageView_content_image4 = new ImageView(this);
            imageView_content_image4.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            Glide.with(this).load(ServerInfo.SERVER_IP_PHOTO+"tip/"+content_image4).into(imageView_content_image4);

            TextView textView_content4 = new TextView(this);
            textView_content4.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView_content4.setText(content4);
            textView_content4.setTextSize(20);

            layout.addView(imageView_content_image4);
            layout.addView(textView_content4);
        }
        if (!content5.contains("none")){
            ImageView imageView_content_image5 = new ImageView(this);
            imageView_content_image5.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            Glide.with(this).load(ServerInfo.SERVER_IP_PHOTO+"tip/"+content_image5).into(imageView_content_image5);

            TextView textView_content5 = new TextView(this);
            textView_content5.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView_content5.setText(content5);
            textView_content5.setTextSize(20);

            layout.addView(imageView_content_image5);
            layout.addView(textView_content5);
        }


        scrollView.addView(layout);

        setContentView(scrollView);
    }
}
