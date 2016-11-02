package br.com.patrimonio.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "checksPicosBean")
@ViewScoped
public class UploadBean {

    public void upload(FileUploadEvent event) {

        try {

            // Aqui cria o diretorio caso n�o exista
            File file = new File("C:/Users/Almir Ricardo/Desktop/My Shared Folder/");
            file.mkdirs();

            byte[] arquivo = event.getFile().getContents();
            String caminho = "C:/Users/Almir Ricardo/Desktop/My Shared Folder/"
                    + event.getFile().getFileName();

            // esse trecho grava o arquivo no diretorio
            FileOutputStream fos = new FileOutputStream(caminho);
            fos.write(arquivo);
            fos.close();

            System.out.println("O caminho da imagem salva o  = " + caminho);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
