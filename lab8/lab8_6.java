package lab8;

import java.io.*;
import java.util.*;
public class lab8_6 {
    public static void main(String[] args)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        ArrayList<int[]> linesRaw=new ArrayList<>();
        ArrayList<ArrayList<Integer>> stationToLines=new ArrayList<>();
        for(int i=0;i<=N;i++)stationToLines.add(new ArrayList<>());
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int S=Integer.parseInt(st.nextToken());
            int[] arr=new int[S];
            for(int k=0;k<S;k++){
                arr[k]=Integer.parseInt(st.nextToken());
                stationToLines.get(arr[k]).add(i);
            }
            linesRaw.add(arr);
        }
        int[][] dist=buildLineGraphAndAllPairs(M,stationToLines);
        int Q=Integer.parseInt(br.readLine().trim());
        StringBuilder out=new StringBuilder();
        for(int qi=0;qi<Q;qi++){
            st=new StringTokenizer(br.readLine());
            int A=Integer.parseInt(st.nextToken());
            int B=Integer.parseInt(st.nextToken());
            if(A==B){out.append(0).append('\n');continue;}
            ArrayList<Integer> LA=stationToLines.get(A);
            ArrayList<Integer> LB=stationToLines.get(B);
            if(LA.isEmpty()||LB.isEmpty()){out.append("impossible\n");continue;}
            int best=Integer.MAX_VALUE;
            for(int a:LA)for(int b:LB)if(dist[a][b]<best)best=dist[a][b];
            if(best==1_000_000_000)out.append("impossible\n");else out.append(best).append('\n');
        }
        System.out.print(out.toString());
    }
    static int[][] buildLineGraphAndAllPairs(int M,ArrayList<ArrayList<Integer>> stationToLines){
        boolean[][] adj=new boolean[M][M];
        for(int s=1;s<stationToLines.size();s++){
            ArrayList<Integer> L=stationToLines.get(s);
            for(int i=0;i<L.size();i++){
                for(int j=i+1;j<L.size();j++){
                    int a=L.get(i),b=L.get(j);
                    adj[a][b]=true;adj[b][a]=true;
                }
            }
        }
        int INF=1_000_000_000;
        int[][] dist=new int[M][M];
        for(int i=0;i<M;i++){
            Arrays.fill(dist[i],INF);
            dist[i][i]=0;
        }
        for(int i=0;i<M;i++){
            ArrayDeque<Integer> q=new ArrayDeque<>();
            q.add(i);
            boolean[] vis=new boolean[M];
            vis[i]=true;
            while(!q.isEmpty()){
                int u=q.poll();
                for(int v=0;v<M;v++){
                    if(adj[u][v]&&!vis[v]){
                        dist[i][v]=dist[i][u]+1;
                        vis[v]=true;
                        q.add(v);
                    }
                }
            }
        }
        return dist;
    }
}
