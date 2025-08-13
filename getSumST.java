// This code implements a Segment Tree to efficiently calculate the sum of elements in a range
// and to update the value of an element at a given index.
public class getSumST {
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
        tree[i]=tree[2*i+1] + tree[2*i+2];
    }
    public static int getSum(int arr[], int l, int r) {
        return query(0, 0, arr.length - 1, l, r);
    }
    public static int query(int i, int st, int ed, int l, int r) {
        if (l >=ed || r <= st) {
            return 0; // No overlap
        }
        if (l <= st && r >= ed) {
            return tree[i]; // Total overlap
        }
        int mid = (st + ed) / 2;
        return query(2 * i + 1, st, mid, l, r) + 
               query(2 * i + 2, mid + 1, ed, l, r); // Partial overlap
    }
    public static void updateUtil(int i, int st, int ed, int idx, int newval) {
        if (idx < st || idx > ed) {
            return; // Index out of range
        }
        tree[i] = tree[i] - (ed - st + 1) * (newval - tree[i]); // Update the current node
        if (st != ed) { // If not a leaf node
            int mid = (st + ed) / 2;
            updateUtil(2 * i + 1, st, mid, idx, newval); // Update left child
            updateUtil(2 * i + 2, mid + 1, ed, idx, newval); // Update right child
        }
    }
    public static void update(int arr[], int idx, int newval) {
        int oldval = arr[idx];
        arr[idx] = newval;
        updateUtil(0, 0, arr.length - 1, idx, newval);
    }
    public static void main(String[] args) {
        int arr[]={1,2,3,4,5,6,7,8};
        int n=arr.length;
        init(n);
        buildST(arr, 0, 0, n-1);
        for(int i=0;i<tree.length;i++){
            System.out.print(tree[i]+" "); 
        }
        System.out.println();
        int sum=getSum(arr, 2, 5);
        System.out.println(sum + " is the sum from index 2 to 5");
        update(arr, 2, 10);
        System.out.println(getSum(arr, 2, 5) + " is the sum from index 2 to 5 after update");
    }
}
