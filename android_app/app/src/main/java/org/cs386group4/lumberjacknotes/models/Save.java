package org.cs386group4.lumberjacknotes.models;


import java.io.Serializable;

public class Save implements Serializable
import android.content.Context;

import org.cs386group4.lumberjacknotes.ui.MainActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Save

{
    private boolean savedToCloud = false;
    private boolean successfulSave = false;
    private UserProfile userProfile;
    private Notes currentNote;

    public Save(UserProfile userProfile, Notes currentNote)
    {
        userProfile = userProfile;
    }

    public void autoCloudSave()
    {

    }

    public void manualCloudSave()
    {

    }




//    public void loadNotes(MainActivity mainActivity)
//    {
//        try
//        {
//            // Opens file where the UserProfile will be stored locally
//            FileInputStream fileInputStream = mainActivity.openFileInput("UserProfile.txt");
//            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//            // Opens UserProfile object from file and closes read access
//            userProfile = (UserProfile) objectInputStream.readObject();
//            // TODO: May remove this current note assignment elsewhere once implementations supports multiple notes
//            currentNote = userProfile.getWrittenNotes().get(0);
//            objectInputStream.close();
//            fileInputStream.close();
//        }
//        catch(IOException | ClassNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//    }

//    public void saveNoteToLocalStorage(MainActivity mainActivity)
//    {
//        try
//        {
//            // Creates file where the UserProfile will be stored locally
//            FileOutputStream fileOutputStream = mainActivity.openFileOutput("UserProfile.txt", Context.MODE_PRIVATE);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//            // Writes UserProfile object to file and closes write access
//            objectOutputStream.writeObject(userProfile);
//            objectOutputStream.close();
//            fileOutputStream.close();
//        }
//        catch(IOException e)
//        {
//            e.printStackTrace();
//        }
//    }


}
