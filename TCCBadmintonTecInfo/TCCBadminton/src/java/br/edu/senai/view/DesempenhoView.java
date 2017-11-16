/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.view;

import br.edu.senai.controller.AlunoDAO;
import br.edu.senai.model.Aluno;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.swing.JOptionPane;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author RafaelBP
 */
@ViewScoped
@ManagedBean
public class DesempenhoView {

    private Boolean exibeGraficoDesemp = false;
    private PieChartModel PCMDesempenho;
    AlunoDAO alunoDAO = new AlunoDAO();
    Boolean exibeDesempenho = false;

    public void exibirDesempenho(AlunoBeanBuscar aBean) {

        Aluno alunoSelecionado = aBean.getAlunoSelecionado();
        PCMDesempenho = new PieChartModel();
        if (alunoDAO.genialidadeDesemp(alunoSelecionado) != null) {
            PCMDesempenho = alunoDAO.genialidadeDesemp(alunoSelecionado);
        }
    }

    public Boolean isExibeGraficoDesemp() {
        return exibeGraficoDesemp;
    }

    public void setExibeGraficoDesemp(Boolean exibeGraficoDesemp) {
        this.exibeGraficoDesemp = exibeGraficoDesemp;
    }

    public PieChartModel carregaPieChart(AlunoBeanBuscar aBean) {
    
        PieChartModel modeloCarregado = new PieChartModel();
        if (aBean.isMostraGraficos() == true && aBean.getAlunoSelecionado() != null){
        modeloCarregado = alunoDAO.retornaPCMDesempenho(aBean.getAlunoSelecionado(), aBean.getExercicioSelecionado());    
        }else{
            modeloCarregado.set("Selecione um Aluno", 1);          
        }
        return modeloCarregado;
    }

    public AlunoDAO getAlunoDAO() {
        return alunoDAO;
    }

    public void setAlunoDAO(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    public Boolean isExibeDesempenho() {
        return exibeDesempenho;
    }

    public void setExibeDesempenho(Boolean exibeDesempenho) {
        this.exibeDesempenho = exibeDesempenho;
    }

    public boolean cu() {
        if (PCMDesempenho.getData().toString().length() > 2) {

            JOptionPane.showMessageDialog(null, "cu2" + PCMDesempenho.getData().toString().length());
            return true;

        } else {
            JOptionPane.showMessageDialog(null, "ceta2");
            return false;
        }

    }

    public PieChartModel getPCMDesempenho() {

        return PCMDesempenho;

    }

}
