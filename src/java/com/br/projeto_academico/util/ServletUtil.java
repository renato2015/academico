/*
 * ServletUtil.java
 *
 * Created on 12/01/2016, 17:02:02
 */

package com.br.projeto_academico.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import static org.hibernate.internal.util.collections.CollectionHelper.arrayList;

/**
 *
 * @author Tekna <informatica@teknamed.com.br>
 */
public class ServletUtil{
    private static ServletUtil instance;

    public static ServletUtil getInstance(){
        if(instance == null){
            instance = new ServletUtil();
        }
        return instance;
    }

    /**
     * Método que retorna o caminho completo de um arquivo ou pasta da aplicação.
     * @param diretorio
     * @return diretorio
     */
    private String getDiretorioReal(String diretorio) {
        HttpSession session = (HttpSession)
        FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getServletContext().getRealPath(diretorio);
    }

    /**
     * Método para retornar o nome da aplicação.
     * @return Nome da aplicação
     */
    private String getContextPath() {
        HttpSession session = (HttpSession)
        FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getServletContext().getContextPath();
    }

    /**
     * Método que gera o arquivo PDF.
     * @param print
     * @throws JRException
     */
    private void preenchePdf(JasperPrint print) throws JRException {
        // Pego o caminho completo do PDF desde a raiz
        String saida = getDiretorioReal("/pdf/relatorio.pdf");
        // Exporto para PDF
        JasperExportManager.exportReportToPdfFile(print, saida);
        /*
         * Jogo na variável saída o nome da aplicação mais o
         * caminho para o PDF. Essa variável será utilizada pela view
         */
        saida = getContextPath() + "/pdf/relatorio.pdf";
    }


    public void executeReport() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map parameters = new HashMap();

        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        InputStream reportStream = facesContext.getExternalContext().getResourceAsStream("caminho_arquivo.jasper");
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");

        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();

            //classe que envia um lista de objeto
            //JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(arrayList);

            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream,  parameters);

            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            facesContext.responseComplete();
        }
    }
}
