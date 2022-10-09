package it.femco.cli2api;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * Implements a sort of read-only Map.
 * @author Molok
 * @version 03/10/22 13:27
 */
public class Output{

    private final StringBuffer raw;
    private final Map<String, Object> results;

    public Output(StringBuffer rawOutput, Map<String, Object> mappedResults) {
        this.raw = rawOutput;
        this.results = mappedResults;
    }
    public Output(StringBuffer rawOutput) {
        this(rawOutput, null);
    }
    String GetRaw() {
        return this.raw.toString();
    };

    public int size() {
        return results!=null?results.size():0;
    }

    public boolean isEmpty() {
        return results!=null?results.isEmpty():true;
    }
    
    public boolean containsKey(Object o) {
        return results!=null?results.containsKey(o):false;
    }
    
    public Set keySet() {
        return results!=null?results.keySet():Collections.emptySet();
    }

    public Collection values() {
        return results!=null?results.values():Collections.emptyList();
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return results!=null?results.entrySet():null;
    }

    public Object getOrDefault(Object key, Object defaultValue) {
        return results!=null?results.getOrDefault(key, defaultValue):defaultValue;
    }

    public void forEach(BiConsumer action) {
        if (results==null) return;
        results.forEach(action);
    }
}
