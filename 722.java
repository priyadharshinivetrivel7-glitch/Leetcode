import java.util.*;
class Solution {
    public List<String> removeComments(String[] source) {
        ArrayList<String> a = new ArrayList<>();
        StringBuffer b = new StringBuffer();
        boolean c = false;
        for(String s:source){
            int i = 0;
            while(i<s.length()){
                if(c==true){
                    if(i+1<s.length() && s.charAt(i)=='*' && s.charAt(i+1)=='/'){
                        c=false;
                        i+=2;
                    }
                    else{
                        i++;
                    }
                }
                else if(i+1<s.length() && s.charAt(i)=='/' && s.charAt(i+1)=='/'){
                    break;
                } else if (i + 1 < s.length()
                    && s.charAt(i) == '/'
                    && s.charAt(i + 1) == '*') {
                    c = true;
                    i += 2;
                }
                else{
                    b.append(s.charAt(i));
                    i++;
                }
            }
            if(c==false){
                if(b.length()>0){
                    a.add(b.toString());
                }
                b.setLength(0);
            }
        }
        return a;
    }
}
