package lab9;

import java.io.*;
import java.util.*;

public class lab9_9 {
    static class Node {
        int idx;
        long energy;
        Node prev,next;
        boolean alive=true;
        Node(int idx,long energy){this.idx=idx;this.energy=energy;}
    }
    static class Pair {
        Node left,right;
        long diff;
        int leftIdx;
        long uid;
        Pair(Node l, Node r, long uid){
            left=l; right=r;
            diff=Math.abs(l.energy-r.energy);
            leftIdx=l.idx;
            this.uid=uid;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine().trim());
        Node head=null, prev=null;
        Node[] nodes=new Node[N];
        for(int i=0;i<N;i++){
            long x=Long.parseLong(br.readLine().trim());
            Node cur=new Node(i+1,x);
            nodes[i]=cur;
            if(head==null) head=cur;
            if(prev!=null){ prev.next=cur; cur.prev=prev; }
            prev=cur;
        }
        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->{
            if(a.diff!=b.diff) return Long.compare(b.diff,a.diff);
            if(a.leftIdx!=b.leftIdx) return Integer.compare(a.leftIdx,b.leftIdx);
            return Long.compare(a.uid,b.uid);
        });
        long uid=0;
        for(Node p=head; p!=null && p.next!=null; p=p.next){
            pq.add(new Pair(p,p.next,uid++));
        }
        long total=0;
        while(!pq.isEmpty()){
            Pair top=pq.poll();
            Node L=top.left, R=top.right;
            if(!L.alive || !R.alive || L.next!=R) continue;
            total+=top.diff;
            L.alive=false; R.alive=false;
            Node A=L.prev, B=R.next;
            if(A!=null) A.next=B;
            else head=B;
            if(B!=null) B.prev=A;
            if(A!=null && B!=null) pq.add(new Pair(A,B,uid++));
        }
        System.out.println(total);
    }
}

