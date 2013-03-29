package org.wikimedia.commons.modifications;

import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class PageModifier {

    public static PageModifier fromJSON(JSONObject data) {
        String name = data.optString("name");
        if(name.equals(CategoryModifier.MODIFIER_NAME)) {
            return new CategoryModifier(data.optJSONObject("data"));
        }
        return null;
    }

    protected String name;
    protected JSONObject params;

    protected PageModifier(String name) {
        this.name = name;
        params = new JSONObject();
    }

    public abstract String doModification(String pageName, String pageContents);

    public JSONObject toJSON() {
        JSONObject data = new JSONObject();
        try {
            data.putOpt("name", name);
            data.put("data", params);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}