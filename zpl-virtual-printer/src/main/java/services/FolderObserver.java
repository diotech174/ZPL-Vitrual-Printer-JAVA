/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.diogenestec.zpl.virtual.printer.frmPrograma;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diogenes
 */
public class FolderObserver {

    private frmPrograma frm;

    public FolderObserver(frmPrograma frm) {
        this.frm = frm;
    }

    public void monitore(String folder) {
        try {
            // Cria um WatchService
            WatchService watchService = FileSystems.getDefault().newWatchService();

            // Define o caminho da pasta que será monitorada
            Path path = Paths.get(folder);

            // Registra a pasta para monitorar eventos de criação e exclusão de arquivos
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);

            System.out.println("Monitorando a pasta: " + path);

            // Loop infinito para monitorar a pasta
            while (true) {
                WatchKey key;
                try {
                    // Aguarda até que um evento seja disparado
                    key = watchService.take();
                } catch (InterruptedException e) {
                    return;
                }

                // Itera sobre os eventos
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path fileName = (Path) event.context();

                    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                        System.out.println("Arquivo criado: " + fileName);

                        Thread thread = new Thread(() -> {
                            try {
                                TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(FolderObserver.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            if (fileName.toString().contains(".png")) {
                                frm.setImageLabel(folder + "/" + fileName.toString());
                            } else if (fileName.toString().contains(".txt")) {
                                frm.setZplText(fileName.toString());
                            }
                        });
                        thread.start();

                    } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                        System.out.println("Arquivo deletado: " + fileName);

                        // Verifica se a pasta está vazia após a exclusão
                        if (isFolderEmpty(path)) {
                            frm.removeLabel();
                        }

                        frm.updateLabelImage(fileName.toString());
                    }
                }

                // Reseta o WatchKey para continuar monitorando
                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método auxiliar para verificar se a pasta está vazia
    private boolean isFolderEmpty(Path path) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            return !stream.iterator().hasNext(); // Retorna true se não houver nenhum arquivo na pasta
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Se houver erro ao verificar, assume que não está vazia
        }
    }

}
