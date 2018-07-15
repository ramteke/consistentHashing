package consistentHashing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by skynet on 23/06/18.
 */
public class Client {

    public static void main(String args[]) {

        HashFunction hashFunction = new HashFunction();

        List<Node> ringNodes = new LinkedList<Node>();
        ringNodes.add(new Node("127.0.0.1", hashFunction));
        ringNodes.add(new Node("127.0.0.2", hashFunction));
        ringNodes.add(new Node("127.0.0.3", hashFunction));
        ringNodes.add(new Node("127.0.0.4", hashFunction));
        ringNodes.add(new Node("127.0.0.5", hashFunction));

        List<String> requestIPs = new ArrayList<String>();
        requestIPs.add("192.168.0.1");
        requestIPs.add("192.168.0.2");
        requestIPs.add("192.168.0.3");
        requestIPs.add("192.168.0.4");
        requestIPs.add("192.168.0.5");

        ConsistentHashCode consistentHash = new ConsistentHashCode(ringNodes, hashFunction);

        for ( String ip : requestIPs ) {
            Node node = consistentHash.assignNode(ip,hashFunction);
            System.out.println(ip + " => " + node.getNodeName());
        }
        System.out.println("--------------------------------------------------");

        System.out.println("Deleting Node: " + ringNodes.get(2).getNodeName());
        consistentHash.removeNode(ringNodes.get(2));
        System.out.println("--------------------------------------------------");

        for ( String ip : requestIPs ) {
            Node node = consistentHash.assignNode(ip,hashFunction);
            System.out.println(ip + " => " + node.getNodeName());
        }


    }
}
