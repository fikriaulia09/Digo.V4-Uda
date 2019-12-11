package com.example.digo.Favorite;

import com.example.digo.Home.Information;
import com.example.digo.R;

import java.util.ArrayList;

public class InformationData {

    public static String[] namaTempat = new String[]{
            "Mol Boemi Kedaton",
            "Pantai Sari Ringgung",
            "Menara Siger",
            "Pulau Angso Duo",
            "Lembah Hijau",
            "pahawang"
    };

    public static String[] lokasiTempat = new String[]{
            "Jl. Teuku Umar, Kedaton",
            "Jl. Way Ratai No.KM. 14, Sidodadi",
            "Jalan Lintas Sumatra, Bakauheni",
            "Pasir, Central Pariaman",
            "l. Raden Imba Kusuma Ratu No.21, Sukadana Ham",
            "pahawang island"
    };

    private static int[] fotoTempat = {
            R.drawable.mbk,
            R.drawable.sariringgung,
            R.drawable.menarasiger,
            R.drawable.angsuduo,
            R.drawable.lembahhijau,
            R.drawable.pahawang

    };


    public static ArrayList<com.example.digo.Favorite.Information> getListData() {
        ArrayList<com.example.digo.Favorite.Information> list = new ArrayList<>();
        for (int position = 0; position < namaTempat.length; position++) {
            com.example.digo.Favorite.Information information = new com.example.digo.Favorite.Information();
            information.setNama(namaTempat[position]);
            information.setLokasi(lokasiTempat[position]);
            information.setImage(fotoTempat[position]);
            list.add(information);
        }
        return list;
    }
}
