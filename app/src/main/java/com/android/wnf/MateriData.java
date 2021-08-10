package com.android.wnf;

import com.android.wnf.model.Materi;
import com.android.wnf.model.Materis;
import com.android.wnf.model.ParentMateris;
import com.android.wnf.model.ParentMateris;
import com.android.wnf.model.SubMateri;
import com.android.wnf.model.SubMaterisData;
import com.android.wnf.model.SubMateris;
import com.android.wnf.model.SubMaterisData;

import java.util.ArrayList;
import java.util.List;

public class MateriData {
    private List<ParentMateris> parentMateriList = new ArrayList<>();
    public List<ParentMateris> getParentMateriList(){ return parentMateriList; }
    public MateriData(){
        initializeMateri();
    }
    private void initializeMateri(){
        parentMateriList.add(new ParentMateris(0 , "Materi Bilangan Bulat" , new ArrayList(){{
            add(new Materis(0 , "Pengertian Bilangan Bulat" , 0 , new ArrayList(){{
                add(new SubMateris(0 , "Pengertian Bilangan Bulat" , R.drawable.pengertian_bil_bulat_1 , new ArrayList<>()));
                add(new SubMateris(1 , "Pengertian Bilangan Bulat" , R.drawable.pengertian_bil_bulat_2 , new ArrayList<>()));
                add(new SubMateris(2 , "Pengertian Bilangan Bulat" , R.drawable.pengertian_bil_bulat_3 , new ArrayList<>()));
            }}));

            add(new Materis(1 , "Operasi Hitung Pada Bilangan Bulat" , 1 , new ArrayList(){{

                add(new SubMateris(0 , "Penjumlahan dan Pengurangan" , 0 , new ArrayList(){{
                    add(new SubMaterisData(0 , "Penjumlahan dan Pengurangan" , "" , R.drawable.penjumlahan_pengurangan));
                }}));

                add(new SubMateris(1 , "Perkalian" , 0 , new ArrayList(){{
                    add(new SubMaterisData(0 , "Perkalian" , "" , R.drawable.perkalian));
                }}));

                add(new SubMateris(2 , "Pembagian" , 0 , new ArrayList(){{
                    add(new SubMaterisData(0 , "Pembagian" , "" , R.drawable.pembagian));
                }}));
            }}));

            add(new Materis(2 , "Sifat - Sifat Operasi Bilangan Bulat" , 0 , new ArrayList(){{

                add(new SubMateris(0 , "Sifat - Sifat Operasi Bilangan Bulat" , R.drawable.sifat_operasi_hitung_bil_bulat_1 , new ArrayList()));

                add(new SubMateris(1 , "Sifat - Sifat Operasi Bilangan Bulat" , R.drawable.sifat_operasi_hitung_bil_bulat_2 , new ArrayList()));

            }}));
            add(new Materis(3 , "Pangkat dan Akar Pangkat Pada Bilangan Bulat" , 1 , new ArrayList(){{

                add(new SubMateris(0 , "Kuadrat dan Pangkat Tiga Bilangan Bulat" , 0 , new ArrayList(){{
                    add(new SubMaterisData(0 , "Kuadrat dan Pangkat Tiga Bilangan Bulat" , "" , R.drawable.kuadrat_pangkat_tiga_bil_bulat_1));
                    add(new SubMaterisData(1 , "Kuadrat dan Pangkat Tiga Bilangan Bulat" , "" , R.drawable.kuadrat_pangkat_tiga_bil_bulat_2));
                }}));

                add(new SubMateris(0 , "Akar Kuadrat dan Akar Pangkat Tiga" , 0 , new ArrayList(){{
                    add(new SubMaterisData(0 , "Akar Kuadrat dan Akar Pangkat Tiga" , "" , R.drawable.akar_kuadrat_dan_akar_pangkat_tiga_1));
                    add(new SubMaterisData(1 , "Akar Kuadrat dan Akar Pangkat Tiga" , "" , R.drawable.akar_kuadrat_dan_akar_pangkat_tiga_2));
                }}));

            }}));
        }}));

        parentMateriList.add(new ParentMateris(2 , "Materi Bilangan Pecahan" , new ArrayList(){{
            add(new Materis(0 , "Pengertian Bilangan Pecahan" , 0 , new ArrayList(){{
                add(new SubMateris(0 , "Pengertian Bilangan Pecahan" , R.drawable.pengertian_bil_pecahan , new ArrayList()));
            }}));
            add(new Materis(1 , "Macam - Macam Bilangan Pecahan" ,1 , new ArrayList(){{
                add(new SubMateris( 0 , "Macam - Macam Bilangan Pecahan" , 0 , new ArrayList(){{
                    add(new SubMaterisData(0 , "Macam - Macam Bilangan Pecahan" , "" , R.drawable.macam_macam_bil_pecahan_1));
                    add(new SubMaterisData(1 , "Macam - Macam Bilangan Pecahan" , "" , R.drawable.macam_macam_bil_pecahan_2));
                    add(new SubMaterisData(2 , "Macam - Macam Bilangan Pecahan" , "" , R.drawable.macam_macam_bil_pecahan_3));
                    add(new SubMaterisData(3 , "Macam - Macam Bilangan Pecahan" , "" , R.drawable.macam_macam_bil_pecahan_4));
                    add(new SubMaterisData(4 , "Macam - Macam Bilangan Pecahan" , "" , R.drawable.macam_macam_bil_pecahan_5));
                    add(new SubMaterisData(5 , "Macam - Macam Bilangan Pecahan" , "" , R.drawable.macam_macam_bil_pecahan_6));
                    add(new SubMaterisData(6 , "Macam - Macam Bilangan Pecahan" , "" , R.drawable.macam_macam_bil_pecahan_7));
                    add(new SubMaterisData(7 , "Macam - Macam Bilangan Pecahan" , "" , R.drawable.macam_macam_bil_pecahan_8));
                    add(new SubMaterisData(9 , "Macam - Macam Bilangan Pecahan" , "" , R.drawable.macam_macam_bil_pecahan_9));
                    add(new SubMaterisData(10 , "Macam - Macam Bilangan Pecahan" , "" , R.drawable.macam_macam_bil_pecahan_10));
                }}));
            }}));
            add(new Materis(2 , "Operasi Hitung Pada Bilangan Pecahan" ,1 , new ArrayList(){{
                add(new SubMateris(0 , "Penjumlahan" , 0 , new ArrayList(){{
                    add(new SubMaterisData(0 , "Penjumlahan Bilangan Pecahan" , "" , R.drawable.penjumlahan_bil_pecahan_1));
                    add(new SubMaterisData(1 , "Penjumlahan Bilangan Pecahan" , "" , R.drawable.penjumlahan_bil_pecahan_2));
                    add(new SubMaterisData(2 , "Penjumlahan Bilangan Pecahan" , "" , R.drawable.penjumlahan_bil_pecahan_3));
                    add(new SubMaterisData(3 , "Penjumlahan Bilangan Pecahan" , "" , R.drawable.penjumlahan_bil_pecahan_4));
                    add(new SubMaterisData(4 , "Penjumlahan Bilangan Pecahan" , "" , R.drawable.penjumlahan_bil_pecahan_5));
                    add(new SubMaterisData(5 , "Penjumlahan Bilangan Pecahan" , "" , R.drawable.penjumlahan_bil_pecahan_6));
                    add(new SubMaterisData(6 , "Penjumlahan Bilangan Pecahan" , "" , R.drawable.penjumlahan_bil_pecahan_7));
                }}));

                add(new SubMateris(1 , "Pengurangan" , 0 , new ArrayList(){{
                    add(new SubMaterisData(0 , "Pengurangan Bilangan Pecahan" , "" , R.drawable.pengurangan_bil_pecahan_1));
                    add(new SubMaterisData(1 , "Pengurangan Bilangan Pecahan" , "" , R.drawable.pengurangan_bil_pecahan_2));
                    add(new SubMaterisData(2 , "Pengurangan Bilangan Pecahan" , "" , R.drawable.pengurangan_bil_pecahan_3));
                    add(new SubMaterisData(3 , "Pengurangan Bilangan Pecahan" , "" , R.drawable.pengurangan_bil_pecahan_4));
                    add(new SubMaterisData(4 , "Pengurangan Bilangan Pecahan" , "" , R.drawable.pengurangan_bil_pecahan_5));
                    add(new SubMaterisData(5 , "Pengurangan Bilangan Pecahan" , "" , R.drawable.pengurangan_bil_pecahan_6));
                    add(new SubMaterisData(6 , "Pengurangan Bilangan Pecahan" , "" , R.drawable.pengurangan_bil_pecahan_7));
                }}));

                add(new SubMateris(2 , "Perkalian" , 0 , new ArrayList(){{
                    add(new SubMaterisData(0 , "Perkalian Bilangan Pecahan" , "" , R.drawable.perkalian_bil_pecahan_1));
                    add(new SubMaterisData(1 , "Perkalian Bilangan Pecahan" , "" , R.drawable.perkalian_bil_pecahan_2));
                    add(new SubMaterisData(2 , "Perkalian Bilangan Pecahan" , "" , R.drawable.perkalian_bil_pecahan_3));
                    add(new SubMaterisData(3 , "Perkalian Bilangan Pecahan" , "" , R.drawable.perkalian_bil_pecahan_4));
                    add(new SubMaterisData(4 , "Perkalian Bilangan Pecahan" , "" , R.drawable.perkalian_bil_pecahan_5));
                    add(new SubMaterisData(5 , "Perkalian Bilangan Pecahan" , "" , R.drawable.perkalian_bil_pecahan_6));
                }}));

                add(new SubMateris(3 , "Pembagian" , 0 , new ArrayList(){{
                    add(new SubMaterisData(0 , "Pembagian Bilangan Pecahan" , "" , R.drawable.pembagian_bil_pecahan_1));
                    add(new SubMaterisData(1 , "Pembagian Bilangan Pecahan" , "" , R.drawable.pembagian_bil_pecahan_2));
                    add(new SubMaterisData(2 , "Pembagian Bilangan Pecahan" , "" , R.drawable.pembagian_bil_pecahan_3));
                    add(new SubMaterisData(3 , "Pembagian Bilangan Pecahan" , "" , R.drawable.pembagian_bil_pecahan_4));
                    add(new SubMaterisData(3 , "Pembagian Bilangan Pecahan" , "" , R.drawable.pembagian_bil_pecahan_5));
                }}));
            }}));
        }}));
    }
}
