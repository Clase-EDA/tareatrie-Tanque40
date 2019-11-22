package libraries;

public class Nodo<T> {
    private static char[] keys;
    private Nodo<T> hijos[];
    private T element;
    private int cont;
    private int contVueltas;
    private int contHijosMostrados;
    private int contHijos;
    
    public Nodo(char[] ord) {
        hijos = new Nodo[ord.length];
        Sort.mergeSort(ord);
        keys = ord;
        cont = 0;
        contVueltas = 0;
        contHijosMostrados = 0;
        contHijos = 0;
    }
    public Nodo() {
        hijos = new Nodo[keys.length];
        cont = 0;
        contVueltas = 0;
        contHijosMostrados = 0;
        contHijos = 0;
    }
    public Nodo(String key) {
        hijos = new Nodo[keys.length];
        if (key != null)
            addKey(key);
    }

    public void addKey(String key){
        if (key.length() == 1) {
            if (hijos[pos(key.charAt(0))] == null) {
                hijos[pos(key.charAt(0))] = new Nodo<T>();
                hijos[pos(key.charAt(0))].setCont(getCont() + 1);
            } 
            else
                hijos[pos(key.charAt(0))].setCont(getCont() + 1);
        } 
        else {
            if (hijos[pos(key.charAt(0))] == null)
                hijos[pos(key.charAt(0))] = new Nodo<T>(key.substring(1));
            else {
                hijos[pos(key.charAt(0))].addKey(key.substring(1));
            }
            contHijos++; 
        }
    }

    public Nodo<T> getHijo(int i) {
        return hijos[i];
    }

    public void setHijos(Nodo<T> hijos, int i) {
        this.hijos[i] = hijos;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    private int pos(char c){
        int i = 0;
        while (i < keys.length && keys[i] != c)
            i++;
        if (i >= keys.length)
            return -1;
        else
            return i;
    }

    public String toString(){
        String res = "";
        if(contVueltas == keys.length)
            contVueltas = 0;
        while (contVueltas < keys.length) {
            if(this.cont - this.contHijosMostrados > 0){
                this.contHijosMostrados++;
                return "";
            } 
            if (this.getHijo(contVueltas) != null) {
                contVueltas++;
                return keys[contVueltas - 1] + this.getHijo(contVueltas - 1).toString();
            }
            contVueltas++;
        }

        return res;
    }

    public int getContVueltas() {
        return contVueltas;
    }

    public void setContVueltas(int contVueltas) {
        this.contVueltas = contVueltas;
    }

    public int getContHijosMostrados() {
        return contHijosMostrados;
    }

    public void setContHijosMostrados(int contHijosMostrados) {
        this.contHijosMostrados = contHijosMostrados;
    }

    public int getContHijos() {
        return contHijos;
    }

    public void setContHijos(int contHijos) {
        this.contHijos = contHijos;
    }

}

class Sort{
    public static void mergeSort(char a[]) {
        mergeSort(a, 0, a.length - 1);
    }

    private static void mergeSort(char a[], int min, int max) {
        char[] temp;
        int izq, der;
        if (min >= max - 1)
            return;
        int tam = max - min + 1, mitad = (max + min) / 2;
        temp = new char[tam];
        mergeSort(a, min, mitad);
        mergeSort(a, mitad + 1, max);

        izq = min;
        der = mitad + 1;
        for (int i = 0; i < tam; i++) {
            if (izq <= mitad && der <= max) {
                if (a[izq] < a[der])
                    temp[i] = a[izq++];
                else
                    temp[i] = a[der++];
            } else {
                if (izq <= mitad)
                    temp[i] = a[izq++];
                else
                    temp[i] = a[der++];
            }
        }
        for (int i = 0; i < temp.length; i++) {
            a[min + i] = temp[i];
        }
    }
}