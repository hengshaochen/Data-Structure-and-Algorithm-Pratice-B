class Solution {
    
    class TempStr
    {
        public String str;
        public int left;
        public int right;
        TempStr(String str, int left, int right)
        {
            this.str = str;
            this.left = left;
            this.right = right;
        }
    }
    
    public List<String> generateParenthesis(int n) 
    {
        List<String> result = new LinkedList<String>();
        if(n <= 0)
            return result;
        Stack<TempStr> s = new Stack<>();
        s.push(new TempStr("",0,0));
        
        // dfs using stack
        while(!s.isEmpty())
        {
            TempStr temp = s.pop();
            if(temp.str.length()==n*2)
            {
                result.add(temp.str);
            }
            else 
            {
                if(temp.left < n)
                {
                    TempStr newTemp = new TempStr(temp.str+"(",temp.left+1,temp.right);
                    s.push(newTemp);
                }
                if(temp.right < n && temp.right < temp.left)
                {
                    TempStr newTemp = new TempStr(temp.str+")", temp.left, temp.right+1);
                    s.push(newTemp);
                }
            }
        }
        return result;
    }
}