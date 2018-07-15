package consistentHashing;

import java.util.*;

/**
 * Created by skynet on 23/06/18.
 */
public class ConsistentHashCode {

    SortedMap<Long, VirtualNode> nodeMap = new TreeMap<Long, VirtualNode>();

    public ConsistentHashCode(List<Node> nodeList, HashFunction hashFunction) {
        for (Node node : nodeList) {
            addNode(node,hashFunction);
        }
    }

    void addNode(Node node,HashFunction hashFunction) {
        for (int i = 0; i < 10; i++) {
            Long virtualHash = hashFunction.getHash(node.getNodeName() + "-" + i);
            VirtualNode virtualNode  = new VirtualNode(node, virtualHash);
            nodeMap.put(virtualHash, virtualNode);
        }
    }



    public Node assignNode(String inputKey, HashFunction hashFunction) {
        if (nodeMap.isEmpty()) {
            return null;
        }

        Long hashVal = hashFunction.getHash(inputKey);

        SortedMap<Long, VirtualNode> tailMap = nodeMap.tailMap(hashVal);

        Long selectedVirtualNodeHash = 0L;
        if (tailMap.isEmpty()) {
            selectedVirtualNodeHash = nodeMap.firstKey();  //take 1st key from ring
        } else {
            selectedVirtualNodeHash = tailMap.firstKey();  //take 1st key from selected subRing
        }

        return nodeMap.get(selectedVirtualNodeHash).getParentNode();

    }

    public void removeNode(Node node) {
        if (nodeMap.isEmpty()) {
            return;
        }


        List<Long> nodeHash2Delete = new ArrayList<Long>();
        for ( Long nodeHash : nodeMap.keySet()) {
            if ( nodeMap.get(nodeHash).getParentNode().equals(node)) {
                nodeHash2Delete.add(nodeHash);
            }
        }

        for ( Long hash : nodeHash2Delete ) {
            nodeMap.remove(hash);
        }

    }


}
