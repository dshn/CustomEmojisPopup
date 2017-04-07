package com.customemojispopup;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.vanniktech.emoji.EmojiEditText;
import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.EmojiPopup;
import com.vanniktech.emoji.ios.IosEmojiProvider;
import com.vanniktech.emoji.listeners.OnSoftKeyboardCloseListener;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout rootView;

    private ImageView imgChange;
    private TextView tvShow;
    private EmojiEditText emojisEditTextDialog;
    private EmojiPopup emojiPopup;
    private ImageView imgSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EmojiManager.install(new IosEmojiProvider());
        setContentView(R.layout.activity_main);

        rootView = (RelativeLayout) findViewById(R.id.rel_main);
        imgChange = (ImageView) findViewById(R.id.main_activity_emoji);
        tvShow = (TextView) findViewById(R.id.tv_show);
        emojisEditTextDialog = (EmojiEditText) findViewById(R.id.emojiEditText);
        imgSend = (ImageView) findViewById(R.id.main_activity_send);

        emojiPopup = EmojiPopup.Builder.fromRootView(rootView).setOnSoftKeyboardCloseListener(new OnSoftKeyboardCloseListener() {
            @Override
            public void onKeyboardClose() {
                emojiPopup.dismiss();
            }
        }).build(emojisEditTextDialog);

        imgChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emojiPopup.isShowing()) {
                    imgChange.setImageDrawable(getResources().getDrawable(R.drawable.emoji_ios_category_people));
                } else {
                    imgChange.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard));
                }
                emojiPopup.toggle(); // Toggles visibility of the Popup.
            }
        });

        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvShow.setText("");
                tvShow.setText(emojisEditTextDialog.getText().toString());
            }
        });

    }


}
