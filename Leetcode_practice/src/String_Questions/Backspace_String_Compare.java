package String_Questions;

public class Backspace_String_Compare {
    public boolean backspaceCompare_BruteForce(String s, String t) {
        if(s.length() == 0 && t.length() == 0){
            return true;
        }
        String subS = "";
        String subT = "";
        for(int i=0; i<s.length(); i++){
            if(s.substring(i, i+1).equals("#")){
                if(subS.length() != 0){
                    //if length is not 0
                    subS = subS.substring(0, subS.length() - 1);
                }
            }else{
                //add new character to the subS
                subS += s.substring(i, i+1);
            }
        }
        for(int i=0; i<t.length(); i++){
            if(t.substring(i, i+1).equals("#")){
                if(subT.length() != 0){
                    //if length is not 0
                    subT = subT.substring(0, subT.length() - 1);
                }
            }else{
                //add new character to the subS
                subT += t.substring(i, i+1);
            }
        }
        if(subS.equals(subT)){
            return true;
        }
        return false;
    }

    public boolean backspaceCompare_Optimised(String s, String t){
        //use a pointer approach, start from the end of the string
        int sPointer = s.length() -1;
        int tPointer = t.length() - 1;
        int sCountHash = 0;
        int tCountHash = 0;
        while(true){
            String sChar = s.substring(sPointer, sPointer + 1);
            String tChar = t.substring(tPointer, tPointer + 1);
            if(sChar == "#"){
                sCountHash++;
                sPointer--;
            }else{
                //check if hashcount == 0
                while(sCountHash != 0){
                    sPointer--;

                }
            }
            if(tChar == "#"){
                tCountHash++;
                tPointer--;
            }

        }
    }

    public static void main(String[] args){
        String s = "hello";
        String sub = s.substring(0,1);
        sub += s.substring(2, 3);
        System.out.println(sub);
    }
}
