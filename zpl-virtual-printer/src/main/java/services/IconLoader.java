/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import javax.swing.ImageIcon;
import java.net.URL;


/**
 *
 * @author diogenes
 */
public class IconLoader {
    public static ImageIcon loadIcon(String iconName) {
        URL iconURL = IconLoader.class.getResource("/icons/" + iconName);
        if (iconURL != null) {
            return new ImageIcon(iconURL);
        } else {
            System.err.println("Ícone não encontrado: " + iconName);
            return null;
        }
    }
}
