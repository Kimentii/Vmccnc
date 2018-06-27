package com.mzal;

import com.mzal.dto.AutomaticLine;
import com.mzal.dto.Lathe;
import com.mzal.dto.Livetool;
import com.mzal.dto.Tube;

import java.util.ArrayList;

public class ItemStorage {

    private static ArrayList<AutomaticLine> sAutomaticLines;
    private static ArrayList<Lathe> sLathes;
    private static ArrayList<Livetool> sLivetools;
    private static ArrayList<Tube> sTubes;
    private static ArrayList<AdapterGenerator> sCart = new ArrayList<>();

    public static ArrayList<AutomaticLine> getAutomaticLines() {
        return sAutomaticLines;
    }

    public static void setAutomaticLines(ArrayList<AutomaticLine> automaticLines) {
        sAutomaticLines = automaticLines;
    }

    public static ArrayList<Lathe> getLathes() {
        return sLathes;
    }

    public static void setLathes(ArrayList<Lathe> lathes) {
        sLathes = lathes;
    }

    public static ArrayList<Livetool> getLivetools() {
        return sLivetools;
    }

    public static void setLivetools(ArrayList<Livetool> livetools) {
        sLivetools = livetools;
    }

    public static ArrayList<Tube> getTubes() {
        return sTubes;
    }

    public static void setTubes(ArrayList<Tube> tubes) {
        sTubes = tubes;
    }

    public static ArrayList<AdapterGenerator> getCartItems() {
        return sCart;
    }

    public static void addToCart(AdapterGenerator adapterGenerator) {
        sCart.add(adapterGenerator);
    }
}
