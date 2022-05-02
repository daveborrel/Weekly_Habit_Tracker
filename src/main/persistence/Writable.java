package persistence;

import org.json.JSONObject;

/**
 * Template taken from JsonSerializationDemo
 */
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
