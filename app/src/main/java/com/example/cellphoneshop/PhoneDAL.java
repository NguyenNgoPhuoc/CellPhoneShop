package com.example.cellphoneshop;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PhoneDAL {
    public static List<Phone> phones = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void saveToFile(Context context) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(context.openFileOutput("phones.dat", Context.MODE_PRIVATE))){
            oos.writeObject(phones);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void readFromFile(Context context) {
        try (ObjectInputStream ois =
                     new ObjectInputStream(context.openFileInput("phones.dat"))){
            phones = (List<Phone>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }    }

    public static Phone find(String code) {
        for(Phone p : phones) {
            if(p.getCode().equals(code))
                return p;
        }
        return null;
    }
}
