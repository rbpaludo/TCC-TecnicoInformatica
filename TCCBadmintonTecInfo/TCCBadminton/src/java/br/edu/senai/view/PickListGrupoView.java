/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.view;

import br.edu.senai.controller.GrupoAlunosDAO;
import br.edu.senai.model.Exercicio;
import br.edu.senai.model.GrupoAlunos;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.swing.JOptionPane;
import org.primefaces.model.DualListModel;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Gustavo
 */
@ManagedBean
@ViewScoped
public class PickListGrupoView {

    public List<GrupoAlunos> getGruposSelecionados() {

        List<GrupoAlunos> gruposSelecionados = new ArrayList<>();
        List<GrupoAlunos> listaTarget = getGrupos().getTarget();

        for (int i = 0; i < listaTarget.size(); i++) {
            gruposSelecionados.add(listaTarget.get(i));
        }

        return gruposSelecionados;
    }

    public void atualizarTarget(List<GrupoAlunos> gruposExistentes) {
        this.gruposTarget = gruposExistentes;
        grupos.setTarget(gruposTarget);
        for (int i = 0; i < gruposTarget.size(); i++) {
            gruposSource.remove(gruposTarget.get(i));
        }
        grupos.setSource(gruposSource);
    }

    private DualListModel<GrupoAlunos> grupos;
    List<GrupoAlunos> gruposSource = new ArrayList<>();
    List<GrupoAlunos> gruposTarget = new ArrayList<>();

    @PostConstruct
    public void init() {
        
        GrupoAlunosDAO gDAO = new GrupoAlunosDAO();
        gruposTarget = new ArrayList<>();
        
        gruposSource = gDAO.listar(SecurityContextHolder.getContext().getAuthentication().getName());

        grupos = new DualListModel<GrupoAlunos>(gruposSource, gruposTarget);
    }

    public DualListModel<GrupoAlunos> getGrupos() {
        return grupos;
    }

    public void setGrupos(DualListModel<GrupoAlunos> grupos) {
        this.grupos = grupos;
    }

}
