/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ums;

import com.mycompany.ums.db.DatabaseHandler;
import com.mycompany.ums.gui.MainMenu;

public class UMS {
    public static void main(String[] args) {
        DatabaseHandler.connect();  
        new MainMenu().displayMainMenu();
    }
}


