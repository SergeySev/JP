package org.example.collectionsPractice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Dict {
    Map<String, HashSet<String>> dict = new HashMap<>() {{
        put("gut", new HashSet<>(List.of("good", "fine", "superior")));
        put("Seele", new HashSet<>(List.of("soul", "anima", "mind")));
    }};

    public HashSet<String> get(String key) {
        return dict.get(key);
    }

    public void add(String key, HashSet<String> values) {
        HashSet<String> check;
        if (this.dict.containsKey(key)) {
            check = this.dict.get(key);
            for (String value : values) {
                if (!check.contains(value)) {
                    check.add(value);
                }
            }
        } else this.dict.put(key, values);
    }
    public void remove(String key, String value) {
        if (this.dict.containsKey(key)) {
            this.dict.get(key).remove(value);
        }
    }
}