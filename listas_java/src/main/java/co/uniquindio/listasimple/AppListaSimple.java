package co.uniquindio.listasimple;

public class AppListaSimple {
    public static void main(String[] args) {
        ListaSimple<Integer> lista = new ListaSimple<>();
        System.out.println(lista.estaVacia());
        lista.agregarInicio(1);
        System.out.println(lista.estaVacia());
        lista.agregarFinal(3);
        lista.agregarEn(99,1);
        lista.agregarFinal(4);
        lista.agregarInicio(5);
        lista.ordenarLista();
        System.out.println();
        lista.imprimirLista();

        //lista.obtenerValorNodo(1);
        System.out.println(lista.toString());
        //System.out.println(lista.obtenerValorNodo(0));
        lista.eliminarPrimero();
        System.out.println(lista.toString());
        lista.eliminarUltimo();
        System.out.println(lista.toString());
        lista.eliminarEn(1);
        System.out.println(lista.toString());
        lista.modificarNodo(1,7);
        System.out.println(lista.toString());


        ListaSimple<String> lista2 = new ListaSimple<>();
        lista2.agregarFinal("a");
        //Nodo<String> nodoA = lista2.obtenerNodo(0);//Obtener nodo
       // System.out.println(nodoA.getDato());


        //System.out.println(lista2.obtenerValorNodo(0));
        lista2.agregarFinal("b");
        lista2.agregarFinal("c");
        System.out.println(lista2.toString());
        //System.out.println(lista2.obtenerPosicionNodo("f"));
    }
}
