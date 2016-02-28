package music.model;

/**
 * Created by woodev01 on 2/28/16.
 */

import java.util.ArrayList;
import java.util.Hashtable;

public class Keys {
    public static Hashtable<String,Integer> A = new Hashtable<>();
    public static Hashtable<String,Integer> B = new Hashtable<>();
    public static Hashtable<String,Integer> C = new Hashtable<>();
    public static Hashtable<String,Integer> D = new Hashtable<>();
    public static Hashtable<String,Integer> E = new Hashtable<>();
    public static Hashtable<String,Integer> F = new Hashtable<>();
    public static Hashtable<String,Integer> G = new Hashtable<>();

    public static Hashtable<String,Integer> AFlat = new Hashtable<>();
    public static Hashtable<String,Integer> BFlat = new Hashtable<>();
    public static Hashtable<String,Integer> CFlat = new Hashtable<>();
    public static Hashtable<String,Integer> DFlat = new Hashtable<>();
    public static Hashtable<String,Integer> EFlat = new Hashtable<>();
    public static Hashtable<String,Integer> FFlat = new Hashtable<>();
    public static Hashtable<String,Integer> GFlat = new Hashtable<>();

    public static Hashtable<String,Integer> ASharp = new Hashtable<>();
    public static Hashtable<String,Integer> BSharp = new Hashtable<>();
    public static Hashtable<String,Integer> CSharp = new Hashtable<>();
    public static Hashtable<String,Integer> DSharp = new Hashtable<>();
    public static Hashtable<String,Integer> ESharp = new Hashtable<>();
    public static Hashtable<String,Integer> FSharp = new Hashtable<>();
    public static Hashtable<String,Integer> GSharp = new Hashtable<>();

    public static ArrayList<Hashtable> keys = new ArrayList<>();




    public Keys (){
        deriveKeys();
        keys.add(A);
        keys.add(ASharp);
        keys.add(B);
        keys.add(C);
        keys.add(CSharp);
        keys.add(D);
        keys.add(DSharp);
        keys.add(E);
        keys.add(F);
        keys.add(FSharp);
        keys.add(G);
        keys.add(GSharp);
    }


    private void deriveKeys() {
        A.put("A", 0);
        A.put("B", 2);
        A.put("C#", 4);
        A.put("D", 5);
        A.put("E", 7);
        A.put("F#", 9);
        A.put("G#", 11);

        ASharp.put("A#", 1);
        ASharp.put("B#", 3);
        ASharp.put("D", 5);
        ASharp.put("D#", 6);
        ASharp.put("E#", 8);
        ASharp.put("G", 10);
        ASharp.put("A", 12);

        B.put("B", 2);
        B.put("C#", 4);
        B.put("D#", 6);
        B.put("E", 7);
        B.put("F#", 9);
        B.put("G#", 11);
        B.put("A#", 13);

    }



}
