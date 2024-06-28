package zero.dividend;

import org.apache.commons.collections4.Trie;

public class AutoComplete {

    private final Trie<String, String> trie;

    public AutoComplete(Trie<String, String> trie) {
        this.trie = trie;
    }

    public void add(String s) {
        this.trie.put(s, "world");
    }

    public Object get(String s) {
        return this.trie.get(s);
    }
}
