package consistentHashing;

import java.io.FileInputStream;
import java.security.MessageDigest;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by skynet on 23/06/18.
 */
public class Node {

    private Long nodeKey = null;
    private String nodeName = null;

    public Node(String nodeName, HashFunction hashFunction) {
        this.nodeName = nodeName;
        this.nodeKey = hashFunction.getHash(nodeName);
    }


    public String toString() {
        return nodeName;
    }


    public Long getNodeKey() {
        return nodeKey;
    }

    public String getNodeName() {
        return nodeName;
    }
}
