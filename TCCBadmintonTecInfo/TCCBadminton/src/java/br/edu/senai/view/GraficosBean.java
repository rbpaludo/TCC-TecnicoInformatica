/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.senai.view;

import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author RafaelBP
 */
@ManagedBean
public class GraficosBean {
    private PieChartModel model;
    
    public GraficosBean(){
        model = new PieChartModel();
        model.set("Acertos", 100);
        model.set("Erros", 50);
    }

    public PieChartModel getModel() {
        return model;
    }

    public void setModel(PieChartModel model) {
        this.model = model;
    }
}
