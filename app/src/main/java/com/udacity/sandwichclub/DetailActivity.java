package com.udacity.sandwichclub;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private Sandwich sandwich;

    private TextView alias;
    private TextView origin;
    private TextView description;
    private TextView ingredients;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) {
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
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        final ImageView ingredientsIv = findViewById(R.id.image_iv);
        ingredientsIv.setScaleType(ImageView.ScaleType.CENTER);
        alias = findViewById(R.id.also_known_tv);
        origin = findViewById(R.id.origin_tv);
        description = findViewById(R.id.description_tv);
        ingredients = findViewById(R.id.ingredients_tv);

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                //as it takes Picasso some time load image, decided to add placeholder
                .placeholder(R.drawable.ic_image_white_24dp)
                //one of the wikipedia image links was broken so had to add state to indicate failure
                .error(R.drawable.ic_broken_image_white_24dp)
                //icons looked weired with ImageView.ScaleType.CENTER_CROP made that only for the loaded images
                .into(ingredientsIv, new Callback() {
                    @Override
                    public void onSuccess() {
                        ingredientsIv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }

                    @Override
                    public void onError() {
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
            alias.setText(R.string.no_other_names);
            alias.setTypeface(null, Typeface.ITALIC);
        } else {
            alias.setText(TextUtils.join(", ", sandwich.getAlsoKnownAs()));
            alias.setTypeface(null, Typeface.NORMAL);
        }
        if (sandwich.getPlaceOfOrigin().isEmpty()) {
            origin.setText(R.string.unknown);
            origin.setTypeface(null, Typeface.ITALIC);
        } else {
            origin.setText(sandwich.getPlaceOfOrigin());
            origin.setTypeface(null, Typeface.NORMAL);
        }
        description.setText(sandwich.getDescription());
        ingredients.setText(TextUtils.join(", ", sandwich.getIngredients()));
    }
}
