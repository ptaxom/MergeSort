public class MyCollection {
    private int[] value = new int[1000];
    private int n;

    MyCollection(){
        n = 0;
    }
    MyCollection(int[] args){
        n = args.length;
        for(int i = 0; i < n; i++)
            value[i] = args[i];

    }

    public void add(int val){
        value[n++] = val;
    }

    public void print(){
        if (this != null) {
            for (int i = 0; i < n; i++)
                System.out.print(value[i] + " ");
            System.out.println();
        }
        else System.out.println("Must be initialized!");
    }

    public int get(int num){
        return value[num];
    }

    public void Copy(MyCollection A){
        this.n = A.n;
        for(int i = 0; i < this.n; i++)
            this.value[i] = A.value[i];
    }




    public static void Split(MyCollection input, MyCollection output1,MyCollection output2){
        if (input.n == 1){
            output1.Copy(input);
            output2.Copy(new MyCollection());
        }
        else{
            int[] a = new int[input.n / 2];
            for(int i = 0; i < input.n / 2; i++)
                a[i] = input.value[i];
            int[] b = new int[input.n-input.n / 2];
            for(int i = input.n / 2 ; i < input.n; i++)
                b[i-input.n / 2 ] = input.value[i];
            output1.Copy(new MyCollection(a));
            output2.Copy(new MyCollection(b));
        }
    }

    public static MyCollection Merge(MyCollection a, MyCollection b){
        MyCollection collection = new MyCollection();
        int k1 = 0, k2 = 0;
        while (k1+k2<a.n+b.n){
            if (k1 == a.n)
                collection.add(b.get(k2++));
            else if (k2 == b.n)
                collection.add(a.get(k1++));
                else if (a.get(k1) < b.get(k2))
                collection.add(a.get(k1++));
                    else collection.add(b.get(k2++));
        }
        return collection;
    }

    public void Sort(){
        if (this.n > 1){
            MyCollection a = new MyCollection();
            MyCollection b = new MyCollection();
            Split(this, a, b);
            a.Sort(); b.Sort();
            this.Copy(Merge(a, b));
        }
    }

}
