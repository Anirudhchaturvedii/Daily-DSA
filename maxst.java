//this code consist both type of queries for range query and update query
//range query is to find the sum of elements in a given range
//update query is to update the value of an element at a given index
//this code is for maximum segment tree
public class maxst {
    static int tree[];
    public static void init(int n){
        tree=new int[4*n];
    }
    public static void buildST(int arr[],int i,int st,int ed){
        if(st==ed){
            tree[i]=arr[st];
            return ;
        }
        int mid=(st+ed)/2;
        buildST(arr,2*i+1, st, mid);
        buildST(arr, 2*i+2, mid+1, ed);
        tree[i]=Math.max(tree[2*i+1], tree[2*i+2]);
    }
    public static int getMax(int arr[], int l, int r) {
        return query(0, 0, arr.length - 1, l, r);
    }
    public static int query(int i, int st, int ed, int l, int r) {
        if (l > ed || r < st) {
            return Integer.MIN_VALUE; // No overlap
        }
        if (l <= st && r >= ed) {
            return tree[i]; // Total overlap
        }
        int mid = (st + ed) / 2;
        return Math.max(query(2 * i + 1, st, mid, l, r), 
                       query(2 * i + 2, mid + 1, ed, l, r)); // Partial overlap
    }public static void updateUtil(int i, int st, int ed, int idx, int newval) {
        if (idx < st || idx > ed) {
            return; // Index out of range
        }
        tree[i] = Math.max(tree[i], newval); // Update the current node
        if (st != ed) { // If not a leaf node
            int mid = (st + ed) / 2;
            updateUtil(2 * i + 1, st, mid, idx, newval); // Update left child
            updateUtil(2 * i + 2, mid + 1, ed, idx, newval); // Update right child
        }
    }
    public static void update(int arr[], int idx, int newval) {
        arr[idx] = newval;
        updateUtil(0, 0, arr.length - 1, idx, newval);
    }
    public static void main(String[] args) {
        int arr[]={6,8,-1,2,17,1,3,2,4};
        int n=arr.length;
        init(n);
        buildST(arr, 0, 0, n-1);
        for(int i=0;i<tree.length;i++){
            System.out.print(tree[i]+" "); 
        }
        System.out.println();
        int max=getMax(arr, 2, 5);
        System.out.println(max + " is the max from index 2 to 5");
        update(arr, 2, 100);
        System.out.println(getMax(arr, 2, 5) + " is the max from index 2 to 5 after update");
    }
}

