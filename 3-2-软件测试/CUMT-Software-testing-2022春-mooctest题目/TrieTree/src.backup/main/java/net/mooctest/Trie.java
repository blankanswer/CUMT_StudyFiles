package net.mooctest;
public class Trie
{
    private int SIZE = 26;
    private TrieNode root;// å­å¸æ çæ ?

    class TrieNode // å­å¸æ èç?
    {
        private int num;// æå¤å°åè¯é?è¿è¿ä¸ªèç¹,å³ç±æ ¹è³è¯¥èç¹ç»æçå­ç¬¦ä¸²æ¨¡å¼åºç°çæ¬¡æ°
        private TrieNode[] son;// æ?æçå¿å­èç¹
        private boolean isEnd;// æ¯ä¸æ¯æåä¸ä¸ªèç?
        private char val;// èç¹çå??

        TrieNode()
        {
            num = 1;
            son = new TrieNode[SIZE];
            isEnd = false;
        }
    }
    Trie() // åå§åå­å¸æ 
    {
        root = new TrieNode();
    }
    

    // å»ºç«å­å¸æ ?
    public void insert(String str) // å¨å­å¸æ ä¸­æå¥ä¸ä¸ªåè¯?
    {
        if (str == null || str.length() == 0)
        {
            return;
        }
        TrieNode node = root;
        char[] letters = str.toCharArray();//å°ç®æ åè¯è½¬æ¢ä¸ºå­ç¬¦æ°ç»
        for (int i = 0, len = str.length(); i < len; i++)
        {
            int pos = letters[i] - 'a';
            if (node.son[pos] == null)  //å¦æå½åèç¹çå¿å­èç¹ä¸­æ²¡æè¯¥å­ç¬¦ï¼åæå»ºä¸ä¸ªTrieNodeå¹¶å¤å¼è¯¥å­ç¬¦
            {
                node.son[pos] = new TrieNode();
                node.son[pos].val = letters[i];
            } 
            else   //å¦æå·²ç»å­å¨ï¼åå°ç±æ ¹è³è¯¥å¿å­èç¹ç»æçå­ç¬¦ä¸²æ¨¡å¼åºç°çæ¬¡æ°+1
            {
                node.son[pos].num++;
            }
            node = node.son[pos];
        }
        node.isEnd = true;
    }

    // è®¡ç®åè¯åç¼çæ°é?
    public int countPrefix(String prefix)
    {
        if(prefix==null||prefix.length()==0)
        {
            return-1;
        }
        TrieNode node=root;
        char[]letters=prefix.toCharArray();
        for(int i=0,len=prefix.length(); i<len; i++)
        {
            int pos=letters[i]-'a';
            if(node.son[pos]==null)
            {
                return 0;
            }
            else
            {
                node=node.son[pos];
            }
        }
        return node.num;
    }

    // æå°æå®åç¼çåè¯?
    public String hasPrefix(String prefix)
    {
        if (prefix == null || prefix.length() == 0)
        {
            return null;
        }
        TrieNode node = root;
        char[] letters = prefix.toCharArray();
        for (int i = 0, len = prefix.length(); i < len; i++)
        {
            int pos = letters[i] - 'a';
            if (node.son[pos] == null)
            {
                return null;
            }
            else
            {
                node = node.son[pos];
            }
        }
        preTraverse(node, prefix);
        return null;
    }

    // éåç»è¿æ­¤èç¹çåè¯.
    public void preTraverse(TrieNode node, String prefix)
    {
        if (!node.isEnd)
        {
            for (TrieNode child : node.son)
            {
                if (child != null)
                {
                    preTraverse(child, prefix + child.val);
                }
            }
            return;
        }
        System.out.println(prefix);
    }

    // å¨å­å¸æ ä¸­æ¥æ¾ä¸ä¸ªå®å¨å¹éçåè¯.
    public boolean has(String str)
    {
        if(str==null||str.length()==0)
        {
            return false;
        }
        TrieNode node=root;
        char[]letters=str.toCharArray();
        for(int i=0,len=str.length(); i<len; i++)
        {
            int pos=letters[i]-'a';
            if(node.son[pos]!=null)
            {
                node=node.son[pos];
            }
            else
            {
                return false;
            }
        }
        //èµ°å°è¿ä¸æ­¥ï¼è¡¨æå¯è½å®å¨å¹éï¼å¯è½é¨åå¹éï¼å¦ææ?åä¸ä¸ªå­ç¬¦èç¹ä¸ºæ«ç«¯èç¹ï¼åæ¯å®å¨å¹éï¼å¦åæ¯é¨åå¹é?
        return node.isEnd;
    }

    // ååºéåå­å¸æ ?.
    public void preTraverse(TrieNode node)
    {
        if(node!=null)
        {
            System.out.print(node.val+"-");
            for(TrieNode child:node.son)
            {
                preTraverse(child);
            }
        }
    }

    public TrieNode getRoot()
    {
        return this.root;
    }
    
}
