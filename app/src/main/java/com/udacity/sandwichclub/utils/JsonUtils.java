package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String SJ_NAME_NODE = "name";
    private static final String SJ_NAME = "mainName";
    private static final String SJ_ALIAS = "alsoKnownAs";
    private static final String SJ_ORIGIN = "placeOfOrigin";
    private static final String SJ_DESCRIPTION = "description";
    private static final String SJ_IMAGE = "image";
    private static final String SJ_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            if (!jsonObject.has(SJ_NAME_NODE)
                    || !jsonObject.getJSONObject(SJ_NAME_NODE).has(SJ_NAME)
                    || !jsonObject.has(SJ_DESCRIPTION)
                    || !jsonObject.has(SJ_IMAGE)
                    || !jsonObject.has(SJ_INGREDIENTS)) {
                return null;
            }
            JSONObject nameNode = jsonObject.getJSONObject(SJ_NAME_NODE);
            String name = nameNode.optString(SJ_NAME);
            List<String> alias = getStringArray(nameNode.getJSONArray(SJ_ALIAS));
            String origin = jsonObject.optString(SJ_ORIGIN);
            String description = jsonObject.optString(SJ_DESCRIPTION);
            String image = jsonObject.optString(SJ_IMAGE);
            List<String> ingredients = getStringArray(jsonObject.getJSONArray(SJ_INGREDIENTS));
            return new Sandwich(name, alias, origin, description, image, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<String> getStringArray(JSONArray jsonArray) {
        List<String> stringList = new ArrayList<>();
        if (jsonArray != null) {
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                stringList.add(jsonArray.optString(i));
            }
        }
        return stringList;
    }
}