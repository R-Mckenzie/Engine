package MEngine.Physics;

import MEngine.Maths.Vec3;

import java.util.ArrayList;
import java.util.List;

public class AABBTree{
    static float fatFactor=1.05f;
    List<Node> tree;
    Node root;

    public AABBTree(){
        tree=new ArrayList<>();
        root=new Node();
    }

    public void insert(Node root, AABB aabb){
        Node insertNode=new Node();
        insertNode.leafData=aabb;

        if(root.left==null){
           //add on left without bounding box
            insertNode.parent=root;
            root.left=insertNode;
        }else if(root.right==null){
            //create a new branch to contain left and right aabbs. Then add the new node
            Node branch=new Node();
            branch.fatAABB=createBoundingAABB(root.left.leafData, insertNode.leafData);
            //Reassign root.left to the new branch and add the insertAABB
            branch.left=root.left;
            branch.left.parent=branch;
            branch.right=insertNode;
            root.left=branch;
        }
    }

    public void remove(AABB aabb){

    }

    public List<AABB> getCollsions(){
        List<AABB> collisions=new ArrayList<>();
        return collisions;
    }

    private AABB createBoundingAABB(AABB a, AABB b){
        AABB left, right;
        if(a.position.x<b.position.x){
            left=a; right=b;
        }else{
            left=b; right=a;
        }

        AABB top, bottom;
        if(a.position.y<b.position.y){
            top=a; bottom=b;
        }else{
            top=b; bottom=a;
        }

        Vec3 pos=new Vec3(left.position.x, top.position.y, 0);
        int width=(int)((right.position.x-left.position.x)+right.width);
        int height=(int)((bottom.position.y-top.position.y)+bottom.height);

        return new AABB(pos,width,height);
    }

    private class Node{
        Node parent;
        AABB fatAABB;
        Node left;
        Node right;
        AABB leafData;

        protected Node(){

        }

        boolean isLeaf(){
            return leafData!=null;
        }
    }
}
