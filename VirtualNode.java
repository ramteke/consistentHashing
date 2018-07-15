package consistentHashing;

/**
 * Created by skynet on 23/06/18.
 */
public class VirtualNode {
    private Node parentNode = null;
    private Long virtualNodeHash = null;

    public Node getParentNode() {
        return parentNode;
    }


    public Long getVirtualNodeHash() {
        return virtualNodeHash;
    }

    public VirtualNode(Node parentNode, Long virtualNodeHash) {
        this.parentNode = parentNode;
        this.virtualNodeHash = virtualNodeHash;
    }

    public String toString() {
        return parentNode.getNodeName() + "-" + virtualNodeHash;
    }

}
