package com.nicu.life.FirebaseClass;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FireStorage {
    private static FireStorage single_instance = null;
    private StorageReference ref;

    private  FireStorage() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        ref = storage.getReference();
    }

    public static FireStorage getInstance() {
        if (single_instance == null) {
            single_instance = new FireStorage();
        }
        return single_instance;
    }

    public StorageReference getImgRef(String file) {
        return ref.child("img").child(file);
    }
}