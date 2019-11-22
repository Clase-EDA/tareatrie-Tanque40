package libraries;

public class Trie<T>{
    private char[] ord;
    private Nodo<T> raiz;
    private int cont;

    public Trie(char[] ord){
        this.ord = ord;
        raiz = new Nodo<T>(ord);
    }

    public boolean busca(String llave){
        int i = pos(llave.charAt(0));
        if( i >= 0){
            return busca(llave.substring(1), raiz.getHijo(i));
        } else {
            return false;
        }
    }
    private boolean busca(String llave, Nodo<T> current){
        if(llave.length() > 0){
            int i = pos(llave.charAt(0));
            if(i >= 0)
                return busca(llave.substring(1), current.getHijo(i));
            else
                return false;
        } else {
            if(current.getCont() > 0)
                return true;
            else
                return false; 
        }
    }

    public void add(String llave){
        int i = pos(llave.charAt(0));
        if(i >= 0){
            raiz.addKey(llave);
            cont++;
        }
    }

    public boolean elimina(String llave){
        int i = pos(llave.charAt(0));
        if(i >= 0)
            return elimina(llave.substring(1), raiz.getHijo(i));
        else
            return false;
    }
    private boolean elimina(String llave, Nodo<T> current){
        int i = pos(llave.charAt(0));
        if(llave.length() > 0){
            if(i >= 0)
                return elimina(llave.substring(1), current.getHijo(i));
            else
                return false;    
        } else {
            if(current.getCont() > 0){
                current.setCont(current.getCont() - 1);
                return true;
            } else {
                return false;
            }
            
        }
    }


    private int pos(char c) {
        int i = 0;
        while (i < ord.length && ord[i] != c)
            i++;
        if (i >= ord.length)
            return -1;
        else
            return i;
    }

    public String toString(){
        int i = 0;
        Nodo<T> current = raiz;
        String res = "";
        while(i < ord.length){
            if(current.getHijo(i) != null){
                if (current.getHijo(i).getCont() > 0)
                    for (int j = 0; j < current.getHijo(i).getCont(); j++)
                        res += ord[i] + "\n";
                for (int j = 0; j < current.getHijo(i).getContHijos(); j++)
                    res += ord[i] + current.getHijo(i).toString() + "\n";
            }
            i++;
        }

        return res;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }
}