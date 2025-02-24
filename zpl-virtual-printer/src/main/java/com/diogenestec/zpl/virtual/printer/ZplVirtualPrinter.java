/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.diogenestec.zpl.virtual.printer;

import services.WebSocketServerApp;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import services.IconLoader;

/**
 *
 * @author diogenes
 */
public class ZplVirtualPrinter {

    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Inicia o servidor WebSocket em uma thread separada
        new Thread(() -> {
            WebSocketServerApp serverApp = new WebSocketServerApp();
            serverApp.start();
        }).start();
        
        frmPrograma f = new frmPrograma();
        ImageIcon icon = IconLoader.loadIcon("printer.png");
        f.setIconImage(icon.getImage());
        
        f.setVisible(true);
    }
    
}
