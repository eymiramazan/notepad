package stockTheme;

import java.util.HashSet;
import java.util.Iterator;

// Iterator Pattern

public class HashSetIterator implements Iterator {

    // Hashset reference
    HashSet<String> hs;
    // Index
    int current_index = 0;

    public HashSetIterator(HashSet<String> hs) {
        this.hs = hs;
    }

    @Override
    public boolean hasNext() {
        return (current_index<hs.size()?true:false);
    }

    @Override
    public Object next() {
        Object[] temp = hs.toArray();
        return temp[current_index++];
    }
}
