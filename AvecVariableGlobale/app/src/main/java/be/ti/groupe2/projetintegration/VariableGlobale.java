package be.ti.groupe2.projetintegration;

import android.app.Application;


public class VariableGlobale extends Application{


    public int iDUser;
        public String listEvent;



        @Override
        public void onCreate(){
            super.onCreate();
            iDUser = 0;
            listEvent = "blabla";
        }

    public int getiDUser() {
        return iDUser;
    }

    public void setiDUser(int iDUser) {
        this.iDUser = iDUser;
    }

    public String getlistEvent() {
        return listEvent;
    }

    public void setListEvent(String listEvent) {
        this.listEvent = listEvent;
    }


}

