package com.udacity.sandwichclub;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.databinding.ActivityDetailBinding;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private Sandwich sandwich;
    private ActivityDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (null == intent) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (null == sandwich) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.ivImage.setScaleType(ImageView.ScaleType.CENTER);

        populateUI();
        Picasso.get()
                .load(sandwich.getImage())
                //as it takes Picasso some time load image, decided to add placeholder
                .placeholder(R.drawable.ic_image_white_24dp)
                //one of the wikipedia image links was broken so had to add state to indicate failure
                .error(R.drawable.ic_broken_image_white_24dp)
                //icons looked weired with ImageView.ScaleType.CENTER_CROP made that only for the loaded images
                .into(mBinding.ivImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        mBinding.ivImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }

                    @Override
                    public void onError(Exception e) {
                    }
                });

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        if (sandwich.getAlsoKnownAs().isEmpty()) {
            mBinding.tvAlsoKnown.setText(R.string.no_other_names);
            mBinding.tvAlsoKnown.setTypeface(null, Typeface.ITALIC);
        } else {
            mBinding.tvAlsoKnown.setText(TextUtils.join(", ", sandwich.getAlsoKnownAs()));
            mBinding.tvAlsoKnown.setTypeface(null, Typeface.NORMAL);
        }
        if (sandwich.getPlaceOfOrigin().isEmpty()) {
            mBinding.tvOrigin.setText(R.string.unknown);
            mBinding.tvOrigin.setTypeface(null, Typeface.ITALIC);
        } else {
            mBinding.tvOrigin.setText(sandwich.getPlaceOfOrigin());
            mBinding.tvOrigin.setTypeface(null, Typeface.NORMAL);
        }
        mBinding.tvDescription.setText(sandwich.getDescription());
        mBinding.tvIngredients.setText(TextUtils.join(", ", sandwich.getIngredients()));
        mBinding.ivImage.setContentDescription(getString(R.string.content_description_picture, sandwich.getMainName()));
    }
}